package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}

/*
 * main메서드는 SpringApplication.run()을 호출해서  웹 어플리케이션을 실행하는 역할을 한다.
 * @SpringBootApplication 애너테이션 구성
 * @EnableAutoConfiguration : 개발에 필요한 필수적인 설정들의 처리가 되어있다. 해당 애너테이션으로 다양한 설정들의 일부가 자동으로 완료된다.
 * @ComponentScan : 기존의 xml설정 방식의 스프링은 빈의 등록 및 스캔을 위해서 수동으로 ComponentScan을 여러 개 선언하는 방식을 사용했는데 
 * 스프링부트는 해당 애너테이션에 의해서 자동으로 컴포넌트 클래스를 검색하고 스프링 애플리케이션 콘텍스트에 빈으로 등록한다. 쉽게 말해 의존성 주입과정이 더욱 간편해졌다.
 * @Configuration : 해당 애너테이션이 선언된 클래스는 자바 기반의 설정 파일로 인식된다.
 * */