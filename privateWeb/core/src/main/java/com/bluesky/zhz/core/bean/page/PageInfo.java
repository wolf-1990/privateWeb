package com.bluesky.zhz.core.bean.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页参数
 */
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 904939684522363424L;

    @ApiModelProperty(value = "当前第几页", notes = "默认值为1", example = "1")
    @Min(1)
    @NotNull
    private Integer page = 1;
    @ApiModelProperty(value = "每页显示多少条数据", notes = "默认值为10", example = "10")
    @Min(1)
    @NotNull
    private Integer rows = 10;
    @JsonIgnore
    @ApiModelProperty(value = "开始条数", notes = "不是由前端传，而是程序计算")
    private Integer startRow = 0;

    public Integer getPage() {
        return page == null ? 1 : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows == null ? 10 : rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getStartRow() {
        if (page == null || page < 1 || rows == null || rows < 1) {
            return 0;
        }

        return (page - 1) * rows;
    }
}
