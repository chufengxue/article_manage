package cn.wu.am.param;

import java.util.List;

public class PageBean<T> {
    private List<T> data;
    private int totalRecord;
    private int pageSize;
    private int totalPage; // ?
    private int currentPage;
    private int previousPage;
    private int nextPage;
    private int[] pageBar;

    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
    public int getTotalrecord() {
        return totalRecord;
    }
    public void setTotalrecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    public int getPagesize() {
        return pageSize;
    }
    public void setPagesize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalpage() {
        // 100 5 20
        // 101 5 21
        // 99  5 20
        if(totalRecord % pageSize == 0) {
            totalPage = totalRecord / pageSize;
        } else {
            totalPage = totalRecord / pageSize + 1;
        }
        return totalPage;
    }

    public int getCurrentpage() {
        return currentPage;
    }
    public void setCurrentpage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPreviouspage() {
        if(currentPage - 1 <1) {
            previousPage = 1;
        } else {
            previousPage = currentPage - 1;
        }
        return previousPage;
    }

    public int getNextpage() {
        if(currentPage + 1 > getTotalpage()) {
            nextPage = getTotalpage();
        } else {
            nextPage = currentPage + 1;
        }
        return nextPage;
    }

    public int[] getPagebar() {
        if(getTotalpage()<=1) {
            return null;
        }
        int size = 10; // 默认显示 10 个页码
        int startpage = 1;
        int endpage = 10;
        if(getTotalpage() < size) {
            startpage = 1;
            endpage = getTotalpage();
        } else {
            startpage = currentPage - 4;
            endpage = currentPage + 5;
            if(startpage < 1) {
                startpage = 1;
                endpage = size;
            }
            if(endpage > getTotalpage()) {
                endpage = getTotalpage();
                startpage = endpage - size + 1;
            }
        }
        size = endpage - startpage + 1;
        int[] mPageBar = new int[size];
        int index = 0;
        for(int i=startpage; i<=endpage;i++) {
            mPageBar[index++] = i;
        }
        this.pageBar = mPageBar;
        return pageBar;
    }

}

