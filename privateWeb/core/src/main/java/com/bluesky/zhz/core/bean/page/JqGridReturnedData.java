package com.bluesky.zhz.core.bean.page;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页数据返回值
 * @param <T>
 */
public class JqGridReturnedData<T> implements Serializable {

    private static final long serialVersionUID = -5912184952675882625L;

    /**
     * @param page
     * @param records
     * @param rows
     * @param total
     */
    public JqGridReturnedData(int page, int records, List<T> rows, int total) {
        super();
        this.page = page;
        this.records = records;
        this.rows = rows;
        this.total = total;
    }
    public JqGridReturnedData(PageInfo<T> params) {
        this.page = params.getPageNum();
        this.records = new Long(params.getTotal()).intValue();
        this.total = params.getPages();
        this.rows = params.getList();
    }
    /**
     * 当前第几页
     */
    @ApiModelProperty("当前第几页")
    private int page;

    /**
     * 数据总数
     */
    @ApiModelProperty("数据总数")
    private int records;

    /**
     * 返回的list
     */
    @ApiModelProperty("返回的list")
    private List<T> rows;

    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private int total;
    /**
     * 返回的额外数据，在自定义footer时使用
     */
    private Object userdata;

    public Object getUserdata() {
        return userdata;
    }

    public void setUserdata(Object userdata) {
        this.userdata = userdata;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public JqGridReturnedData() {
    }

}
