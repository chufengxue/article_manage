package cn.wu.am.mconst;

public class ArticleConst {

    /**
     * 0 审核中, 文章还不能被公众查看到
     */
    public static final int STATUC_CHECKING = 0;

    /**
     * 1 审核通过，
     */
    public static final int STATUC_CHECKED = 1;

    /**
     * 2 审核不通过， 禁止发布
     */
    public static final int STATUC_INVALID = 2;
}
