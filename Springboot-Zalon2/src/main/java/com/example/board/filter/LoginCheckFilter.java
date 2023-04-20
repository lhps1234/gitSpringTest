package com.example.board.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.PatternMatchUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter implements Filter {
	
	public boolean isLoginCheckPath(String requestURI){
		
		String[] whiteList = {"/","/member/login","/member/join","/default.css"   };
		
		return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
		
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String requestURI = httpRequest.getRequestURI();
		
		if(isLoginCheckPath(requestURI)) {
			 HttpSession session = httpRequest.getSession(false);
			
			 if(session==null || session.getAttribute("loginMember")==null) {
				 log.info("로그인 하지 않은 사용자입니다.");
				 httpResponse.sendRedirect("/member/login");
			 }
		 
		}
		try {
			
			log.info("LogincheckFilter 실행");
			chain.doFilter(request, response);
		}catch (Exception e) {
			throw e;
		}finally {
			log.info("Login filter 종료");
		}
		
		
	}

}
