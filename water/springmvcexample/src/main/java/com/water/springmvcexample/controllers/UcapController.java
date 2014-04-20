package com.water.springmvcexample.controllers;

import java.util.ArrayList;
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

@Controller
@RequestMapping("ucap")
public class UcapController{
	
	@RequestMapping(value="/example",method=RequestMethod.GET)
	public String example(Model model) {
		model.addAttribute("message", "Hello World!");
		return "index";
	}

	@RequestMapping(value="/content", method=RequestMethod.GET)
    public ModelAndView getContent() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content");
       
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,Object> tempmap = null;
        for(int i = 0; i < 10; i++){
        	tempmap = new HashMap<>();
        	tempmap.put("id", UUID.randomUUID().toString());
        	tempmap.put("content11","<a>aa</a>");
        	result.add(tempmap);
        }
        
        mav.addObject("sampleContentList", result);
        return mav;
    }
}
