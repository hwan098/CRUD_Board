package com.study.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로깅 추상화 라이브러리: 로깅을 직접 하기 않고 로깅 구현체를 찾아 기능을 사용할 수 있게 해줌
public class LoggerInterceptor implements HandlerInterceptor{
	//컨르롤러 접근 직전에 어떠한 기능을 수행했는지 파악하기 위해 해당 기능과 매핑된 URI정보가 콘솔에 로그가 출력되도록 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("========================================");
		log.debug("================= BEGIN ================");
		log.debug("Request URI ===> " + request.getRequestURI());
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	//컨르롤러 접근 후에 요청(Request)의 끝을 알리는 로그가 콘솔에 출력되도록 처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug("================== END =================");
		log.debug("========================================");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
}
