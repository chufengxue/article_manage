package cn.wu.am.mconst;

public class UserConst {
    // 用户类型
    public static final String USER_TYPE_ADMIN = "admin";
    public static final String USER_TYPE_COMMON = "common";

    // 用户的状态
    /**
     * 0 审核中， 不能发文章
     */
    public static final int USER_STATUS_CHECKING = 0;
    /**
     * 1 审核通过，可以发文章
     */
    public static final int USER_STATUS_CHECKED = 1;

    public static final String LOGIN_USER = "login_user";
    public static final String LOGIN_TYPE = "login_type";

}
