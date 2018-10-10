package com.taofeng.webcast.common.entity;

import com.taofeng.webcast.common.query.BaseQuery;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>后端与前端的交互,分页处理的返回值</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 下午3:40
 * @since V1.0
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> result;

    private int pageNo = BaseQuery.DEFAULT_PAGE;

    private int totalCount; // 总的数目

    private int pageSize = BaseQuery.DEFAULT_PAGE_SIZE; //每一页数目

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        if(pageNo < 1){
            pageNo = BaseQuery.DEFAULT_PAGE;
        }
        return totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageResult(){

    }

    public PageResult(int size, int no, int count){
        pageSize = size;
        pageNo = no;
        totalCount = count;
        result = new LinkedList<>();
    }

    public PageResult(int size, int no, int count, List<T> xList){
        pageSize = size;
        pageNo = no;
        totalCount = count;
        result = xList;
    }
}
