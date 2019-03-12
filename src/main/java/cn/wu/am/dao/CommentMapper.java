package cn.wu.am.dao;

import cn.wu.am.entity.Comment;

import java.util.List;

public interface CommentMapper {

    int addComment(Comment comment);

    List<Comment> getAllByArticleId(Integer articleId);

    int deleteAllByArticleId(Integer articleId);
}
