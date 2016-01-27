package com.quhuwai.common.author.domain;

/**
 * 泛型通用分页查询接口
 *
 * @param <T>
 */
public class Query<T> {

    final static int DEFAULT_PAGE_SIZE = 10;

    final static int DEFAULT_PAGE_NUM = 1;

    T module;

    boolean success;

    boolean isAsc;//order by是否以升序排序

    int pageSize = DEFAULT_PAGE_SIZE;

    int totalPage;

    int pageNum = DEFAULT_PAGE_NUM;

    int totalSize;

    private String orderBy;

    //是否包含limit语句
    private boolean hasLimit = true;


    public boolean hasLimit() {
        return hasLimit;
    }

    public void setHasLimit(boolean hasLimit) {
        this.hasLimit = hasLimit;
    }

    public T getModule() {
        return module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum < DEFAULT_PAGE_NUM ? DEFAULT_PAGE_NUM : pageNum;
    }

    public int getStart() {
        return pageNum > 0 ? (pageNum - 1) * pageSize + 1 : 0;
    }

    public String getOrderBy() {
        if (orderBy != null) {
            if (isAsc) {
                return orderBy + " ASC";
            }
            return orderBy + " DESC";
        } else {
            return null;
        }
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }
}
