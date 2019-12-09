package main.java.com.bluesky.zhz.server.mybatisgen.impl;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GeneratorU {
	
    public static void generate(String xmlName){
//        URL url = ClassLoader.getSystemResource("generatorConfig.xml");
        URL url = ClassLoader.getSystemResource(xmlName);
        //指定逆向工程配置文件
        try {
        	List<String> warnings = new ArrayList<String>();
        	ConfigurationParser cp = new ConfigurationParser(warnings);
        	File configFile = new File(url.getFile()); 
        	Configuration config = cp.parseConfiguration(configFile);
        	boolean overwrite = false;
        	DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        	MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
        			callback, warnings);
        	myBatisGenerator.generate(null);
			for (String warn :  warnings)
			{
				System.err.println(warn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//        try {
//        	GeneratorAntTask task = new GeneratorAntTask();
//        	task.setConfigfile(url.getFile()); 
//        	task.execute();// 需要 org.apache.ant包
//		} catch (Exception e) {
//		}
        System.out.println("Generator done!");
    }
}
