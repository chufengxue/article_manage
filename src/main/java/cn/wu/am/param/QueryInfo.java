package cn.wu.am.param;

public class QueryInfo {
    private int currentPage;
    private int pageSize = 10;
    private int startIndex;

    public int getCurrentpage() {
        return currentPage;
    }
    public void setCurrentpage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPagesize() {
        return pageSize;
    }
    public void setPagesize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getStartindex() {
        this.startIndex = (currentPage -1) * pageSize;
        return startIndex;
    }
}
