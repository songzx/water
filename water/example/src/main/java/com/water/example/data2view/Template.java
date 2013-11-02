package com.water.example.data2view;

import java.io.InputStream;

/**
 * 处理表单片段与整块jsp模块
 * 合成jsp 根据对象属性值，正则表达式判断输入合法性，表单类型，JMS的主题选择
 * 
 * @author 0000
 * @date Nov 2, 2013
 * @version V1.0
 */
public abstract class Template {
	private Script script = null;
	private Authz authz = null;
	
	/**
	 * 填充模板，并输出
	 * @return
	 */
	public InputStream fillin(){
		script.check();
		authz.check();
		rule();
		return null;
	}
	
	/**
	 * 处理各种填充形式
	 */
	public abstract void rule();
}