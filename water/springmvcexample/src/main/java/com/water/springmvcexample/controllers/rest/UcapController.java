package com.water.springmvcexample.controllers.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.water.springmvcexample.models.XmlModel;

@Controller
//@RestController
@RequestMapping(value = "ucap", method = { RequestMethod.GET, RequestMethod.POST })
public class UcapController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UcapController.class);

	/**
	 * jsp
	 * 
	 * @param model
	 */
	@RequestMapping("/example")
	public String example(@RequestParam Map<String,Object> param, Model model) {
		model.addAttribute("message", param.get("message"));
		return "index";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		LOGGER.info(request.getPathInfo());
		return "admin/index";
	}

	/**
	 * free 源
	 * 
	 * @return
	 */
	@RequestMapping(value = "/content", produces = "application/atom+xml")
	public ModelAndView getContent() {
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> tempmap = null;
		for (int i = 0; i < 10; i++) {
			tempmap = new HashMap<>();
			tempmap.put("id", UUID.randomUUID().toString());
			tempmap.put("content11", "<a>aa</a>");
			result.add(tempmap);
		}

		mav.addObject("sampleContentList", result);
		return mav;
	}

	/**
	 * json 源
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contentjson", produces = "application/json")
	public ModelAndView getContentjson() {
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> tempmap = null;
		for (int i = 0; i < 10; i++) {
			tempmap = new HashMap<>();
			tempmap.put("id", UUID.randomUUID().toString());
			tempmap.put("content11", "<a>'||中文||\"aa</a>");
			result.add(tempmap);
		}

		mav.addObject("sampleContentList", result);
		return mav;
	}


	/**
	 * xml 源
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contentxml", produces = "application/xml")
	public XmlModel getContentxml() {
		
		XmlModel xmlModel = new XmlModel();
		xmlModel.set_content("内容");
		xmlModel.setId(UUID.randomUUID().toString());
		xmlModel.set_date(new Date());
		return xmlModel;
	}
}
