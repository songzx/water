package com.water.basictool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oracle.net.aso.s;

import org.apache.commons.io.IOUtils;
import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;


/**
 * @todo 工作日管理
 * @author 0000
 * @date Jan 21, 2013 10:49:57 AM
 * @version 1.0
 * @classname WorkdayUtil
 */
public class WorkdayUtil {

	private Logger logger = LoggerFactory.getLogger(WorkdayUtil.class);
	public static final long RE_DATE = 1000 * 60 * 60 * 24;
	public static final long RE_HOUR = 1000 * 60 * 60;
	public static final long RE_MINUES = 1000 * 60;
	public static final long RE_WEEK = 1000 * 60 * 60 * 24 * 7;
	public static final long RE_METHOM = 1000 * 60 * 60 * 24 * 30;//
	public static final long RE_YEAR = 1000 * 60 * 60 * 24 * 365;//
	private static WorkdayUtil workdayUtil;

	private WorkdayUtil() {
	}

	public static WorkdayUtil getInstance() {
		if (workdayUtil == null) {
			workdayUtil = new WorkdayUtil();
		}
		return workdayUtil;
	}

	/**
	 * 比较日期相关多少,可精确到年,月,星期,天,时,分,秒
	 * 
	 * @param startdate
	 * @param enddate
	 * @return
	 */
	public long compareDate(Date startdate, Date enddate, long retype) {
		long starttime = startdate.getTime();
		long endtime = enddate.getTime();
		return (endtime - starttime) % retype == 0 ? (endtime - starttime) / retype : ((endtime - starttime) / retype + 1);
	}

	/**
	 * 比较日期相关多少年,月,星期,天,时,分,秒
	 * 
	 * @param strstartdate
	 * @param strenddate
	 * @param dateformat
	 * @return
	 */
	public long compareDate(String strstartdate, String strenddate, String dateformat, long retype) {
		Date sdate = DateValidator.getInstance().validate(strstartdate, dateformat);
		Date edate = DateValidator.getInstance().validate(strenddate, dateformat);
		if (sdate == null || edate == null) {
			return 0;
		}
		return compareDate(sdate, edate, retype);
	}

	/**
	 * 比较日期相关多少年,月,星期,天,时,分,秒 注:是否包含假日
	 * 
	 * @param strstartdate
	 * @param strenddate
	 * @param dateformat
	 * @param retype
	 * @param exclude
	 *            false:包含假日,:true只有工作日,星期一至五
	 * @return
	 */
	public long compareDateExclude(String strstartdate, String strenddate, String dateformat, long retype, boolean exclude) {
		Date sdate = DateValidator.getInstance().validate(strstartdate, dateformat);
		Date edate = DateValidator.getInstance().validate(strenddate, dateformat);
		if (sdate == null || edate == null) {
			return 0;
		}
		return compareDateExclude(sdate, edate, dateformat, retype, exclude);
	}

	/**
	 * 假期
	 * 
	 * @param strstartdate
	 * @param strenddate
	 * @param excludestrdates
	 * @param includestrdates
	 * @param dateformat
	 * @param retype
	 * @return
	 */
	public long compareDateExclude(String strstartdate, String strenddate, String[] excludestrdates, String[] includestrdates, String dateformat, long retype) {
		Date sdate = DateValidator.getInstance().validate(strstartdate, dateformat);
		Date edate = DateValidator.getInstance().validate(strenddate, dateformat);
		if (sdate == null || edate == null) {
			return 0;
		}
		Date excludedates[] = null;
		Date includedates[] = null;
		if (excludestrdates != null) {
			excludedates = new Date[excludestrdates.length];
			int index = 0;
			for (String tmpstr : excludestrdates) {
				excludedates[index] = DateValidator.getInstance().validate(tmpstr, dateformat);
				index ++;
			}
		}
		if (includestrdates != null) {
			includedates = new Date[includestrdates.length];
			int index = 0;
			for (String tmpstr : includestrdates) {
				includedates[index] = DateValidator.getInstance().validate(tmpstr, dateformat);
				index ++;
			}
		}

		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(sdate);
		Calendar ecalendar = Calendar.getInstance();
		ecalendar.setTime(edate);
		long result = 1;
		while (scalendar.compareTo(ecalendar) < 0) {
			// 排除情况
			if (excludedates != null) {
				boolean eflag = false;
				for (Date tmpDate : excludedates) {
					if (tmpDate.compareTo(scalendar.getTime()) == 0) {
						scalendar.add(Calendar.MILLISECOND, (int) retype);
						eflag = true;
						break;
					}
				}
				if (eflag) {
					continue;
				}
			}

			// 包含情况
			if (includedates != null) {
				boolean iflag = false;
				for (Date tmpDate : includedates) {
					if (tmpDate.compareTo(scalendar.getTime()) == 0) {
						scalendar.add(Calendar.MILLISECOND, (int) retype);
						result++;
						iflag = true;
						break;
					} 
				}
				if (iflag) {
					continue;
				}else {
					if (scalendar.get(Calendar.DAY_OF_WEEK) == 7 || scalendar.get(Calendar.DAY_OF_WEEK) == 1) {
						scalendar.add(Calendar.MILLISECOND, (int) retype);
						continue;
					}
				}
			} else {
				if (scalendar.get(Calendar.DAY_OF_WEEK) == 7 || scalendar.get(Calendar.DAY_OF_WEEK) == 1) {
					scalendar.add(Calendar.MILLISECOND, (int) retype);
					continue;
				}
			}

			// 正常情况
			scalendar.add(Calendar.MILLISECOND, (int) retype);
			result++;
		}
		return result;
	}

