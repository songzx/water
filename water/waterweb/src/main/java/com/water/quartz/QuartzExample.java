package com.water.quartz;

import java.io.UnsupportedEncodingException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

//多个属性文件中的key如相同，则会被替换;reader用于识别是用来读取xml还是properties
@Configuration
@PropertySource(value = { "classpath:quartz/quartzexample.properties" })
//@ImportResource(value = { "classpath:/quartz/quartzexample.properties" },reader=PropertiesBeanDefinitionReader.class)
public class QuartzExample {
	private static Logger logger = LoggerFactory.getLogger(QuartzExample.class);
	
	public String name;
	
	@Autowired
    Environment env;

	@Scheduled(cron = "0/5 * * * * ?")
	protected void execute() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		logger.info("执行定时器－－－－－－－－－－－－－－－－－－－－－－－");
		logger.info("传入值:" +new String(env.getProperty("quartzexample1.name","").getBytes("ISO-8859-1"),"UTF-8"));
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

}
