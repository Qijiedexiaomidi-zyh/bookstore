package com.abc.bookstore.utils;

public class PageModel {
    //当前页面的页码
    private int pageIndex;
    //页面大小
    private int pageSize = 3;
    //总页码数
    private int totalSize;
    //总记录数
    private int recordCount;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        if(recordCount%pageSize==0){
            totalSize=recordCount/pageSize;
        }else {
            totalSize=recordCount/pageSize+1;
        }
        return totalSize;
    }

//    public void setTotalSize(int totalSize) {
//        this.totalSize = totalSize;
//    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * 查询当前页的起始索引，也就是sql语句中limit后面的第一个参数
     * @return
     */
    public int getLimitFirstParam(){
        return (this.getPageIndex()-1)*this.pageSize;
    }
}
