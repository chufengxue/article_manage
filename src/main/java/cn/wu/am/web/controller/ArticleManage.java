package cn.wu.am.web.controller;

import cn.wu.am.entity.Article;
import cn.wu.am.entity.User;
import cn.wu.am.mconst.UserConst;
import cn.wu.am.param.PageBean;
import cn.wu.am.param.QueryInfo;
import cn.wu.am.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleManage {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/checkArticleList")
    public String checkArticle(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                               @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                               Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConst.LOGIN_USER);
        if(user==null || !"admin".equals(user.getUserType())) {
            return "redirect:/index";
        }
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setPagesize(pageSize);
        queryInfo.setCurrentpage(currentPage);
        PageBean<Article> pageBean = articleService.getAllWithChecking(queryInfo);
        model.addAttribute("pageBean", pageBean);
        return "admin/article_check_list" ;
    }

    @GetMapping("/checkArticle")
    public String checkArticleUI(@RequestParam(name = "id", defaultValue = "0") int articleId, Model model) {
        Article article = articleService.getArticle(articleId);
        if(article==null) {
            model.addAttribute("errorMsg", "文章不存在");
            return "info";
        }
        String statusView = Article.statusView(article.getStatus());
        model.addAttribute("statusView", statusView);
        model.addAttribute("article", article);
        return "admin/article_check";
    }

    @PostMapping("/checkArticle")
    public String checkArticle(@RequestParam(name = "id", defaultValue = "0") int articleId, int status, Model model) {
        Article article = articleService.getArticle(articleId);
        if(article==null) {
            model.addAttribute("errorMsg", "文章不存在");
            return "info";
        }
        article = new Article();
        article.setId(articleId);
        article.setStatus(status);
        boolean flag = articleService.updateArticle(article);
        if(!flag) {
            model.addAttribute("errorMsg", "发生错误，文章状态更新失败");
            return "info";
        } else {
            model.addAttribute("errorMsg", "文章状态更新成功");
            return "info";
        }
    }
}
