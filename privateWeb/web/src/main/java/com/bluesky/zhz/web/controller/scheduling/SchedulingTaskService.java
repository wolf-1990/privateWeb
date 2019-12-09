package com.bluesky.zhz.web.controller.scheduling;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/9 11:05
 **/
//@Service
public class SchedulingTaskService {
    int i = 0;
    @Scheduled(cron = "*/5 * * * * *")
    public void taskTest(){
        i++;
        System.out.println("定时任务增长"+i);
    }
    @Scheduled(cron = "*/5 * * * * *")
    public void taskTest1(){
        System.out.println("其他任务"+i);
    }
    @Scheduled(cron = "*/5 * * * * *")
    public void taskTest2(){
        System.out.println("这样也挺好"+i);
    }
}
