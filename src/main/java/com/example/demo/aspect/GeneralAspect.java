package com.example.demo.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Student;

@Aspect
@Component
public class GeneralAspect {
	private static final Logger LOGGER = LogManager.getLogger(GeneralAspect.class);

	// intercept the methods
	@Pointcut("execution(* com.example.demo.controller..*(..)))")
	public void loggingPointCut() {

	}

	// before the aspect method
	// condition of point cut is met advice is executes
	@Before("loggingPointCut()")
	public void before(JoinPoint joinPoint) {
		LOGGER.info("Before method invoked::" + joinPoint.getSignature());
	}
	// @After the aspect method

	@After("loggingPointCut()")
	public void after(JoinPoint joinPoint) {
		LOGGER.info("after method invoked::" + joinPoint.getSignature());
	}

     //executes only method executes successfully 
	// returning student details
	@AfterReturning(value = "execution(* com.example.demo.controller..*.*(..))", returning = "s")
	public void afterReturning(JoinPoint joinPoint, Student s) {
		LOGGER.info("after method invoked::" + s);
	}

//method throws exception
//	@AfterThrowing(value = "execution(* com.example.demo.*.*(..))", throwing = "e")
//	public void afterThrowing(JoinPoint joinPoint, Exception e) {
//		LOGGER.info("exception method::" + e.getMessage());
//	}
	//
	@Around("loggingPointCut()")
	//@Annoations
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		LOGGER.info("Before around method invoked::" + joinPoint.getSignature());
		Object obj = joinPoint.proceed();

		if (obj instanceof Student) {
			LOGGER.info("after around method invoked::" + joinPoint.getSignature());

		}
		return obj;
	}

}
