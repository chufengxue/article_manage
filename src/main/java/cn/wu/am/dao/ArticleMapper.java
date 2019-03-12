package cn.wu.am.dao;

import cn.wu.am.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    int add(Article article);

    int delete(Integer id);

    int update(Article article);

    Article find(Integer id);

    // 这里只需返回 id, title, user, create_time， 首页查看文章使用这个方法
    List<Article> getAll(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    List<Article> getAllWithChecking(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    List<Article> getAllById(@Param("id") Integer id, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    List<Article> getAllByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    int getCount();

    int getCountWithChecking();

    int getCountByUserId(Integer userId);

}
