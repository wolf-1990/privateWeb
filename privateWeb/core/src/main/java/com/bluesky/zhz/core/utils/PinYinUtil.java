package com.bluesky.zhz.core.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
/**
 * 中文转拼音，拼音转中文等工具类
 *      
 * 类名称：PinYinUtil   
 * 类描述：   
 * 创建人： Su Hua Bing
 * 创建时间：2014-5-28 上午11:01:13   
 * 修改人：  Su Hua Bing 
 * 修改时间：2014-5-28 上午11:01:13   
 * 修改备注：   
 * @version    
 *
 */
public class PinYinUtil {
    // 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
    private static int BEGIN = 45217;
    private static int END = 63486;

    // 对应首字母区间表
    private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z', };
    // 二十六个字母区间对应二十七个端点
    // GB2312码汉字区间十进制表示
    private static int[] table = new int[27];
    // 按照声 母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
    // i, u, v都不做声母, 自定规则跟随前面的字母
    private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', };

    // 初始化
    static {
        for (int i = 0; i < 26; i++) {
            table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
        }
        table[26] = END;// 区间表结尾
    }

    public static void main(String[] args) {
		System.out.println(chTraSpelAll("转拼音的方法"));
		System.out.println(chTraSpelFist("转拼音的方法"));
	}

	public PinYinUtil(){
		
	}
	    
    /**
    * 汉字转拼音的方法(全拼)
    * @param content 汉字
    * @return 拼音
    */
    public static String chTraSpelAll(String content){
        char[] nameChar = content.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        StringBuffer pinyinName = new StringBuffer("");
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
        }
        return pinyinName.toString();
    }
    
    /**
     * 汉字转拼音的方法(获取每个字首字母)
     * @param content 汉字
     * @return 拼音
     */
    @Deprecated
    public static String chTraSpelFist(String content){
    	char[] nameChar = content.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        StringBuffer pinyinName = new StringBuffer("");
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                	String temp = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                    pinyinName.append((temp != null && !"".equals(temp.trim()))?temp.substring(0, 1):"");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
        }
        return pinyinName.toString();
    }

    // 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出
    /**
     * 汉字转拼音的方法(获取每个字首字母)
     * @param SourceStr 汉字
     * @return 拼音
     */
    public static String cn2py(String SourceStr) {
        String Result = "";
        int StrLength = SourceStr.length();
        int i;
        try {
            for (i = 0; i < StrLength; i++) {
                Result += Char2Initial(SourceStr.charAt(i));
            }
        } catch (Exception e) {
            Result = "";
            e.printStackTrace();
        }
        return Result;
    }
    /**
     * 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0' 　　* 　　
     */
    private static char Char2Initial(char ch) {
        // 对英文字母的处理：小写字母转换为大写，大写的直接返回
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 'a' + 'A');
        }
        if (ch >= 'A' && ch <= 'Z') {
            return ch;
        }
        // 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
        // 若不是，则直接返回。
        // 若是，则在码表内的进行判断。
        int gb = gbValue(ch);// 汉字转换首字母
        if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
        {
            return ch;
        }
        int i;
        for (i = 0; i < 26; i++) {// 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
            if ((gb >= table[i]) && (gb < table[i + 1])) {
                break;
            }
        }
        if (gb == END) {// 补上GB2312区间最右端
            i = 25;
        }
        return initialtable[i]; // 在码表区间中，返回首字母
    }

    /**
     * 取出汉字的编码 cn 汉字 　　
     */
    private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。
        String str = new String();
        str += ch;
        try {
            byte[] bytes = str.getBytes("GB2312");
            if (bytes.length < 2) {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
        } catch (Exception e) {
            return 0;
        }
    }


 
}