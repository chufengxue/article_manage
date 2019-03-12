package cn.wu.am.param;

import java.util.List;

public class QueryResult<T> {
    private List<T> data;
    private int totalrecord;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(int totalrecord) {
        this.totalrecord = totalrecord;
    }
}
