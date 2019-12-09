package com.bluesky.zhz.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SafeUtils {
	@Value("${customer.token.net.apikey}")
	private String apikey;

	private String createToken(Map<String, String> mapPara) {
		SortedMap<String, String> sortedPara = new TreeMap<String, String>();
		if (sortedPara != null) {
			for (String key : mapPara.keySet()) {

				if (mapPara.get(key) != null) {
					sortedPara.put(key, mapPara.get(key));
				}
			}
			StringBuilder sbText = new StringBuilder();
			for (String key : sortedPara.keySet()) {
				if (mapPara.get(key) != null) {
					sbText.append(mapPara.get(key));
				}
			}
			String sign = sbText.toString();
			System.err.println("sign:" + sign);
			return MD5.getInstance().getMD5String(sign);
		}
		return "";
	}

	private String getTimeSecond() {
		int zonetime = 28800000;// 毫秒 8小时的毫秒数
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date basedate = null;
		try {
			basedate = new Date(simpleDateFormat.parse("2019-01-01 00:00:00").getTime() + zonetime);
			long time = (new Date().getTime() - basedate.getTime()) / 1000 / 180;
			return time + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 获取当前token
	 * 
	 * @author 康晓璞2019年3月15日
	 *
	 * @param mapPara
	 * @return
	 */
	public String getNowToken(Map<String, String> mapPara) {
		if (mapPara == null) {
			mapPara = new HashMap<String, String>();
		}
		String timeSecond = getTimeSecond();
		mapPara.put("apikey", apikey);
		mapPara.put("timeSecond", timeSecond);
		String token = createToken(mapPara);
		return token;
	}

	public static void main(String[] args) throws ParseException {
		Map<String, String> mapPara = new HashMap<>();
		mapPara.put("username", "test1");// 用户接口需要此值
		// mapPara.put("agentname","test");//代理商接口需要此值
		SafeUtils safeUtils = new SafeUtils();
		String token = safeUtils.getNowToken(mapPara);
		System.err.println("token:" + token);
	}

	private static class MD5 {
		// private static final org.slf4j.Logger log =
		// LoggerFactory.getLogger(MD5.class);
		private static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
				'f' };
		private static Map<Character, Integer> rDigits = new HashMap<Character, Integer>(16);
		static {
			for (int i = 0; i < digits.length; ++i) {
				rDigits.put(digits[i], i);
			}
		}
		private static MD5 me = new MD5();
		private MessageDigest mHasher;
		private final ReentrantLock opLock = new ReentrantLock();

		private MD5() {
			try {
				this.mHasher = MessageDigest.getInstance("md5");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public static MD5 getInstance() {
			return me;
		}

		public String getMD5String(String content) {
			return this.bytes2string(this.hash(content));
		}

		public String getMD5String(byte[] content) {
			return this.bytes2string(this.hash(content));
		}

		public byte[] getMD5Bytes(byte[] content) {
			return this.hash(content);
		}

		public byte[] hash(String str) {
			this.opLock.lock();
			try {
				byte[] bt = this.mHasher.digest(str.getBytes("utf-8"));
				if (null == bt || bt.length != 16) {
					throw new IllegalArgumentException("md5 need");
				}
				return bt;
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("unsupported utf-8 encoding", e);
			} finally {
				this.opLock.unlock();
			}
		}

		public byte[] hash(byte[] data) {
			this.opLock.lock();
			try {
				byte[] bt = this.mHasher.digest(data);
				if (null == bt || bt.length != 16) {
					throw new IllegalArgumentException("md5 need");
				}
				return bt;
			} finally {
				this.opLock.unlock();
			}
		}

		public String bytes2string(byte[] bt) {
			int l = bt.length;
			char[] out = new char[l << 1];
			for (int i = 0, j = 0; i < l; i++) {
				out[j++] = digits[(0xF0 & bt[i]) >>> 4];
				out[j++] = digits[0x0F & bt[i]];
			}
			// if (log.isDebugEnabled()) {
			// log.debug("[hash]" + new String(out));
			// }
			return new String(out);
		}

		public byte[] string2bytes(String str) {
			if (null == str) {
				throw new NullPointerException("Argument is not allowed empty");
			}
			if (str.length() != 32) {
				throw new IllegalArgumentException("String length must equals 32");
			}
			byte[] data = new byte[16];
			char[] chs = str.toCharArray();
			for (int i = 0; i < 16; ++i) {
				int h = rDigits.get(chs[i * 2]).intValue();
				int l = rDigits.get(chs[i * 2 + 1]).intValue();
				data[i] = (byte) ((h & 0x0F) << 4 | l & 0x0F);
			}
			return data;
		}

		/***
		 * MD5加码 生成32位md5码
		 */
		public static String string2MD5(String inStr, String key) {
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
				return "";
			}
			inStr = inStr + key;
			char[] charArray = inStr.toCharArray();
			byte[] byteArray = new byte[charArray.length];

			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		}
	}
}
