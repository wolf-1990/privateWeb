package com.bluesky.zhz.core.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class BeanMap {
	
	/**
	 * 对象字段转换为map集合
	 *   比如 user 对象
	 *      {"name":"zhangsan","id":11}
	 * @author 孙志勇 2018年5月22日 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	 public static Map<String, Object> objectToMap(Object obj) throws Exception {    
	        if(obj == null)  
	            return null;      
	  
	        Map<String, Object> map = new HashMap<String, Object>();   
	  
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
	        for (PropertyDescriptor property : propertyDescriptors) {    
	            String key = property.getName();    
	            if (key.compareToIgnoreCase("class") == 0) {   
	                continue;  
	            }  
	            Method getter = property.getReadMethod();  
	            Object value = getter!=null ? getter.invoke(obj) : null;  
	            map.put(key, value);  
	        }    
	  
	        return map;  
	    }    
	 
	 
	 /**
		 * 对象字段转换为map集合
		 *   key 为String，即字段名
		 *   value 为值，所有转换为string（传入对象，除了基本类型和对应的包装类，不要有其他类型，因为最终要转换为String穿到cc）
		 * @author 孙志勇 2018年5月22日 
		 * @param obj
		 * @return
		 * @throws Exception
		 */
		 public static Map<String, String> objectToMapForCC(Object obj){    
		        if(obj == null)  
		            return null;      
		  
		        Map<String, String> map = new HashMap<String, String>();   
		        
		        BeanInfo beanInfo;
				try {
					beanInfo = Introspector.getBeanInfo(obj.getClass());
					PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
			        for (PropertyDescriptor property : propertyDescriptors) {    
			            String key = property.getName();    
			            if (key.compareToIgnoreCase("class") == 0) {   
			                continue;  
			            }  
			            Method getter = property.getReadMethod();  
			            Object value = getter!=null ? getter.invoke(obj) : null;  
			            
			            if(value != null){
			            	 map.put(key, value.toString());  
			            }else{
			            	 map.put(key, null);  
			            }
			        }    
				} catch (Exception e) {
					log.error(obj+"转换map异常", e);
					e.printStackTrace();
				}    
		       
		        return map;  
		    }    
}

