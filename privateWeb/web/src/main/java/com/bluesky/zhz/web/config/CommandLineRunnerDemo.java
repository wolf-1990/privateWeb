package com.bluesky.zhz.web.config;

import org.springframework.boot.CommandLineRunner;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/22 16:38
 **/
//@Order(2)
//@Component
public class CommandLineRunnerDemo implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunnerDemo");
    }
}
