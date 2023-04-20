package com.example.board.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.board.filter.LogFilter;
import com.example.board.filter.LoginCheckFilter;
import com.example.board.interceptor.LogInterceptor;
import com.example.board.interceptor.LoginCheckInterceptor;

@Configuration
public class Webconfig implements WebMvcConfigurer {

	
	private String[] excludePaths= {"/","/member/join","/member/login"
			,"/member/logout","/*.css","/*.js","/*ico","/error"};
	
	
	
//	@Bean
	public FilterRegistrationBean logFilter() {
		
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		
		//등록할 필터 지정 
		filterRegistrationBean.setFilter(new LogFilter());
		
		// 필터 순서 적용
		filterRegistrationBean.setOrder(1);
		
		// 필터 적용 url 패턴
		filterRegistrationBean.addUrlPatterns("/*");
		
		
		return filterRegistrationBean;
	}
	
//	@Bean
	public FilterRegistrationBean loginCheckFilter() {
		
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		
		//등록할 필터 지정 
		filterRegistrationBean.setFilter(new LoginCheckFilter());
		
		// 필터 순서 적용
		filterRegistrationBean.setOrder(2);
		
		// 필터 적용 url 패턴
		filterRegistrationBean.addUrlPatterns("/*");
		
		
		return filterRegistrationBean;
	}
	
	
	 @Override
		public void addInterceptors(InterceptorRegistry registry) {
		 
		 registry.addInterceptor(new LoginCheckInterceptor())
		 .order(1)
		 .addPathPatterns("/**")
		 .excludePathPatterns(excludePaths);
		 
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
