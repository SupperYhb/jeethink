package com.jeethink.requestutil.entity;

import java.util.List;

public class casetotalentity {
    /** 当前页 */
    private Integer currentPage;
    /** 每页条数 */
    private Integer pageSize;
    /** 总页数 */
    private Integer totalpage;
    /** 总条数 */
    private Integer totalSize;
    /** 案卷集合 */
    private List<kdcaseentity> list;

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setList(List<kdcaseentity> list) {
        this.list = list;
    }

    public List<kdcaseentity> getList() {
        return list;
    }
}
