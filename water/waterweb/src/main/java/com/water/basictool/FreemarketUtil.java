package com.water.basictool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @Title: FreemarketUtil.java 
 * @Package com.ucap.gsm.util 
 * @Description: freemarket常用操作
 * @author 0000
 * @date 2012-8-21 上午10:20:07 
 * @version V1.0
 */
public class FreemarketUtil {

	private static FreemarketUtil freemarketUtil = null;
	private static File templatefolder = null;
	
	private FreemarketUtil(){}
	
	public static FreemarketUtil getInstance(String path){
		if(freemarketUtil == null){
			//String path = Config.getResouce(com.ucap.cm.engine.Constant.APPLICATION_PROPERTY,com.ucap.cm.engine.Constant.DEFAULT_CMS_PUBLISH_JSP_FOLDER)+"../../admin/freemarkettempfile";
			freemarketUtil = new FreemarketUtil();
			templatefolder = new File(path);
		}
		return freemarketUtil;
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
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		configuration.setDirectoryForTemplateLoading(templatefolder);
		
		Template t = configuration.getTemplate(templatfile); 
		
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmfilename),"UTF-8"));
		t.process(params, out);		
	}
}
