package com.water.springmvcexample.controllers.freemarker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.water.springmvcexample.models.XmlModel;

@Controller
@RequestMapping(value = "ucap", method = { RequestMethod.GET, RequestMethod.POST })
public class FreemarkerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerController.class);

	
	/**
	 * ftl 源
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contentftl")
	public ModelAndView getContentftl() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content");
		mav.getModel().put("content", "内容");
		
		return mav;
	}
}
