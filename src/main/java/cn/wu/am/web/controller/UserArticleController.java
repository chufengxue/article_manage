package cn.wu.am.web.controller;

import cn.wu.am.entity.Article;
import cn.wu.am.entity.Comment;
import cn.wu.am.entity.User;
import cn.wu.am.mconst.ArticleConst;
import cn.wu.am.mconst.UserConst;
import cn.wu.am.param.NewArticleParam;
import cn.wu.am.param.PageBean;
import cn.wu.am.param.QueryInfo;
import cn.wu.am.service.ArticleService;
import cn.wu.am.service.CommentService;
import cn.wu.am.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

// 普通用户使用这个 Controller
@Controller
public class UserArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/newArticle")
    public String addArticleUI(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute(UserConst.LOGIN_USER);
        if(user==null) {
            model.addAttribute("errorMsg", "你还没有登录");
            return "info";
        }
        if(user.getUserStatus()==UserConst.USER_STATUS_CHECKING) {
            model.addAttribute("errorMsg", "你当前没有权限发布文章。");
            return "info";
        }
        model.addAttribute("userId", user.getId());
        return "edit_article";
    }

    @PostMapping("/newArticle")
    public String addArticle(@Valid  NewArticleParam articleParam, BindingResult result, int userId, Model model, HttpServletRequest request) {

        User loginedUser = (User) request.getSession().getAttribute(UserConst.LOGIN_USER);
        if(loginedUser==null) {
            model.addAttribute("errorMsg", "你还没有登录");
            return "info";
        }
        if(loginedUser.getUserStatus()==UserConst.USER_STATUS_CHECKING) {
            model.addAttribute("errorMsg", "你当前没有权限发布文章。");
            return "info";
        }

        model.addAttribute("userId", userId);
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getDefaultMessage() + "; ";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "edit_article";
        }

        User loginUser = (User) request.getSession().getAttribute(UserConst.LOGIN_USER);
        int articleStatus = "admin".equals(loginUser.getUserType())? ArticleConst.STATUC_CHECKED : ArticleConst.STATUC_CHECKING;

        User user = new User();
        user.setId(userId);
        Article article = new Article();
        article.setUser(user);
        article.setUpdateTime(new Date());
        article.setViewCount(0);
        article.setCreateTime(new Date());
        article.setStatus(articleStatus);
        article.setTitle(articleParam.getTitle());
        article.setContent(articleParam.getContent());
        boolean flag = articleService.addArticle(article);
        if(!flag) {
            errorMsg = "发送未知原因，文章发布失败";
        } else {
            errorMsg = "发布成功";
        }
        model.addAttribute("errorMsg", errorMsg);
        return "info";
    }

    @GetMapping("/article_detail")
    public String articleDetail(int id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConst.LOGIN_USER);
        String errorMsg = "";
        Article article = articleService.getArticle(id);
        if(article==null) {
            errorMsg = "文章不存在";
            model.addAttribute("errorMsg", errorMsg);
            return "info";
        }

        List<Comment> comments = commentService.getAllByArticleId(article.getId());
        model.addAttribute("comments", comments);
        model.addAttribute("article", article);
        model.addAttribute("statusView", Article.statusView(article.getStatus()));


        // 如果用户已登录，且是管理员或者该篇文章的发布者
        if(user!=null && (user.getUserType().equals("admin") || user.getId() == article.getUser().getId())) {
            return "user/article_detail";
        } else {
            return "article_detail";
        }
    }

    @RequestMapping({"article_list/{userId}"})
    public String index(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                        @PathVariable int userId, Model model) {
        User user = userService.findUserById(userId);
        if(user==null) {
            model.addAttribute("errorMsg", "用户不存在");
            return "info";
        }
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setPagesize(pageSize);
        queryInfo.setCurrentpage(currentPage);
        PageBean<Article> pageBean = articleService.getAll(userId, queryInfo);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("user", user);
        return "user/article_list" ;
    }

    // article_delete
    @RequestMapping("/article_delete")
    public String article_delete(@RequestParam(name = "id", defaultValue = "0") int articleId,int userId, Model model) {
        Article article = articleService.getArticle(articleId);
        if(article==null) {
            model.addAttribute("errorMsg", "文章不存在");
            return "info";
        }
        boolean flag = articleService.deleteArticleById(articleId);
        if(!flag) {
            model.addAttribute("errorMsg", "发生错误，删除失败");
            return "info";
        }
        commentService.deleteAllByArticleId(articleId);
        return "redirect:/article_list/" + userId;
    }
}
