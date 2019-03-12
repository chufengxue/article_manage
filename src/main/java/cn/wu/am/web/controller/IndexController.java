package cn.wu.am.web.controller;

import cn.wu.am.entity.Article;
import cn.wu.am.entity.User;
import cn.wu.am.param.PageBean;
import cn.wu.am.param.QueryInfo;
import cn.wu.am.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

   /* @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }*/

    @RequestMapping({"/", "/index"})
    public String index(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, Model model) {
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setPagesize(pageSize);
        queryInfo.setCurrentpage(currentPage);
        PageBean<Article> pageBean = articleService.getAll(queryInfo);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }

}
