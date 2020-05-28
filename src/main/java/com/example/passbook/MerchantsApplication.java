package com.example.passbook;

import com.example.passbook.security.AuthCheckInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;


@SpringBootApplication
public class MerchantsApplication extends WebMvcConfigurationSupport {

	/** 以 Resource 方式进行依赖注入 */
	@Resource
	private AuthCheckInterceptor authCheckInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(MerchantsApplication.class, args);
	}

	/**
	 * 注册拦截器
	 * @param registry
	 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor)
				.addPathPatterns("/merchants/**");
	}
}
