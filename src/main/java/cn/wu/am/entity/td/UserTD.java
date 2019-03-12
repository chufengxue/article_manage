package cn.wu.am.entity.td;

import cn.wu.am.entity.User;
import cn.wu.am.mconst.UserConst;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class UserTD {
    private Integer id;
    private String username;
    private String userType;
    private String userTypeView; //
    private int userStatus;
    private String userStatusView; //
    private Date createTime;
    private Integer articleCount;

    public UserTD(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.userType = user.getUserType();
        this.userStatus = user.getUserStatus();
        this.createTime = user.getCreateTime();

        if("admin".equals(user.getUserType())) {
            this.userTypeView = "管理员";
        } else {
            this.userTypeView = "普通用户";
        }

        if(user.getUserStatus() == UserConst.USER_STATUS_CHECKING) {
            this.userStatusView = "审核中";
        } else {
            this.userStatusView = "通过";
        }
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserTypeView() {
        return userTypeView;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public String getUserStatusView() {
        return userStatusView;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    @Override
    public String toString() {
        return "UserTD{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userType='" + userType + '\'' +
                ", userTypeView='" + userTypeView + '\'' +
                ", userStatus=" + userStatus +
                ", userStatusView='" + userStatusView + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
