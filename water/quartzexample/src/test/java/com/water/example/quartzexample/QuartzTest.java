package com.water.example.quartzexample;

import org.junit.Before;
import org.junit.Test;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

	StdSchedulerFactory factory;
	
	@Before
	public void init() {
		try {
			factory = new StdSchedulerFactory("quartz/quartz.properties");
			factory.getScheduler().start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test01(){
		System.out.println("=-");
	}
}
