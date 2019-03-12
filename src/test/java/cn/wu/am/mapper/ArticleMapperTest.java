package cn.wu.am.mapper;

import cn.wu.am.dao.ArticleMapper;
import cn.wu.am.entity.Article;
import cn.wu.am.entity.User;
import cn.wu.am.mconst.ArticleConst;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void test() {
        System.out.println(articleMapper);
    }

    @Test
    public void testAdd() {
        Article article = new Article();
        User user = new User();
        user.setId(1);
        article.setUser(user);
        article.setTitle("测试标题");
        article.setContent("测试内容");
        article.setCreateTime(new Date());
        article.setStatus(ArticleConst.STATUC_CHECKING);
        article.setViewCount(0);
        int count = articleMapper.add(article);
        System.out.println(count);
    }

    @Test
    public void testFind() {
        Article article = articleMapper.find(1);
        System.out.println(article);
    }

    @Test
    public void testUpdate() {
        Article article = new Article();
        article.setId(1);
        article.setViewCount(10);
        article.setTitle("修改测试标题");
        article.setContent("修改测试内容");
        article.setUpdateTime(new Date());
        int count = articleMapper.update(article);
        System.out.println(count);
    }
}