	/**
	 * 比较日期相关多少年,月,星期,天,时,分,秒 注:是否包含假日
	 * 
	 * @param strstartdate
	 * @param strenddate
	 * @param dateformat
	 * @param retype
	 * @param exclude
	 *            false:包含假日,:true只有工作日,星期一至五
	 * @return
	 */
	public long compareDateExclude(Date sdate, Date edate, String dateformat, long retype, boolean exclude) {
		if (!exclude) {// 包含假日
			return compareDate(sdate, edate, retype);
		}

		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(sdate);
		Calendar ecalendar = Calendar.getInstance();
		ecalendar.setTime(edate);
		long result = 1;
		while (scalendar.compareTo(ecalendar) < 0) {
			if (scalendar.get(Calendar.DAY_OF_WEEK) == 7 || scalendar.get(Calendar.DAY_OF_WEEK) == 1) {
				scalendar.add(Calendar.MILLISECOND, (int) retype);
				continue;
			}
			scalendar.add(Calendar.MILLISECOND, (int) retype);
			result++;
		}
		return result;
	}

/*	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {

		System.out.println(WorkdayUtil.getInstance().compareDateExclude("2012-03-16 12:00", "2012-03-17 14:00", "yyyy-MM-dd HH:mm", WorkdayUtil.RE_DATE, true));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-03-20"));
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		System.out.println(calendar.compareTo(Calendar.getInstance()));

		System.out.println(WorkdayUtil.getInstance().compareDate("2012-03-14 12:10", "2012-03-15 12:10", "yyyy-MM-dd HH:mm", WorkdayUtil.RE_MINUES));

		System.out.println(WorkdayUtil.getInstance().compareDateExclude("2013-01-18", "2013-01-31", "yyyy-MM-dd", WorkdayUtil.RE_DATE, true));
		
		
		String path = AppQueryImp.class.getResource("").getPath();
		Object[]  includestr = IOUtils.readLines(new FileInputStream(path +"/includedate.txt")).toArray();
		Object[]  excludestr = IOUtils.readLines(new FileInputStream(path + "/excludedate.txt")).toArray();
		String excludedates[] = new String[excludestr.length];
		String includedates[] = new String[includestr.length];
		for(int i = 0; i < includestr.length; i++){
			includedates[i] = includestr[i].toString().trim();
		}
		for(int i = 0; i < excludestr.length; i++){
			excludedates[i] = excludestr[i].toString().trim();
		}
		
		System.out.println(WorkdayUtil.getInstance().compareDateExclude("2013.01.05", "2013.01.21",excludedates,includedates, "yyyy.MM.dd", WorkdayUtil.RE_DATE));
		
		BASE64Decoder decoder = new BASE64Decoder();
		System.out.println(decoder.decodeBuffer("5oKo5bCa5pyq55Sz6K+35Yqe55CG55qE5bGF5rCR6Lqr5Lu96K+B5Lia5Yqh").toString());
		
	}*/
	

}
