package com.water.springmvcexample.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;


public class WebRequestMappingHandlerAdapter extends DispatcherServlet{

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//super.doDispatch(new MyHttpServletRequestWrapper(request), response);
	}

}
