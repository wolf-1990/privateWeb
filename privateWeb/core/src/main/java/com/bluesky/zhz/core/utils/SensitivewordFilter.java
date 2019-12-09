package com.bluesky.zhz.core.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Description: 敏感词过滤
 * @Project：test
 * @version 1.0
 */
public class SensitivewordFilter {

	@SuppressWarnings("rawtypes")
	public static Map sensitiveWordMap = null;
	public static int minMatchTYpe = 1; // 最小匹配规则
	public static int maxMatchType = 2; // 最大匹配规则

	/**
	 * 构造函数，初始化敏感词库
	 */
	public SensitivewordFilter(List<String> sensitiveNameList) {
		sensitiveWordMap = new SensitiveWordInit().initKeyWord(sensitiveNameList);
	}

	/**
	 * 判断文字是否包含敏感字符
	 * 
	 * @param txt
	 *            文字
	 * @param matchType
	 *            匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return 若包含返回true，否则返回false
	 * @version 1.0
	 */
	public boolean isContaintSensitiveWord(String txt, int matchType) {
		boolean flag = false;
		for (int i = 0; i < txt.length(); i++) {
			@SuppressWarnings("static-access")
			int matchFlag = this.CheckSensitiveWord(txt, i, matchType);
			if (matchFlag > 0) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 获取文字中的敏感词
	 * 
	 * @param txt
	 *            文字
	 * @param matchType
	 *            匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return
	 * @version 1.0
	 */
	public static Set<String> getSensitiveWord(String txt, int matchType) {
		Set<String> sensitiveWordList = new HashSet<String>();
		for (int i = 0; i < txt.length(); i++) {
			int length = CheckSensitiveWord(txt, i, matchType);
			if (length > 0) {
				sensitiveWordList.add(txt.substring(i, i + length));
				i = i + length - 1;
			}
		}
		return sensitiveWordList;
	}

	/**
	 * 替换敏感字字符
	 * 
	 * @date 2014年4月20日 下午5:12:07
	 * @param txt
	 * @param matchType
	 * @param replaceChar
	 *            替换字符，默认*
	 * @version 1.0
	 */
	public String replaceSensitiveWord(String txt, int matchType, String replaceChar) {
		String resultTxt = txt;
		Set<String> set = getSensitiveWord(txt, matchType);
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = getReplaceChars(replaceChar, word.length());
			resultTxt = resultTxt.replaceAll(word, replaceString);
		}
		return resultTxt;
	}

	/**
	 * 获取替换字符串
	 * 
	 * @date 2014年4月20日 下午5:21:19
	 * @param replaceChar
	 * @param length
	 * @return
	 * @version 1.0
	 */
	private String getReplaceChars(String replaceChar, int length) {
		String resultReplace = replaceChar;
		for (int i = 1; i < length; i++) {
			resultReplace += replaceChar;
		}
		return resultReplace;
	}

	/**
	 * 检查文字中是否包含敏感字符，检查规则如下：<br>
	 * 
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return，如果存在，则返回敏感词字符的长度，不存在返回0
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes" })
	public static int CheckSensitiveWord(String txt, int beginIndex, int matchType) {
		boolean flag = false;
		int matchFlag = 0;
		char word = 0;
		Map nowMap = sensitiveWordMap;
		for (int i = beginIndex; i < txt.length(); i++) {
			word = txt.charAt(i);
			nowMap = (Map) nowMap.get(word);
			if (nowMap != null) {
				matchFlag++;
				if ("1".equals(nowMap.get("isEnd"))) {
					flag = true;
					if (SensitivewordFilter.minMatchTYpe == matchType) {
						break;
					}
				}
			} else {
				break;
			}
		}
		if (matchFlag < 2 || !flag) {
			matchFlag = 0;
		}
		return matchFlag;
	}

}
