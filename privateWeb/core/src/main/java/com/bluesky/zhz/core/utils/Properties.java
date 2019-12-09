package com.bluesky.zhz.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Properties {
    private static HashMap<String, String> propertiesMap = new HashMap<String, String>();
    static {
        try {
            ClassLoader cl=Properties.class.getClassLoader();
            InputStream inputStream = cl.getResourceAsStream("private.properties");
            java.util.Properties pro = new java.util.Properties();
            InputStream inputStream2 = cl.getResourceAsStream("public.properties");
            java.util.Properties pro2 = new java.util.Properties();
            try {
                pro.load(inputStream);
                pro2.load(inputStream2);
                for (String n : pro.stringPropertyNames()) {
                    if (!propertiesMap.containsKey(n))
                        propertiesMap.put(n, pro.getProperty(n));
                }
                for (String n : pro2.stringPropertyNames()) {
                    if (!propertiesMap.containsKey(n))
                        propertiesMap.put(n, pro2.getProperty(n));
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String Get(String key) {
        return propertiesMap.get(key);
    }
}
