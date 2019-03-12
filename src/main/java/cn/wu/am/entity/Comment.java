package cn.wu.am.entity;

import java.util.Date;

public class Comment {
    private Integer id;
    private User user; // 这条评论发出者
    //private Article article; // 这条评论对应的文章
    private Integer articleId;
    private String content; // 评论内容
    private Date createTime; // 评论时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
