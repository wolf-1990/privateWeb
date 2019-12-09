package com.bluesky.zhz.core.utils;


import com.bluesky.zhz.core.exception.ValidateException;
import com.bluesky.zhz.core.enums.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 工具类-日期处理
 * 
 * @author xx
 * @version 2.0
 * @since 2014年1月28日
 */
public class DateUtil {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	/** 格式 ：yyyy-MM-dd HH:mm:ss */
	public static final String DATEFORMAT_STR_001 = "yyyy-MM-dd HH:mm:ss";
	/** 格式 ：yyyy-MM-dd */
	public static final String DATEFORMAT_STR_002 = "yyyy-MM-dd";
	/** 格式 ：MM-dd */
	public static final String DATEFORMAT_STR_003 = "MM-dd";
	/** 格式 ：HH:mm:ss */
	public static final String DATEFORMAT_STR_004 = "HH:mm:ss";
	/** 格式 ：HH:mm:ss */
	public static final String DATEFORMAT_STR_005 = "HHmmss";
	/** 格式 ：HH:mm:ss:SSS */
	public static final String DATEFORMAT_STR_006 = "HH:mm:ss:SSS";
	/** 格式 ：yyyy-MM-dd HH:mm*/
	public static final String DATEFORMAT_STR_007 = "yyyy-MM-dd HH:mm";
	
	/** 格式 ：yyyyMMddHHmmss */
	public static final String DATEFORMAT_STR_011 = "yyyyMMddHHmmss";
	/** 格式 ：yyyyMMdd */
	public static final String DATEFORMAT_STR_012 = "yyyyMMdd";
	
