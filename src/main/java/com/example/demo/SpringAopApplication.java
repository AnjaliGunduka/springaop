package com.example.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.College;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
		 ApplicationContext appContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		 College countryObj = (College) appContext.getBean("CollegeBean");
		 String clgName=countryObj.getClgname();
	        String clgAddress=countryObj.getClgAddress();
	        System.out.println(clgAddress+"and"+clgName);
	        
	        
	        
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("REST API",
				" REST API.", "1.0", "Terms of service",
				new Contact("Anjali Gunduka", "", "anjali@gmail.com"), "License of API", "API license URL",
				Collections.emptyList());
	}
}
