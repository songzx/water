package com.water.basictool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Title: HtmlFileUtil.java
 * @Package com.ucap.gsm.util
 * @Description: apache-httpclient组件,生成静态页面
 * 
 * @author 0000
 * @date 2012-8-22 下午4:03:51
 * @version V1.0
 */
public class HtmlFileUtil {
	private static Logger logger = LoggerFactory.getLogger(HtmlFileUtil.class);

	private static HtmlFileUtil htmlFileUtil;

	private HtmlFileUtil() {
	}

	public static HtmlFileUtil getInstance() {
		if (htmlFileUtil == null) {
			htmlFileUtil = new HtmlFileUtil();
		}
		return htmlFileUtil;
	}

	/**
	 * 创建静态页面
	 * 
	 * 
	 * @param url
	 * @param encode
	 * @param htmlfile
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean createhtmlfile(String url, String encode, File htmlfile) throws Exception {
		if (!htmlfile.getParentFile().exists()) {
			htmlfile.getParentFile().mkdirs();
		}
		boolean result = false;
		FileOutputStream fos = null;
		InputStream inputStream = null;
		HttpClient httpclient = null;
		HttpGet httpget = null;
		try {
			httpclient = new DefaultHttpClient();
			// httpclient.getParams().setParameter(, arg1)
			// ClientConnectionManager clientConnectionManager = new
			// ThreadSafeClientConnManager();

			httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);

			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				inputStream = entity.getContent();
			}
			fos = new FileOutputStream(htmlfile);
			int ch = 0;
			while ((ch = inputStream.read()) != -1) {
				fos.write(ch);
			}
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (RuntimeException ex) {
			httpget.abort();
			logger.error(ex.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				fos.close();
			}
			httpclient.getConnectionManager().shutdown();
		}
		return result;

	}

	/**
	 * 创建静态页面,默认编码UTF-8
	 * 
	 * @param url
	 * @param htmlfile
	 * @return
	 * @throws Exception
	 */
	public boolean createhtmlfile(String url, File htmlfile) throws Exception {
		return createhtmlfile(url, "UTF-8", htmlfile);
	}

}
