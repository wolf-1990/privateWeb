package com.bluesky.zhz.core.config;

import java.util.ArrayList;
import java.util.List;

import com.bluesky.zhz.core.constants.CommonConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = { "com.zhongda.yunxiao.*.controller" })
@Component
public class SwaggerConfig {
	@Bean
	public Docket customDocket() {
		ParameterBuilder ticketToken = new ParameterBuilder();
		ticketToken.name(CommonConstants.Header.HEADER_PARAM_TOKEN).description("user ticket").modelRef(new ModelRef("string"))
				.parameterType("header").required(false).build();
		ParameterBuilder ticketSource = new ParameterBuilder();
		ticketSource.name(CommonConstants.Header.HEADER_PARAM_SOURCE).description("source").modelRef(new ModelRef("string"))
				.parameterType("header").required(false).build();

		List<Parameter> pars = new ArrayList<Parameter>();
		pars.add(ticketToken.build());
		pars.add(ticketSource.build());

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).build()
				.globalOperationParameters(pars).apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("api swagger document").description("前后端联调swagger api 文档").version("1.0")
				.build();
	}
}