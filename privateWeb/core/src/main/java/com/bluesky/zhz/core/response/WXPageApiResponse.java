package com.bluesky.zhz.core.response;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class WXPageApiResponse<T> extends WXApiResponse<Object> implements Serializable{

	private static final long serialVersionUID = 6265844595020392458L;

	protected WXPageApiResponse(String code, String message,List<T> data, PageInfo<?> pageInfo) {
        super(code, message, data);
        this.page = pageInfo.getPageNum();
        this.records = new Long(pageInfo.getTotal()).intValue();
        this.total = pageInfo.getPages();
    }

    /**
     * 创建请求成功时返回结果对象。
     *
     * @return
     */
    public static <T> WXPageApiResponse<T> pageSuccess(PageInfo<T> data) {
        PageInfo<T> pageData = null;
        if (data instanceof PageInfo){
             pageData = (PageInfo<T>)data;
        }

        return new WXPageApiResponse<T>(SUCCESS, "", pageData.getList(), pageData);
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
     * 总页数
     */
    @ApiModelProperty("总页数")
    private int total;

    /**
     * 返回的额外数据，在自定义footer时使用
     */
    @ApiModelProperty("返回的额外数据，在自定义footer时使用")
    private Object userdata;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getUserdata() {
        return userdata;
    }

    public void setUserdata(Object userdata) {
        this.userdata = userdata;
    }
}
