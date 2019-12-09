package com.bluesky.zhz.core.utils;

import java.util.Random;

public class StringTool {

    public static String padRight(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, 0, src.length());
        for (int i = src.length(); i < len; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }

    public static String padLeft(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, diff, src.length());
        for (int i = 0; i < diff; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }
    
    public static String randomStr(int count){
    	Random random = new Random();
    	StringBuffer buffer = new StringBuffer();
        for( int i = 0; i < count; i ++) {     
            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写还是小写
            buffer.append((char)(choice + random.nextInt(26)));
        } 
    	return buffer.toString();
    }
}
