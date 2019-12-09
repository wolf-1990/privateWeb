package com.bluesky.zhz.web.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/22 16:16
 **/
//@Order(3)
public class TestApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.print("TestApplicationContextInitializer");
    }
}
