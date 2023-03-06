package com.example.demo.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@SuppressWarnings("deprecation")
@Component
public class StudentServiceInterceptorAppConfig extends WebMvcConfigurerAdapter{
	@Autowired
	StudentInterceptors studentInterceptors;
	
	
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(studentInterceptors);
	}
	
	
	

}
