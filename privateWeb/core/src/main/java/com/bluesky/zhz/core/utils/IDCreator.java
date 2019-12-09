package com.bluesky.zhz.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IDCreator {
    private static String preTimestamp = "";
    private static int id = 0;

    public static synchronized String CreateID() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date now = new Date();
        String timestamp = simpleDateFormat.format(now);
        if (!timestamp.equals(preTimestamp)) {
            id = 0;
            preTimestamp = timestamp;
        } else {
            id++;
        }
        String SiteID = StringTool.padLeft(Properties.Get("SiteID"), 3, '0');
        String MachineID = StringTool.padLeft(Properties.Get("MachineID"), 3, '0');
        String strID = StringTool.padLeft(new Integer(id).toString(), 7, '0');
        //时间戳占12位，SiteID占3位,MachineID占3位 自增占7位 总共25位
        return preTimestamp + SiteID + MachineID + strID;
    }
    
    /**
     * 创建UUID
     * @return
     */
    public static synchronized String CreateUuID() {
    	return UUID.randomUUID().toString();
    }
    public static void main(String[] args) {
    	for (int i = 0; i < 10; i++) {
    		System.out.println(i+":"+UUID.randomUUID().toString());
		}
	}
}
