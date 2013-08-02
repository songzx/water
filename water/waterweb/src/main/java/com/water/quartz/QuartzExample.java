package com.water.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class QuartzExample {
	private static Logger logger = LoggerFactory.getLogger(QuartzExample.class);
	
	@Async
	@Scheduled(cron="*/5 * * * * ?")
	protected void execute() {
		// TODO Auto-generated method stub
		logger.info("执行定时器－－－－－－－－－－－－－－－－－－－－－－－");
	}

}
