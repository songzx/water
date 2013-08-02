package com.water.basictool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @Title: FreemarketUtil.java 
 * @Package com.ucap.gsm.util 
 * @Description: freemarket常用操作
 * @author 0000
 * @date 2012-8-21 上午10:20:07 
 * @version V1.0
 */
public class FreemarkerUtil {

	private static FreemarkerUtil freemarkerUtil = null;
	private static File templatefolder = null;
	private static Configuration configuration = null;
	
	private FreemarkerUtil(){}
	
	public static FreemarkerUtil getInstance(File tempdirecotry) throws IOException, TemplateException{
		if(freemarkerUtil == null){
			//String path = Config.getResouce(com.ucap.cm.engine.Constant.APPLICATION_PROPERTY,com.ucap.cm.engine.Constant.DEFAULT_CMS_PUBLISH_JSP_FOLDER)+"../../admin/freemarkettempfile";
			freemarkerUtil = new FreemarkerUtil();
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			Properties properties = new Properties();
			properties.load(new FileInputStream(System.getProperty("freemarket.setSettings")));
			configuration.setSettings(properties);
		}
		configuration.setDirectoryForTemplateLoading(tempdirecotry);
		return freemarkerUtil;
	}
	
	public static FreemarkerUtil getInstance() throws IOException, TemplateException{
		File tempdirecotry = new File(System.getProperty("freemarket.DirectoryForTemplate"));
		return getInstance(tempdirecotry);
	}

	/**
	 * 根据ftl模板生成指定路径的html文件.
	 * 
	 * @param templatfile
	 * @param htmfilename
	 * @param params
	 * @throws Exception
	 */
	public void recreatehtml(String templatfile, File htmfilename,Map params) throws Exception{
		if(!htmfilename.getParentFile().exists()){
			htmfilename.getParentFile().mkdirs();
		}
		Template t = configuration.getTemplate(templatfile); 
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmfilename),"UTF-8"));
		t.process(params, out);		
	}
}
