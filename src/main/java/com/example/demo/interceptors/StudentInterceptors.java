package com.example.demo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class StudentInterceptors implements HandlerInterceptor {
	org.slf4j.Logger logIntercept = org.slf4j.LoggerFactory.getLogger(this.getClass());

//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		if (request.getHeader("Authorization") == null) {
//			logIntercept.info("Authorization not sent.");
//			logIntercept.info("Validation NOK.");
//			return false;
//		} else if (request.getHeader("Authorization").equals("Test")) {
//
//			logIntercept.info("Validation OK.");
//			return true;
//		} else {
//			logIntercept.info("Validation NOK.");
//			return false;
//		}
//
//	}

	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object handle, ModelAndView modelview) {
		logIntercept.info("State: Post request is handled");
	}

	public void afterCompletions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object handle, Exception exception) {
		logIntercept.info("State: After completion");
	}
}
