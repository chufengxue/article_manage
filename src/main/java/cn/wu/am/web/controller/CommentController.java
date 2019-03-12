package cn.wu.am.web.controller;

import cn.wu.am.entity.Comment;
import cn.wu.am.entity.User;
import cn.wu.am.mconst.UserConst;
import cn.wu.am.service.ArticleService;
import cn.wu.am.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author wuqian
 * @Created 2019/3/10 11:15
 * @Desc
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/newComment")
    public String newComment(Integer articleId, String content, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute(UserConst.LOGIN_USER);
        if (user == null) {
            model.addAttribute("errorMsg", "你还没有登录");
            return "info";
        }
        if (content == null || "".equals(content.trim())) {
            model.addAttribute("errorMsg", "你没有填评论信息");
            return "info";
        }
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setCreateTime(new Date());
        comment.setContent(content);
        comment.setUser(user);
        boolean flag = commentService.addComment(comment);
        if(!flag) {
            model.addAttribute("errorMsg", "发生未知原因，评论失败");
            return "info";
        }
        model.addAttribute("errorMsg", "评论成功");
        return "redirect:/article_detail?id="+articleId;
    }
}
