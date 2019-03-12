package cn.wu.am.service;

import cn.wu.am.dao.CommentMapper;
import cn.wu.am.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wuqian
 * @Created 2019/3/10 12:50
 * @Desc
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public boolean addComment(Comment comment) {
        return commentMapper.addComment(comment) > 0;
    }

    public List<Comment> getAllByArticleId(Integer articleId) {
        return  commentMapper.getAllByArticleId(articleId);
    }

    public int deleteAllByArticleId(Integer articleId) {
        return  commentMapper.deleteAllByArticleId(articleId);
    }
}
