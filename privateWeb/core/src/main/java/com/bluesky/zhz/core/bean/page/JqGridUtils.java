package com.bluesky.zhz.core.bean.page;

import com.bluesky.zhz.core.utils.StringUtil;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 */
public class JqGridUtils {

	/**
	 * 分页对象转换为JqGridReturnedData
	 */
	public static <T> JqGridReturnedData<T> transToJqGridReturnedData(List<T> list) {
		PageInfo<T> pages = new PageInfo<>(list);
		JqGridReturnedData<T> returnedData = new JqGridReturnedData<T>();
		returnedData.setPage(pages.getPageNum());
		returnedData.setRecords(((Long) pages.getTotal()).intValue());
		returnedData.setTotal(pages.getPages());
		returnedData.setRows(list);
		return returnedData;
	}

	public static <T> JqGridReturnedData<T> transToJqGridReturnedData(List<T> list, Integer pageNum){
		PageInfo<T> pages = new PageInfo<>(list);
		JqGridReturnedData<T> returnedData = new JqGridReturnedData<T>();
		returnedData.setPage(pageNum);
		returnedData.setRecords(((Long) pages.getTotal()).intValue());
		returnedData.setTotal(pages.getPages());
		returnedData.setRows(list);
		return returnedData;
	}

	public static <T> JqGridReturnedData<T> transToJqGridReturnedData(List<T> list, Integer pageNum,Integer records){
		PageInfo<T> pages = new PageInfo<>(list);
		JqGridReturnedData<T> returnedData = new JqGridReturnedData<T>();
		returnedData.setPage(pageNum);
		returnedData.setRecords(records);
		returnedData.setTotal(pages.getPages());
		returnedData.setRows(list);
		return returnedData;
	}
	
	/**
	 * 分页对象转换成map
	 */
    public static <T> Map<String, Object> transformToMap(List<T> list){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        JqGridUtils.returnData(resultMap, transToJqGridReturnedData(list));
        return resultMap;
    }
	
	/**
	 * 返回给客户端的数据
	 *
	 * @param map
	 * @param returnedData
	 */
	public static void returnData(Map<String, Object> map, JqGridReturnedData<?> returnedData) {
		map.put("page", returnedData.getPage());
		map.put("records", returnedData.getRecords());
		if( StringUtil.isBlank(returnedData.getRows()) ){
			map.put("rows", new ArrayList<>());
		}else{
			map.put("rows", returnedData.getRows());
		}

		map.put("total", returnedData.getTotal());
		map.put("userdata", returnedData.getUserdata());
	}
	
}
