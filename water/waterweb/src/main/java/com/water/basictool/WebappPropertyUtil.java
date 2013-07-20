package com.water.basictool;

/**
 * 获取系统基础属性
 * 
 * @author 0000
 * @date Feb 8, 2013 5:48:50 PM
 * @version 1.0
 * @classname WebappPropertyUtil
 */
public class WebappPropertyUtil {

	private static WebappPropertyUtil webappPropertyUtil;
	private WebappPropertyUtil(){}
	
	public static WebappPropertyUtil getInstance(){
		if(webappPropertyUtil == null){
			webappPropertyUtil = new WebappPropertyUtil();
		}
		return webappPropertyUtil;
	}
	
	public String getServerPath(){
		return System.getProperty(WaterSysteConfigConst.SERVER_BASICDIR);
	}
	
	public String getWebappPath(){
		return System.getProperty(WaterSysteConfigConst.WEBAPP_BASICDIR);
	}
	
}