	/** 格式 ：yyyy年MM月dd日 HH时mm分ss秒 */
	public static final String DATEFORMAT_STR_021 = "yyyy年MM月dd日 HH时mm分ss秒";
	/** 格式 ：yyyy年MM月dd日 */
	public static final String DATEFORMAT_STR_022 = "yyyy年MM月dd日";
	/** 格式 ：MM月dd日 hh:mm */
	public static final String DATEFORMAT_STR_023 = "MM月dd日 hh:mm";
	/** 格式 ：yyyy/MM */
	public static final String DATEFORMAT_STR_024 = "yyyy/MM";
	/** 格式 ：yyyy-MM-dd HH:mm:ss */
	public static final String DATEFORMAT_STR_025 = "yyyyMMddHHmmssSSS";
	/**
	 * 获得当前日期
	 * @return
	 */
	public static Date getNow() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return currDate;
	}

	/**
	 * 获取当前日期yyyymmdd
	 * @return
	 */
	public static String getDateNow() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return dateStr7(currDate);
	}
	
	
	/**
	 * 获取当前时间hhmmss
	 * @return
	 */
	public static String getTimeNow() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return dateStr9(currDate);
	}
	/**
	 * 日期转换为字符串 格式自定义
	 * 
	 * @param date
	 * @param f
	 * @return
	 */
	public static String dateStr(Date date, String f) {
		SimpleDateFormat format = new SimpleDateFormat(f);
		String str = format.format(date);
		return str;
	}
	
	public static String strToDateStr(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT_STR_011);
		Date date = new Date();
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr4(date);
	}

	/**
	 * 日期转换为字符串 MM月dd日 hh:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr(Date date) {
		return dateStr(date, DATEFORMAT_STR_023);
	}

	/**
	 * 日期转换为字符串 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr2(Date date) {
		return dateStr(date, DATEFORMAT_STR_002);
	}

	/**
	 * 获取当前日期前后日期 yyyy-MM-dd
	 * 负的是过去的日期 正的是未来的日期
	 */
	public static String getDate(int day){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		Date time=cal.getTime();
		return dateStr2(time);
	}

	/**
	 * 日期转换为字符串yyyy-MM-dd HH:mm
	 *
	 * @param day
	 * @return
	 */
	public static Date getDate2(int day){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		Date time=cal.getTime();
		String date = dateStr(time, DATEFORMAT_STR_007);
		return valueOf2(date);
	}


	/**
	 * yyyy年MM月dd日HH时mm分ss秒
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr5(Date date) {
		return dateStr(date, DATEFORMAT_STR_021);
	}

	/**
	 * yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr3(Date date) {
		return dateStr(date, DATEFORMAT_STR_011);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr4(Date date) {
		return dateStr(date, DATEFORMAT_STR_001);

	}

	/**
	 * yyyy年MM月dd日
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr6(Date date) {
		return dateStr(date, DATEFORMAT_STR_022);
	}

	/**
	 * yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr7(Date date) {
		return dateStr(date, DATEFORMAT_STR_012);
	}

	/**
	 * MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr8(Date date) {
		return dateStr(date, DATEFORMAT_STR_003);
	}
	
	/**
	 * HHmmdd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr9(Date date) {
		return dateStr(date, DATEFORMAT_STR_005);
	}
	
	/**
	 * 日期转换为字符串yyyy/MM 
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr10(Date date) {
		return dateStr(date, DATEFORMAT_STR_024);
	}

	/**
	 * 日期转换为字符串yyyyMMddHHmmssSSS
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr11(Date date) {
		return dateStr(date, DATEFORMAT_STR_025);

	}


	public static String dateStr12(Date date) {
		return dateStr(date, DATEFORMAT_STR_006);
	}
	
	/**
	 * 将时间戳转换为Date
	 * 
	 * @param times
	 * @return
	 */
	public static Date getDate(String times) {
		long time = Long.parseLong(times);
		return new Date(time * 1000);
	}

	public static String dateStr(String times) {
		return dateStr(getDate(times));
	}

	public static String dateStr2(String times) {
		return dateStr2(getDate(times));
	}

	public static String dateStr3(String times) {
		return dateStr3(getDate(times));
	}

	public static String dateStr4(String times) {
		return dateStr4(getDate(times));
	}

	public static String dateStr5(String times) {
		return dateStr5(getDate(times));
	}

	/**
	 * 将Date转换为时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long getTime(Date date) {
		return date.getTime() / 1000;
	}

	public static int getDay(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	
	
	/**
	 * 默认的valueOf 方法，格式化 yyyy-mm-dd HH:mm:ss
	 * @param str 
	 * @return
	 */
	public static Date valueOf(String str){
		return valueOf(str, DATEFORMAT_STR_001);
	}

	/**
	 * 默认的valueOf 方法，格式化 yyyy-mm-dd HH:mm
	 * @param str
	 * @return
	 */
	public static Date valueOf2(String str){
		return valueOf(str, DATEFORMAT_STR_007);
	}

	
	/**
	 * 默认的valueOfDay日期方法，格式化 yyyy-mm-dd
	 * @param str
	 * @return
	 */
	public static Date valueOfDay(String str){
		return valueOf(str, DATEFORMAT_STR_002);
	}
	
	
	/**
	 * 
	 * 自定义format格式化字符串为date
	 * @param str 要格式化的字符串
	 * @param dateFormatStr 
	 * @return
	 */
	public static Date valueOf(String str, String dateFormatStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStr);
		Date strtoDate = null;
		try {
			strtoDate = formatter.parse(str);
		} catch (ParseException e) {
			throw new ValidateException(ErrorCode.BUSSINESS_ERROR_0002, "日期格式不正确");
		}
		return strtoDate;
	}
	

	/**
	 * @author lijie
	 * @param Begin
	 * @param end 传入开始时间 和 结束时间 格式如：2012-09-07
	 * @return 返回Map 获取相隔多少年 get("YEAR")及为俩个时间年只差，月 天，类推 Key ： YEAR MONTH DAY 如果开始时间 晚于 结束时间 return null；
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getApartTime(String Begin, String end) {
		String[] temp = Begin.split("-");
		String[] temp2 = end.split("-");
		if (temp.length > 1 && temp2.length > 1) {
			Calendar ends = Calendar.getInstance();
			Calendar begin = Calendar.getInstance();

			begin.set(StringUtil.toInt(temp[0]), StringUtil.toInt(temp[1]), StringUtil.toInt(temp[2]));
			ends.set(StringUtil.toInt(temp2[0]), StringUtil.toInt(temp2[1]), StringUtil.toInt(temp2[2]));
			if (begin.compareTo(ends) < 0) {
				Map map = new HashMap(16);
				ends.add(Calendar.YEAR, -StringUtil.toInt(temp[0]));
				ends.add(Calendar.MONTH, -StringUtil.toInt(temp[1]));
				ends.add(Calendar.DATE, -StringUtil.toInt(temp[2]));
				map.put("YEAR", ends.get(Calendar.YEAR));
				map.put("MONTH", ends.get(Calendar.MONTH) + 1);
				map.put("DAY", ends.get(Calendar.DATE));
				return map;
			}
		}
		return null;
	}

	/**
	 * 前/后?分钟
	 * 
	 * @param d
	 * @param minute
	 * @return
	 */
	public static Date rollMinute(Date d, int minute) {
		return new Date(d.getTime() + minute * 60 * 1000);
	}

	/**
	 * 前/后?天
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date rollDay(Date d, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}

	/**
	 * 前/后?月
	 * 
	 * @param d
	 * @param mon
	 * @return
	 */
	public static Date rollMon(Date d, int mon) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MONTH, mon);
		return cal.getTime();
	}

	/**
	 * 前/后?年
	 * 
	 * @param d
	 * @param year
	 * @return
	 */
	public static Date rollYear(Date d, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static Date rollDate(Date d, int year, int mon, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, mon);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}

	/**
	 * 获取当前时间-时间戳字符串
	 * 
	 * @return
	 */
	public static String getNowTimeStr() {
		String str = Long.toString(System.currentTimeMillis() / 1000);
		return str;
	}

	/**
	 * 获取当前时间-时间戳
	 * 
	 * @return
	 */
	public static int getNowTime() {
		return Integer.parseInt(StringUtil.isNull(System.currentTimeMillis() / 1000));
	}

	/**
	 * 将Date转换为时间戳
	 * 
	 * @param time
	 * @return
	 */
	public static String getTimeStr(Date time) {
		long date = time.getTime();
		String str = Long.toString(date / 1000);
		return str;
	}

	public static String getTimeStr(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		String str = DateUtil.getTimeStr(date);
		return str;
	}

	public static String rollMonth(Date addTime, String time_limit) {
		Date t = DateUtil.rollDate(addTime, 0, StringUtil.toInt(time_limit), 0);
		return t.getTime() / 1000 + "";
	}

	public static String rollDay(Date addTime, String time_limit_day) {
		Date t = DateUtil.rollDate(addTime, 0, 0, StringUtil.toInt(time_limit_day));
		return t.getTime() / 1000 + "";
	}

	public static Date getIntegralTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getLastIntegralTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getLastSecIntegralTime(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	
	public static long getTime(String format) {
		long t = 0;
		if (StringUtil.isBlank(format)) {
			return t;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(format);
			t = date.getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t;
	}

	// 获取本周日的日期
	@SuppressWarnings("unused")
	public static String getCurrentWeekday() {
		int weeks = 0;
		int mondayPlus = DateUtil.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();

		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获得本周一的日期
	@SuppressWarnings("unused")
	public static String getMondayOFWeek() {
		int weeks = 0;
		int mondayPlus = DateUtil.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获取当前月第一天：
	public static String getFirstDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = format.format(c.getTime());
		return first;
	}

	// 获取当月最后一天
	public static String getLastDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		return last;
	}

	/**
	 * 日期月份处理
	 * 
	 * @param d 时间
	 * @param month 相加的月份，正数则加，负数则减
	 * @return
	 */
	public static Date timeMonthManage(Date d, int month) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(d);
		rightNow.add(Calendar.MONTH, month);
		return rightNow.getTime();
	}

	/**
	 * 获取指定年月的最后一天
	 * 
	 * @param year_time 指定年
	 * @param month_time 指定月
	 * @return
	 */
	public static Date monthLastDay(int year_time, int month_time) {
		Calendar cal = Calendar.getInstance();
		cal.set(year_time, month_time, 0, 23, 59, 59);
		return cal.getTime();
	}

	/**
	 * 获取指定年月的第一天
	 * 
	 * @param year_time 指定年
	 * @param month_time 指定月
	 * @return
	 */
	public static Date monthFirstDay(int year_time, int month_time) {
		Calendar cal = Calendar.getInstance();
		cal.set(year_time, month_time - 1, 1, 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定时间月的第一天
	 * 
	 * @param d 指定时间，为空代表当前时间
	 * @return
	 */
	public static Date currMonthFirstDay(Date d) {
		Calendar cal = Calendar.getInstance();
		if (d != null)
			cal.setTime(d);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定时间月的最后一天
	 * 
	 * @param d 指定时间，为空代表当前时间
	 * @return
	 */
	public static Date currMonthLastDay(Date d) {
		Calendar cal = Calendar.getInstance();
		if (d != null)
			cal.setTime(d);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
		return cal.getTime();
	}

	/**
	 * 获取指定时间的年
	 * 
	 * @param date 指定时间
	 * @return
	 */
	public static int getTimeYear(Date date) {
		if (date == null)
			date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取指定时间的月
	 * 
	 * @param date 指定时间
	 * @return
	 */
	public static int getTimeMonth(Date date) {
		if (date == null)
			date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取指定时间的天
	 * 
	 * @param date 指定时间
	 * @return
	 */
	public static int getTimeDay(Date date) {
		if (date == null)
			date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE);
	}

	/**
	 * 获取当天00:00:00
	 * @param d
	 * @return
	 */
	public static Date getFirstSecIntegralTime(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 
	 * 指定天数 d + day天后的结束时间
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date rollDayEndTime(Date d, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
		return cal.getTime();
	}
	
	/**
	 * 
	 * 指定天数 d + day天后的开始时间
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date rollDayStartTime(Date d, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取指定时间天的结束时间
	 * 
	 * @param d
	 * @return
	 */
	public static Date getDayEndTime(long d) {
		Date day = null;
		if (d <= 0){
			day = new Date();
		} else{
			day = new Date(d * 1000);
		}
		Calendar cal = Calendar.getInstance();
		if (day != null){
			cal.setTimeInMillis(day.getTime());
		}
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
		return cal.getTime();
	}

	/**
	 * 获取指定时间天的开始时间
	 * 
	 * @param d
	 * @return
	 */
	public static Date getDayStartTime(long d) {
		Date day = null;
		if (d <= 0){
			day = new Date();
		} else {
			day = new Date(d * 1000);
		}
		Calendar cal = Calendar.getInstance();
		if (day != null) {
			cal.setTimeInMillis(day.getTime());
		}
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 获取19位的格式时间
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateByFullDateStr(String dateStr) {
		if (StringUtil.isBlank(dateStr)) {
			return null;
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    /** 
     * 计算两个日期之间相差的天数 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int daysBetween(Date date1, Date date2){
		DateFormat sdf=new SimpleDateFormat(DATEFORMAT_STR_012);
		Calendar cal = Calendar.getInstance();
		try {
			Date d1 = sdf.parse(DateUtil.dateStr7(date1));
			Date d2 = sdf.parse(DateUtil.dateStr7(date2));
			cal.setTime(d1);
			long time1 = cal.getTimeInMillis();
			cal.setTime(d2);
			long time2 = cal.getTimeInMillis();
			return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
       
    /** 
     * 计算两个日期之间相差的小时数 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int hoursBetween(Date date1, Date date2) {
     
        DateFormat sdf=new SimpleDateFormat(DATEFORMAT_STR_012);
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(DateUtil.dateStr7(date1));
            Date d2 = sdf.parse(DateUtil.dateStr7(date2));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / 3600000L));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /** 
     * 计算两个日期之间相差的秒数
     * @param date1 
     * @param date2 
     * @return date2-date1
     */  
    public static int secBetween(Date date1, Date date2) {
    	long time1 = date1.getTime();
    	long time2 = date2.getTime();
    	return Integer.parseInt(String.valueOf((time2 - time1) / 1000L));
    }

	/**
	 * 比较两个日期大小,前者大返回true
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean dayCompare(Date date1, Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if (time1 - time2 > 0) {
			return true;
		} else {
			return false;
		}
	}
    /**
     * 比较两个日期大小,前者大返回true
     * @param date1
     * @param date2
     * @return
     */
    public static boolean dayCompare(String date1,String date2){
    	 DateFormat sdf=new SimpleDateFormat(DATEFORMAT_STR_012);
         Calendar cal = Calendar.getInstance();
         try {
             Date d1 = sdf.parse(date1);
             Date d2 = sdf.parse(date2);
             cal.setTime(d1);
             long time1 = cal.getTimeInMillis();
             cal.setTime(d2);
             long time2 = cal.getTimeInMillis();
             if(time1-time2>0){
            	 return true;
             }else{
            	 return false;
             }
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return false;
    }

    public static String transforTime(long time){
    	Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.add(Calendar.HOUR, -8);
		return dateStr12(cal.getTime());
    }
    
    /**
   	 * 转换会计日
   	 * @param str
   	 * @return
   	 */
   	public static String valueofAccount(String str){
   		return toAccountDate(valueOfDay(str));
   	}
    
    
    /**
	 * 转换会计日
	 * @param str
	 * @return
	 */
	public static Date valueAccountDate(String str){
		return valueOf(str, DATEFORMAT_STR_012);
	}

	/**
	 * 转换会计日
	 * @param date
	 * @return
     */
	public static String toAccountDate(Date date){
		return dateStr(date, DATEFORMAT_STR_012);
	}
	
	/**
	 * 前/后?天  格式：yyyyMMdd 
	 * @param d
	 * @param day
	 * @return
	 */
	public static String rollAccountDate(String d, int day) {
		Date date = valueAccountDate(d);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return toAccountDate(cal.getTime());
	}
    
	/**
	 * 前/后?个月 yyyy/MM
	 * @param d
	 * @param mon
	 * @return
	 */
	public static String rollAccountMon(String d, int mon) {
		Date date = valueAccountDate(d);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, mon);
		return dateStr10(cal.getTime());
	}
	
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param daysOfWeek
	 * @return
	 */
	public static List<Date> getDaysByDaysOfWeek(Date startDate,Date endDate,int daysOfWeek){
		List<Date> days = new ArrayList<Date>();
		Calendar cd = Calendar.getInstance();
		cd.setTime(startDate);
		int dow = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		cd.add(Calendar.DAY_OF_YEAR, dow>daysOfWeek?daysOfWeek+7-dow:daysOfWeek-dow);
		int daysCount = daysBetween(startDate, endDate);
		for(int i=0;i<daysCount/7;i++){
			days.add(cd.getTime());
			cd.add(Calendar.DAY_OF_YEAR, 7);
		}
		days.add(cd.getTime());
		return days;
	}

	public static String getLocalDateTimeDiffStr(LocalDateTime startTime,LocalDateTime endTime){
		if(startTime.isAfter(endTime)){
			return null;
		}
		java.time.Duration duration = java.time.Duration.between(startTime,endTime);
		long hours = duration.toHours();
		long minutes = duration.toMinutes() - (hours * 60);
		long seconds = (duration.toMillis() / 1000) - (hours * 3600) - (minutes * 60);
		String tempHours = hours<10?"0"+hours+"":hours+"";
		String tempMinutes = minutes<10?"0"+minutes+"":minutes+"";
		String tempSeconds = seconds<10?"0"+seconds+"":seconds+"";
		return tempHours+":"+tempMinutes+":"+tempSeconds;
	}

}
