package com.water.basictool.exception;

public class ConfigFileNoExistException extends Exception{

	public ConfigFileNoExistException(String error){
		super(error);
	}
	public ConfigFileNoExistException(){
		super("系统配置文件不存在！");
	}
}