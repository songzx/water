package com.water.quartz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @Title: AccessJspJob.java
 * @Package com.ucap.quartz
 * @Description: 处理jsp任务
 * @author Administrator
 * @date 2012-12-2 下午10:49:49
 * @version V1.0
 */
public class AccessJspJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(AccessJspJob.class);

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		String jsps[] = jobExecutionContext.getJobDetail().getJobDataMap().get("jsps") == null ? new String[0] : jobExecutionContext.getJobDetail().getJobDataMap().get("jsps").toString().split("\\|");
		for (String jsp : jsps) {
				logger.info("访问jsp:"+jsp+";");
				logger.info("结果:"+accessjsp(jsp));
		}
	}

	/**
	 * 访问jsp
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private boolean accessjsp(String url) {

		boolean result = false;
		FileOutputStream fos = null;
		InputStream inputStream = null;
		HttpClient httpclient = null;
		HttpGet httpget = null;
		try {
			httpclient = new DefaultHttpClient();
			//httpclient.getParams().setParameter(, arg1)
			//ClientConnectionManager clientConnectionManager = new ThreadSafeClientConnManager();
			
			httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);	
			
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				inputStream = entity.getContent();
			}
			while (inputStream.read() != -1) {
				
			}
			result = true;
		}
		catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (RuntimeException ex) {
            httpget.abort();
            logger.error(ex.getMessage());
        } 
		finally {
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

}
