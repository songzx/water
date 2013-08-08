package com.water.actions;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ParentAction extends ActionSupport {
	
	public String list() throws Exception {
		//String strs[] = ServletActionContext.getRequest().getRequestURI().split("//");
		
		
		return "list";
	}

	public String add() throws Exception {

		return "add";
	}

	public String premodify() throws Exception {

		return "modify";
	}

	public String modify() throws Exception {

		return "list";
	}

	public String read() throws Exception {

		return "read";
	}
}
