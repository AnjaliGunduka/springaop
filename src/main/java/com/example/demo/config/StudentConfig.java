package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.StudentFilter;

@Configuration
public class StudentConfig {
	@Bean
	 public FilterRegistrationBean <StudentFilter> filterRegistrationBean() {
	  FilterRegistrationBean <StudentFilter> registrationBean = new FilterRegistrationBean();
	  StudentFilter customURLFilter = new StudentFilter();
	  registrationBean.setFilter(customURLFilter);
	  registrationBean.addUrlPatterns("/createStudent");
	  registrationBean.setOrder(2); //set precedence
	  return registrationBean;
	 }
}
