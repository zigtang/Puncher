package com.zig.puncher.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static DateUtil dateUtil;
	private final Calendar calendar;

	private DateUtil() {
		calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(new Date());
	}

	public static DateUtil getInstance() {
		if (dateUtil == null) {
			dateUtil = new DateUtil();
		}
		return dateUtil;
	}

	public String getYearMonthWeek() {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		return year + "年" + month + "月第" + week + "周";
	}

	public int getTodayIndex() {
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public  String getTodayDate() {
		long time = System.currentTimeMillis();
		DateFormat df = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.CHINA);// 12,DEC,2013
		return df.format(new Date(time));
	}

	public  String getCurrentTime() {
		long time = System.currentTimeMillis();
		DateFormat df = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT, Locale.CHINA);// 上午12:00
		return df.format(new Date(time));
	}

	public  String getTimeDetail() {//
		long time = System.currentTimeMillis();
		DateFormat df = SimpleDateFormat.getDateInstance(SimpleDateFormat.DEFAULT, Locale.CHINA);// 2013-12-12
		String dateStr = df.format(new Date(time));
		return dateStr + "：" + getCurrentTime();
	}

}
