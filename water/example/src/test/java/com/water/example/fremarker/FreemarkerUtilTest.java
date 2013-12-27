package com.water.example.fremarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtilTest {

	@Test
	public void testtemplatedoc(){
		try {
			String path = "D:\\coding\\study\\water\\water\\example\\src\\test\\java\\com\\water\\example\\fremarker\\";
			File templatefolder = new File(path);
			
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setDirectoryForTemplateLoading(templatefolder);
			
			Template t = configuration.getTemplate("1.xml"); 
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywork", "中");
			
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/2.doc"),"UTF-8"));
			t.process(params, out);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
