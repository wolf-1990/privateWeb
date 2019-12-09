package com.bluesky.zhz.web;

import com.bluesky.zhz.web.config.TestApplicationContextInitializer;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
@ComponentScan(basePackages = {"com.bluesky.zhz.web"})
@EnableScheduling
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.addListeners((ApplicationListener<ApplicationEvent>)event->{
			System.out.print("ApplicationListener");
		});
		springApplication.addInitializers(new TestApplicationContextInitializer());
		springApplication.run(args);
	}
}
