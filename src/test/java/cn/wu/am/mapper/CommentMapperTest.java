package cn.wu.am.mapper;

import cn.wu.am.dao.CommentMapper;
import cn.wu.am.entity.Comment;
import cn.wu.am.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author wuqian
 * @Created 2019/3/10 12:38
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentMapperTest {
    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void testAddComment() {
        Comment comment = new Comment();
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        comment.setUser(user);
        comment.setContent("测试评论");
        comment.setCreateTime(new Date());
        comment.setArticleId(1);

        commentMapper.addComment(comment);
    }

    @Test
    public void testGetAllByArticleId() {
        List<Comment> comments = commentMapper.getAllByArticleId(1);
        for(Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    public void deleteAllByArticleId() {
        int count = commentMapper.deleteAllByArticleId(1);
        System.out.println(count);
    }
}
