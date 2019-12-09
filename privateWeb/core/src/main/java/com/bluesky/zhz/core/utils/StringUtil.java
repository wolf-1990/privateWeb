package com.bluesky.zhz.core.utils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil extends StringUtils {

	/**
	 * 字符串空处理，去除首尾空格 如果str为null，返回"",否则返回str
	 * 
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 将对象转为字符串
	 * 
	 * @param o
	 * @return
	 */
	public static String isNull(Object o) {
		if (o == null) {
			return "";
		}
		String str = "";
		if (o instanceof String) {
			str = (String) o;
		} else {
			str = o.toString();
		}
		return str.trim();
	}

	/**
	 * 检验是否为空或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return StringUtil.isNull(str).equals("");
	}

	public static boolean isBlank(Object o) {
		return StringUtil.isNull(o).equals("");
	}

	/**
	 * 检验是否非空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isNull(str).equals("");
	}

	/**
	 * 检验手机号
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		phone = isNull(phone);
		Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher matcher = regex.matcher(phone);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 校验是否全中文，返回true 表示是 反之为否
	 * 
	 * @param realname
	 * @return
	 */
	public static boolean isChinese(String realname) {
		realname = isNull(realname);
		Pattern regex = Pattern.compile("[\\u4e00-\\u9fa5]{2,10}");
		Matcher matcher = regex.matcher(realname);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 校验邮箱格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		email = isNull(email);
		Pattern regex = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 校验身份证号码
	 * 
	 * @param cardId
	 * @return
	 */
	public static boolean isCard(String cardId) {
		cardId = isNull(cardId);
		// 身份证正则表达式(15位)
		Pattern isIDCard1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
		// 身份证正则表达式(18位)
		Pattern isIDCard2 = Pattern
				.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
		Matcher matcher1 = isIDCard1.matcher(cardId);
		Matcher matcher2 = isIDCard2.matcher(cardId);
		boolean isMatched = matcher1.matches() || matcher2.matches();
		return isMatched;
	}

	/**
	 * 根据身份证Id获取性别
	 * 
	 * @param cardId
	 * @return
	 */
	public static int getSex(String cardId) {
		int sexNum = 0;
		// 15位的最后一位代表性别，18位的第17位代表性别，奇数为男，偶数为女
		if (cardId.length() == 15) {
			sexNum = cardId.charAt(cardId.length() - 1);
		} else {
			sexNum = cardId.charAt(cardId.length() - 2);
		}

		if (sexNum % 2 == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 根据身份证Id获取出生年月日
	 * 
	 * @param cardId
	 * @return
	 */
	public static String getBirthday(String cardId) {
		String birthday = "";
		// 15位7-12位出生年月日 如590101 代表 19590101; 18位7-14位出生年月日如 19590101
		if (cardId.length() == 15) {
			birthday = "19" + cardId.substring(6, 12);
		} else {
			birthday = cardId.substring(6, 14);
		}
		return birthday;
	}

	/**
	 * 判断字符串是否为整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (isBlank(str)) {
			return false;
		}
		Pattern regex = Pattern.compile("\\d*");
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isBlank(str)) {
			return false;
		}
		Pattern regex = Pattern.compile("(-)?\\d*(.\\d*)?");
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String firstCharUpperCase(String s) {
		StringBuffer sb = new StringBuffer(s.substring(0, 1).toUpperCase());
		sb.append(s.substring(1, s.length()));
		return sb.toString();
	}

	/**
	 * 隐藏头几位
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String hideFirstChar(String str, int len) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		if (str.length() <= len) {
			for (int i = 0; i < chars.length; i++) {
				chars[i] = '*';
			}
		} else {
			for (int i = 0; i < 1; i++) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	/**
	 * 隐藏末几位
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String hideLastChar(String str, int len) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		if (str.length() <= len) {
			return hideLastChar(str, str.length() - 1);
		} else {
			for (int i = chars.length - 1; i > chars.length - len - 1; i--) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	/**
	 * 指定起始位置字符串隐藏
	 * 
	 * @param str
	 * @param index1
	 * @param index2
	 * @return
	 */
	public static String hideStr(String str, int index1, int index2) {
		if (str == null)
			return null;
		String str1 = str.substring(index1, index2);
		String str2 = str.substring(index2);
		String str3 = "";
		if (index1 > 0) {
			str1 = str.substring(0, index1);
			str2 = str.substring(index1, index2);
			str3 = str.substring(index2);
		}
		char[] chars = str2.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			chars[i] = '*';
		}
		str2 = new String(chars);
		String str4 = str1 + str2 + str3;
		return str4;
	}

	/**
	 * Object数组拼接为字符串
	 * 
	 * @param args
	 * @return
	 */
	public static String contact(Object[] args) {
		if (args == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * Long数组拼接为字符串
	 * 
	 * @param args
	 * @return
	 */
	public static String contact(long[] args) {
		if (args == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * 数字数组拼接为字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String array2Str(int[] arr) {
		if (arr == null) {
			return "";
		}
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			s.append(arr[i]);
			if (i < arr.length - 1) {
				s.append(",");
			}
		}
		return s.toString();
	}

	/**
	 * 字符串数组转换为数字数组
	 * 
	 * @param strarr
	 * @return
	 */
	public static int[] strarr2intarr(String[] strarr) {
		int[] result = new int[strarr.length];
		for (int i = 0; i < strarr.length; i++) {
			result[i] = Integer.parseInt(strarr[i]);
		}
		return result;
	}

	// public static String fillTemplet(String template, Map<String, Object>
	// sendData) {
	// // 模板中的'是非法字符，会导致无法提交，所以页面上用`代替
	// template = template.replace('`', '\'');
	// try {
	// return FreemarkerUtil.renderTemplate(template, sendData);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return "";
	// }

	/**
	 * 大写字母转成“_”+小写 驼峰命名转换为下划线命名
	 * 
	 * @param str
	 * @return
	 */
	public static String toUnderline(String str) {
		char[] charArr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		sb.append(charArr[0]);
		for (int i = 1; i < charArr.length; i++) {
			if (charArr[i] >= 'A' && charArr[i] <= 'Z') {
				sb.append('_').append(charArr[i]);
			} else {
				sb.append(charArr[i]);
			}
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * 下划线改成驼峰样子
	 * 
	 * @param str
	 * @return
	 */
	public static String clearUnderline(String str) {
		char[] charArr = StringUtil.isNull(str).toLowerCase().toCharArray();
		StringBuffer sb = new StringBuffer();
		sb.append(charArr[0]);
		boolean isClear = false;
		for (int i = 1; i < charArr.length; i++) {
			if (charArr[i] == '_') {
				isClear = true;
				continue;
			}
			if (isClear && (charArr[i] >= 'a' && charArr[i] <= 'z')) {
				char c = (char) (charArr[i] - 32);
				sb.append(c);
				isClear = false;
			} else {
				sb.append(charArr[i]);
			}

		}
		return sb.toString();
	}

	/**
	 * String to int
	 * 
	 * @param str
	 * @return
	 */
	public static int toInt(String str) {
		if (StringUtil.isBlank(str))
			return 0;
		int ret = 0;
		try {
			ret = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}

	public static byte toByte(String str) {
		if (StringUtil.isBlank(str))
			return 0;
		byte ret = 0;
		try {
			ret = Byte.parseByte(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}

	/**
	 * String to Long
	 * 
	 * @param str
	 * @return
	 */
	public static long toLong(String str) {
		if (StringUtil.isBlank(str))
			return 0L;
		long ret = 0;
		try {
			ret = Long.parseLong(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}

	/**
	 * String[] to long[]
	 * 
	 * @param str
	 * @return
	 */
	public static long[] toLongs(String[] str) {
		if (str == null || str.length < 1)
			return new long[] { 0L };
		long[] ret = new long[str.length];
		ret = (long[]) ConvertUtils.convert(str, long.class);
		return ret;
	}

	/**
	 * String[] to double[]
	 * 
	 * @param str
	 * @return
	 */
	public static double[] toDoubles(String[] str) {

		if (str == null || str.length < 1)
			return new double[] { 0L };
		double[] ret = new double[str.length];
		for (int i = 0; i < str.length; i++) {
			ret[i] = toDouble(str[i]);
		}
		return ret;
	}

	/**
	 * String to Double
	 * 
	 * @param str
	 * @return
	 */
	public static double toDouble(String str) {
		if (StringUtil.isBlank(str))
			return 0;
		try {
			return BigDecimalUtil.round(str);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 生成指定长度的随机字符串，字母加数字组合
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String val = "";
		Random random = new Random();
		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * “参数=参数值”的模式用“&”字符拼接成字符串转换为map，例如："username=zhangsan&age=14"
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, Object> toMap(String str) {
		Map<String, Object> data = new HashMap<String, Object>();
		String[] str2 = str.split("&");
		for (String ss : str2) {
			String[] str3 = ss.split("=");
			data.put(str3[0], str3[1]);
		}
		return data;
	}

	/**
	 * 
	 * mysql like 值进行处理，若包含通配符则转译
	 * 
	 * @param value
	 * @return
	 */
	public static String likeSQLTranslation(String value) {
		if (isBlank(value)) {
			return "";
		}
		value = value.replace("_", "\\_");
		value = value.replace("%", "\\%");
		return "%" + value + "%";
	}

	/**
	 * 
	 * 去除字符串中包含的html标签内容
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String removeHtmlValue(String htmlStr) {
		if (htmlStr!=null) {
			String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
			String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			return htmlStr.trim(); // 返回文本字符串
		} else
			return htmlStr;
	}

	/**
	 * 以0补齐4位
	 * 
	 * @param n
	 * @param j
	 * @return
	 */
	public static String addZero(int n, int j) {
		StringBuffer str = new StringBuffer(n + "");
		for (int i = str.length(); i < j; i++) {
			str.insert(0, "0");
		}
		return str.toString();
	}

	/**
	 * 生成订单号
	 * 
	 * @return
	 */
	public static String creatOrder(long n) {
		return DateUtil.dateStr3(Calendar.getInstance().getTime()) + addZero((int) n, 8);
	}

	/**
	 * 以0补齐位
	 * 
	 * @param no
	 * @param j
	 * @return
	 */
	public static String addZeroStr(String no, int j) {
		StringBuffer str = new StringBuffer(no);
		for (int i = str.length(); i < j; i++) {
			str.insert(0, "0");
		}
		return str.toString();
	}

	/**
	 * 重写String.valueOf方法
	 * 
	 * @param obj
	 * @return
	 */
	public static String valueOf(Object obj) {
		return (obj == null) ? "" : obj.toString();
	}

	/**
	 * 将字符串集合以规定符号拼接
	 * 
	 * @param content
	 * @param regex
	 * @return
	 */
	public static String concatString(List<String> content, String regex) {
		String str = "";
		for (int i = 0; i < content.size(); i++) {
			if (i == 0)
				str = content.get(i);
			else
				str += regex + content.get(i);
		}
		return str;
	}

	/**
	 * 将字符串按规定符号拆分
	 * 
	 * @param content
	 * @param regex
	 * @return
	 */
	public static List<String> splitString(String content, String regex) {
		List<String> list = new ArrayList<String>();
		if (!StringUtil.isBlank(content)) {
			String[] strs = content.split(regex);
			for (String str : strs) {
				list.add(str);
			}
		}
		return list;
	}

	/**
	 * 返回集合中最长的字符串
	 * 
	 * @param list
	 * @return
	 */
	public static Map<String, Object> findLongest(List<String> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		String string = "";
		int max = 0;
		for (String str : list) {
			int num = str.length();
			if (num > max) {
				max = num;
				string = str;
			}
		}
		map.put("str", string);
		map.put("num", max);
		return map;
	}

	/**
	 * 检索字符串中含有某个字符的个数
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static int getNumOfRegex(String str, String regex) {
		return str.length() - str.replaceAll(regex, "").length();
	}

}
