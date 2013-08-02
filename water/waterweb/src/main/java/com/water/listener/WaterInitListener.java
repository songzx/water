package com.water.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.water.basictool.FreemarkerUtil;
import com.water.basictool.WaterSysteConfigConst;

/**
 * 初始化系统的基本配置
 * 
 * @todo TODO
 * @author 0000
 * @date Feb 13, 2013 8:24:14 PM
 * @version 1.0
 * @classname gtdInitListener
 */
public class WaterInitListener implements ServletContextListener {
	private static Logger logger = null;

	@Override
	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		logger.info("应用配置监听正在销毁.....");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletcontextevent) {
		//从servletContext获取路径
		//String realpath = servletcontextevent.getServletContext().getRealPath("/");
		//String basicconfig = realpath + "WEB-INF/resources/basicconfig/sysconfigpath.properties";
		
		//从classpath:获取路径
		String realpath = WaterInitListener.class.getResource("/").getPath();
		String basicconfig = realpath + "commonconfig/sysconfigpath.properties";
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(basicconfig));

			String serverdir = properties.getProperty(WaterSysteConfigConst.SERVER_BASICDIR);
			String tmpdir = properties.getProperty(WaterSysteConfigConst.SERVER_BASICDIR) + "tmpdir/";
			String logdir = properties.getProperty(WaterSysteConfigConst.SERVER_BASICDIR) + "logs/";
			String encode = properties.getProperty(WaterSysteConfigConst.WEBAPP_ENCODE);

			System.setProperty(WaterSysteConfigConst.SERVER_BASICDIR, serverdir);
			System.setProperty(WaterSysteConfigConst.WEBAPP_BASICDIR, servletcontextevent.getServletContext().getRealPath("/"));
			System.setProperty(WaterSysteConfigConst.WEBAPP_LOG_BASICDIR, logdir);
			System.setProperty(WaterSysteConfigConst.WEBAPP_JAVA_IO_TMPDIR, tmpdir);
			System.setProperty(WaterSysteConfigConst.WEBAPP_ENCODE, encode);
			
			
			

			File serverfile = new File(serverdir);
			if (!serverfile.exists()) {
				serverfile.mkdirs();
			}
			File tmpfile = new File(tmpdir);
			if (!tmpfile.exists()) {
				tmpfile.mkdirs();
			}
			File logfile = new File(logdir);
			if (!logfile.exists()) {
				logfile.mkdirs();
			}

			logger = LoggerFactory.getLogger(WaterInitListener.class);
			for(Iterator<Object> it = properties.keySet().iterator();it.hasNext();){
				String key = (String) it.next();
				System.setProperty(key, properties.getProperty(key).trim().replaceFirst("classpath:", realpath));
				logger.info(key+" : "+ properties.getProperty(key).trim().replaceFirst("classpath:", realpath));
			}
			logger.info("应用配置监听正在启动....");
			logger.info("－－－－－－－－－－－－－－－－应用基本配置－－－－－－－－－－－－－－－－－－－－－－－－");
			logger.info("｜－－－服务器根路径：" + serverdir);
			logger.info("｜－－－应用根路径：" + servletcontextevent.getServletContext().getRealPath("/"));
			logger.info("｜－－－应用存放自定义日志路径：" + logdir);
			logger.info("｜－－－应用临时存放文件路径：" + tmpdir);
			logger.info("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
		} catch (FileNotFoundException e) {
			System.out.println("系统配置文件不存在；"+basicconfig);
			System.exit(0);
		} catch (IOException e) {
			System.out.println("配置文件被使用，不能正确读取。");
			System.exit(0);
		}
	}

}
