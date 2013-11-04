package com.water.example.data2view;

import java.io.InputStream;

/**
 * 正则表达式，验证表单的前后台；结合jquery
 * 
 * @author 0000
 * @date Nov 2, 2013
 * @version V1.0
 */
public abstract class Script {

	public InputStream check() {
		getScriptExpression();
		return null;
	}

	public boolean checkflag() {
		return false;
	}

	/**
	 * 获取错误的信息
	 * 
	 * @return
	 */
	public abstract String getErrorMsg();

	/**
	 * 获取正则校验正则表达式
	 * 
	 * @return
	 */
	public abstract String getScriptExpression();
}
