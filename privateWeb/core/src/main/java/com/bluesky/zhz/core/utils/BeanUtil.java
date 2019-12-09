package com.bluesky.zhz.core.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

import com.bluesky.zhz.core.bean.user.CurrentUser;
import com.bluesky.zhz.core.enums.CommonEnum;


/**
 * 空值设置为空字符串或者空字符串或者当前时间
 * @author Administrator
 *
 */
public class BeanUtil extends org.springframework.beans.BeanUtils{
	

	public static void resetNullValue(Object source){
		Class<?> cla = source.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(cla);
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null &&
							ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							if(value == null){
								String typeName = targetPd.getPropertyType().getName();
								if("java.lang.Integer".equals(typeName)){
									value = 0;
								}else if("java.lang.Double".equals(typeName)){
									value = 0D;
								}else if("java.lang.Float".equals(typeName)){
									value = 0F;
								}else if("java.lang.String".equals(typeName)){
									value = "";
								}else if("java.util.Date".equals(typeName)){
									value = new Date();
								}else if("java.math.BigDecimal".equals(typeName)){
									value = BigDecimal.ZERO;
								}
								writeMethod.invoke(source, value);
							}
						}
						catch (Throwable ex) {
							throw new FatalBeanException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}
	
	private static Map<String, String> insertChangeMap = null;
	private static Map<String, String> updateChangeMap = null;
	static{
		insertChangeMap = new HashMap<String, String>();
		insertChangeMap.put("creatorId", "id");
		insertChangeMap.put("creatorName", "fullName");
		insertChangeMap.put("creationTime", "");
		insertChangeMap.put("lastoperatorId", "id");
		insertChangeMap.put("lastoperatorName", "fullName");
		insertChangeMap.put("lastoperationTime", "");
		insertChangeMap.put("status", "");
		insertChangeMap.put("isDelete", "");
		
		updateChangeMap = new HashMap<String, String>();
		updateChangeMap.put("lastoperatorId", "id");
		updateChangeMap.put("lastoperatorName", "fullName");
		updateChangeMap.put("lastoperationTime", "");
	}
	

	public static <T> void copyInsert(T t , CurrentUser user ) {
		copy(t, user, insertChangeMap);
	}
	
	public static <T> void copyUpdate(T t , CurrentUser user ) {
		copy(t, user, updateChangeMap);
	}
	
	public static <T> void copy(T t , CurrentUser user, Map<String, String> map) {
		Field[] fields = t.getClass().getDeclaredFields();
		
		String fieldName = null;
		String fieldTypeName = null;
		try {
			for( Field f : fields ){
				fieldName = f.getName();
				fieldTypeName = f.getType().getName();
				if( map.containsKey(fieldName) ){
					f.setAccessible(true);
					if( fieldTypeName.equals("java.time.LocalDateTime") ){
						f.set(t, LocalDateTime.now());
					} else {
						if( fieldName.equals("status") ){
							f.set(t, CommonEnum.State.YES.getCode());
						}else if( fieldName.equals("isDelete") ){
							f.set(t, CommonEnum.IsDelete.NORMAL.getCode());
						}else{
							//获取user里  key对应value的属性值
							String attrName = map.get(fieldName);
							String getName = attrName.substring(0,1).toUpperCase() + attrName.substring(1);
							Method m = user.getClass().getMethod("get" + getName);
							f.set(t, m.invoke(user).toString());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
