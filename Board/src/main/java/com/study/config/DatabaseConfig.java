package com.study.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration	// @Configuration이 지정된 클래스를 자바 기반의 설정 파일로 인식
@PropertySource("classpath:/application.properties")	//해당 클래스에서 참조할 properties 파일의 위치를 지정
public class DatabaseConfig {

    @Autowired	//Bean으로 등록된 인스턴스를 클래스에 주입하는데 사용
    private ApplicationContext context;	//스프링컨테이너 중 하나, 빈의 생성과 사용, 관계, 생명 주기 등을 관리한다.

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")	// @PropertySource에 지정된 파일(application.properties)에서 prefix에 해당하는 spring.datasource.hikari로 시작하는 설정을 모두 읽어 들여 해당 메서드에 매핑(바인딩).

    public HikariConfig hikariConfig() {	//커넥션 풀 라이브러리 중 하나인 히카리cp 객체를 생성
        return new HikariConfig();
    }

    @Bean	//Configuration 클래스의 메서드레벨에만 지정 가능, @Bean이 지정된 객체는 컨테이너에 의해 관리되는 Bean으로 등록됨
    public DataSource dataSource() {	//데이터 소스 객체를 생성한다. 커넥션 풀을 지원하기 위한 인터페이스
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {	//데이터베이스의 커넥션과 SQL실행에 대한 모든 것을 갖는 역할이다.
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml"));
        factoryBean.setConfiguration(mybatisConfig());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {	//sqlSessionFactory를 통해 생성되고 SQL실행에 필요한 모든 메서드를 갖는 객체이다.
        return new SqlSessionTemplate(sqlSessionFactory());
    }
    
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig() {
        return new org.apache.ibatis.session.Configuration();
    }

}
/*
 * 
 * */
 