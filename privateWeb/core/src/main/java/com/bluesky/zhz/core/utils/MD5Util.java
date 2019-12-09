
package com.bluesky.zhz.core.utils;

import com.gladst.common.encryptdecrypt.DigestEncoder;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author 孙志勇   2018年5月11日
 *
 */
public class MD5Util {
	
	
	/**
	 * 获取MD5 串
	 * @author 孙志勇 2018年5月11日 
	 * @param source
	 * @return
	 */
	public static String getMD5(String source) {
		String result = null;
		try {
			result = DigestEncoder.generateMD5(source);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String sign(String text, String key, String input_charset) {
		text = text + key;
		return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	}

	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5:" + charset);
		}
	}

}
