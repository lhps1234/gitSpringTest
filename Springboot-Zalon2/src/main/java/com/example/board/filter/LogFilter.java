package com.example.board.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter{
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Log Filter Init");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Log Filter stop");
		
		//요청 값 확인
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		
		log.info("requestURI : {}",requestURI);
		
		// 필터에서 안 멈추고 값을 넘겨주는 방법 
		
		chain.doFilter(request, response);	
	}

	
	
	@Override
	public void destroy() {
		log.info("Log Filter Destroy");
	}
}
