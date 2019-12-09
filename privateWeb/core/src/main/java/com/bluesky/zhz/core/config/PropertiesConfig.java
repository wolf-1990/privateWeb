package com.bluesky.zhz.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取配置文件中的值
 */
@Component
public class PropertiesConfig {

    // 接口访问时间间隔
    @Value("${customer.request.interval.millisecond}")
    private String requestIntervalMillisecond;

	public String getRequestIntervalMillisecond() {
		return requestIntervalMillisecond;
	}

	public void setRequestIntervalMillisecond(String requestIntervalMillisecond) {
		this.requestIntervalMillisecond = requestIntervalMillisecond;
	}
   
}
