package cn.wu.am.service;

import cn.wu.am.dao.ArticleMapper;
import cn.wu.am.entity.Article;
import cn.wu.am.param.PageBean;
import cn.wu.am.param.QueryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;



    public boolean addArticle(Article article) {
        return articleMapper.add(article)>0;
    }

    public boolean updateArticle(Article article) {
        return  articleMapper.update(article)>0;
    }

    public Article getArticle(int id) {
        return articleMapper.find(id);
    }

    public PageBean<Article> getAll(QueryInfo queryInfo) {
        List<Article> data = articleMapper.getAll(queryInfo.getStartindex(), queryInfo.getPagesize());
        int count = articleMapper.getCount();
        PageBean<Article> articlePageBean = new PageBean<>();
        articlePageBean.setData(data);
        articlePageBean.setTotalrecord(count);
        articlePageBean.setPagesize(queryInfo.getPagesize());
        articlePageBean.setCurrentpage(queryInfo.getCurrentpage());
        return articlePageBean;
    }

    public PageBean<Article> getAll(int userId, QueryInfo queryInfo) {
        List<Article> data = articleMapper.getAllByUserId(userId, queryInfo.getStartindex(), queryInfo.getPagesize());
        int count = articleMapper.getCountByUserId(userId);
        PageBean<Article> articlePageBean = new PageBean<>();
        articlePageBean.setData(data);
        articlePageBean.setTotalrecord(count);
        articlePageBean.setPagesize(queryInfo.getPagesize());
        articlePageBean.setCurrentpage(queryInfo.getCurrentpage());
        return articlePageBean;
    }

    public int getUserArticleCount(Integer userId) {
        return articleMapper.getCountByUserId(userId);
    }

    public PageBean<Article> getAllWithChecking(QueryInfo queryInfo) {
        List<Article> data = articleMapper.getAllWithChecking(queryInfo.getStartindex(), queryInfo.getPagesize());
        int count = articleMapper.getCountWithChecking();
        PageBean<Article> articlePageBean = new PageBean<>();
        articlePageBean.setData(data);
        articlePageBean.setTotalrecord(count);
        articlePageBean.setPagesize(queryInfo.getPagesize());
        articlePageBean.setCurrentpage(queryInfo.getCurrentpage());
        return articlePageBean;
    }

    public boolean deleteArticleById(int articleId) {
        return articleMapper.delete(articleId)>0;
    }

}
