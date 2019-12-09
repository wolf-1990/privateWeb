package com.bluesky.zhz.web.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/22 16:40
 **/
//@Order(1)
//@Component
public class ApplicationRunnerDemo implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner");
    }
}
