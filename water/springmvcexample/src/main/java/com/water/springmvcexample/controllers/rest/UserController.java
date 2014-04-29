package com.water.springmvcexample.controllers.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	
	@RequestMapping("/index")
	public String index(@RequestParam String logincode,@RequestParam String username,@RequestParam String passwd, Model model){
		//if("logincode".equals(logincode) && "passwd".equals(passwd)){
			model.asMap().put("username", username);
			return "admin/index";
		//}
		//return "login";
	}
}
