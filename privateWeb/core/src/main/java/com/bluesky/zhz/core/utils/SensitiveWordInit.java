package com.bluesky.zhz.core.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @Description: 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 * @Project：test
 * @version 1.0
 */
@Component
public class SensitiveWordInit {

	@Autowired
	private RedisTemplate<String,Object> redisTemplat;
	@SuppressWarnings("unused")
	private String ENCODING = "gbk";
	@SuppressWarnings("rawtypes")
	public HashMap sensitiveWordMap;

	public SensitiveWordInit() {
		super();
	}

	/**
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public Map initKeyWord(List<String> sensitiveNameList) {
		// 读取敏感词库
		try {
			Set<String> keyWordSet = new HashSet<String>();
			for (String s : sensitiveNameList) {
				keyWordSet.add(s.trim());
			}
			addSensitiveWordToHashMap(keyWordSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * @param keyWordSet
	 *            敏感词库
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;

		Iterator<String> iterator = keyWordSet.iterator();
		while (iterator.hasNext()) {
			key = iterator.next();
			nowMap = sensitiveWordMap;
			for (int i = 0; i < key.length(); i++) {
				char keyChar = key.charAt(i);
				Object wordMap = nowMap.get(keyChar);

				if (wordMap != null) {
					nowMap = (Map) wordMap;
				} else {
					newWorMap = new HashMap<String, String>();
					newWorMap.put("isEnd", "0");
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1");
				}
			}
		}
	}

}
