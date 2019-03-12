package cn.wu.am.entity;

import cn.wu.am.mconst.ArticleConst;

import java.util.Date;

public class Article {
    private Integer id;
    private User user;// 发布文章的用户
    private String title;
    private String content;
    private Integer viewCount;
    private Integer status; // 文章的状态 0： 审核中， 1 审核通过 2 审核不同过，禁止公布
    private String statusMsg; // 审核备注（不同过的原因啊）
    private Date createTime;
    private Date updateTime;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCount=" + viewCount +
                ", status=" + status +
                ", statusMsg='" + statusMsg + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public static String statusView(int status) {
        String str = "审核中";
        if(status == ArticleConst.STATUC_CHECKED) {
            str = "审核通过";
        } else if(status == ArticleConst.STATUC_INVALID) {
            str = "禁止公布";
        }
        return str;
    }
}
