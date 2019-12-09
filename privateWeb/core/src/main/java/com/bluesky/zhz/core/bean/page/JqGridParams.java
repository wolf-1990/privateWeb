package com.bluesky.zhz.core.bean.page;

import com.bluesky.zhz.core.constants.ValidateNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 
 *
 */
@ApiModel("分页相关数据")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JqGridParams implements Serializable {

	private static final long serialVersionUID = 2296722197188158072L;

	/**
	 * 当前第几页
	 */
	@Max(message = ValidateNumber.PAGE_NUMBER_MESSAGE, value = ValidateNumber.PAGE_NUMBER_MAX)
	@Min(message = ValidateNumber.PAGE_NUMBER_MESSAGE, value = ValidateNumber.PAGE_NUMBER_MIN)
	@ApiModelProperty(value = "当前第几页", example = "0")
	private int page = 1;

	/**
	 * 显示多少条数据
	 */
	@Max(message = ValidateNumber.PAGE_COUNT_MESSAGE, value = ValidateNumber.PAGE_COUNT_MAX)
	@Min(message = ValidateNumber.PAGE_COUNT_MESSAGE, value = ValidateNumber.PAGE_COUNT_MIN)
	@ApiModelProperty(value = "每页显示多少条数据", example = "2")
	private int rows = 20;

	/**
	 * 排序列名
	 */
	@JsonIgnore
	private String sidx;

	/**
	 * 排序规则 asc or desc
	 */
	@JsonIgnore
	private String sord;

	/**
	 * 得到firstresult hibernate使用
	 *
	 * @return
	 */
	@JsonIgnore
	public int getFirstResult() {
		return (getPage() - 1) * getRows();
	}

	/**
	 * 计算总共有多少页
	 * 
	 * @param records
	 * @return
	 */
	public int getTotal(int records) {
		return records % getRows() == 0 ? records / getRows() : records / getRows() + 1;
	}

	/**
	 * 设置默认排序
	 * 
	 * @param sidx
	 * @param sord
	 */
	public void setDefaultOrder(String sidx, String sord) {
		if (StringUtils.isEmpty(this.sidx)) {
			this.sidx = sidx;
			this.sord = sord;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}
}
