package com.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로깅 관련 라이브러리들을 통일된 방식으로 사용할 수 있게 하는 어노테이션
@Aspect //AOP 기능을 하는 클래스의 클래스 레벨에 지정하는 어노테이션
@Component //스프링 컨테이너에 빈으로 들록하기 위해 사용하는 어노테이션
public class LoggerAspect {
	
	//어드바이스 다섯가지 종류 중 하나로 메서드 호출 자체를 제어할 수 있는 어드바이스 중 가장 강력한 기능 
	@Around("execution(* com.study.domain..*Controller.*(..)) || execution(* com.study.domain..*Service.*(..)) || execution(* com.study.domain..*Mapper.*(..))")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable{
		
		String name = joinPoint.getSignature().getDeclaringTypeName(); //대상 파일의 패키지 경로와 파일명이 담기게 된다.
		String type = 
				StringUtils.contains(name, "Controller") ? "Controller ===> " :
	            StringUtils.contains(name, "Service") ? "Service ===> " :
	            StringUtils.contains(name, "Mapper") ? "Mapper ===> " : "";
		
		log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}

}
