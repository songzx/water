package com.water.example.quartzexample;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessJspJob implements Job {
	private static Logger logger = LoggerFactory.getLogger(AccessJspJob.class);
	private static HttpClient httpclient = new DefaultHttpClient();

	@Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		String jsps[] = jobExecutionContext.getJobDetail().getJobDataMap()
				.get("jsps") == null ? new String[0] : jobExecutionContext
				.getJobDetail().getJobDataMap().get("jsps").toString()
				.split("\\|");
		for (String jsp : jsps) {
			logger.info("访问jsp:" + jsp + ";");
			boolean result = accessjsp(jsp);
			logger.info("结果:" + result);
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
	private synchronized boolean accessjsp(String url) {
		boolean flag = false;

		HttpGet httpget = null;
		HttpResponse response = null;
		HttpEntity entity = null;
		try {
			httpget = new HttpGet(url);
			httpget.setHeader("Connection", "close");
			response = httpclient.execute(httpget);
			Calendar calendar = Calendar.getInstance(
					TimeZone.getTimeZone("GMT"), Locale.CHINA);
			calendar.add(Calendar.MILLISECOND, 1000 * 5);
			response.setHeader("Expires", getGMTDateStr(calendar.getTime()));
			if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
				return false;
			}
			entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				int l;
				while ((l = instream.read()) != -1) {
				}
				flag = true;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			if (httpclient != null) {
				httpclient.getConnectionManager().closeExpiredConnections();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			if (httpclient != null) {
				httpclient.getConnectionManager().closeExpiredConnections();
			}
		} catch (IOException e) {
			e.printStackTrace();
			if (httpclient != null) {
				httpclient.getConnectionManager().closeExpiredConnections();
			}
		} finally {
			if (httpclient != null) {
				httpclient.getConnectionManager().closeExpiredConnections();
			}
		}
		return flag;
	}

	private static String getGMTDateStr(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		dateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
		return dateFormat.format(date);
	}
}
