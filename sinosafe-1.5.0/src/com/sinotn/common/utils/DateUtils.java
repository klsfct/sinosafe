package com.sinotn.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Title: 系统时间公共类
 * </p>
 * <li>提供取得系统时间的所有共用方法</li> <br>
 * 
 */
public class DateUtils {

	private static Date date;

	private static Calendar CALENDAR = Calendar.getInstance();

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

	public static final String[] C_WEAK = { "星期日", "星期一", "星期二", "星期三", "星期四","星期五", "星期六" };

	public static final String C_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String C_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String C_YYYYMMDD = "yyyyMMdd";

	public static final String C_HHMMSS = "HH:mm:ss";

	public static final String C_YYYY = "yyyy";
	
	public static final String C_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	public static final String C_YYYYMMDDHHMM = "yyyy年MM月dd日  HH:mm";
	/**
	 * 取得当前时间
	 * 
	 * @return 当前日期（Date）
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 取得昨天此时的时间
	 * 
	 * @return 昨天日期（Date）
	 */
	public static Date getYesterdayDate() {
		return new Date(getCurTimeMillis() - 0x5265c00L);
	}

	/**
	 * 取得去过i天的时间
	 * 
	 * @param i
	 *            过去时间天数
	 * @return 昨天日期（Date）
	 */
	public static Date getPastdayDate(int i) {
		return new Date(getCurTimeMillis() - 0x5265c00L * i);
	}

	/**
	 * 取得当前时间的长整型表示
	 * 
	 * @return 当前时间（long）
	 */
	public static long getCurTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 取得当前时间的特定表示格式的字符串
	 * 
	 * @param formatDate
	 *            时间格式（如：yyyy/MM/dd hh:mm:ss）
	 * @return 当前时间
	 */
	public static synchronized String getCurFormatDate(String formatDate) {
		date = getCurrentDate();
		simpleDateFormat.applyPattern(formatDate);
		return simpleDateFormat.format(date);
	}

	/**
	 * 取得某日期时间的特定表示格式的字符串
	 * 
	 * @param format
	 *            时间格式
	 * @param date
	 *            某日期（Date）
	 * @return 某日期的字符串
	 */
	public static synchronized String getDate2Str(String format, Date date) {
		simpleDateFormat.applyPattern(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * 将日期转换为长字符串（包含：年-月-日 时:分:秒）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy-MM-dd HH:mm:ss 的字符串
	 */
	public static String getDate2LStr(Date date) {
		return getDate2Str("yyyy-MM-dd HH:mm:ss", date);
	}

	/**
	 * 将日期转换为长字符串（包含：年/月/日 时:分:秒）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy/MM/dd HH:mm:ss 的字符串
	 */
	public static String getDate2LStr2(Date date) {
		return getDate2Str("yyyy/MM/dd HH:mm:ss", date);
	}

	/**
	 * 将日期转换为中长字符串（包含：年-月-日 时:分）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy-MM-dd HH:mm 的字符串
	 */
	public static String getDate2MStr(Date date) {
		return getDate2Str("yyyy-MM-dd HH:mm", date);
	}

	/**
	 * 将日期转换为中长字符串（包含：年/月/日 时:分）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy/MM/dd HH:mm 的字符串
	 */
	public static String getDate2MStr2(Date date) {
		return getDate2Str("yyyy/MM/dd HH:mm", date);
	}

	/**
	 * 将日期转换为短字符串（包含：年-月-日）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy-MM-dd 的字符串
	 */
	public static String getDate2SStr(Date date) {
		return getDate2Str("yyyy-MM-dd", date);
	}

	/**
	 * 将日期转换为短字符串（包含：年/月/日）
	 * 
	 * @param date
	 *            日期
	 * @return 返回型如：yyyy/MM/dd 的字符串
	 */
	public static String getDate2SStr2(Date date) {
		return getDate2Str("yyyy/MM/dd", date);
	}

	/**
	 * 取得型如：yyyyMMddhhmmss的字符串
	 * 
	 * @param date
	 * @return 返回型如：yyyyMMddhhmmss 的字符串
	 */
	public static String getDate2All(Date date) {
		return getDate2Str("yyyyMMddhhmmss", date);
	}

	/**
	 * 将长整型数据转换为Date后，再转换为型如yyyy-MM-dd HH:mm:ss的长字符串
	 * 
	 * @param l
	 *            表示某日期的长整型数据
	 * @return 日期型的字符串
	 */
	public static String getLong2LStr(long l) {
		date = getLongToDate(l);
		return getDate2LStr(date);
	}

	/**
	 * 将长整型数据转换为Date后，再转换为型如yyyy-MM-dd的长字符串
	 * 
	 * @param l
	 *            表示某日期的长整型数据
	 * @return 日期型的字符串
	 */
	public static String getLong2SStr(long l) {
		date = getLongToDate(l);
		return getDate2SStr(date);
	}

	/**
	 * 将长整型数据转换为Date后，再转换指定格式的字符串
	 * 
	 * @param l
	 *            表示某日期的长整型数据
	 * @param formatDate
	 *            指定的日期格式
	 * @return 日期型的字符串
	 */
	public static String getLong2SStr(long l, String formatDate) {
		date = getLongToDate(l);
		simpleDateFormat.applyPattern(formatDate);
		return simpleDateFormat.format(date);
	}

	public static synchronized Date getStrToDate(String format, String str) {
		simpleDateFormat.applyPattern(format);
		try {
			return simpleDateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将某指定的字符串转换为某类型的字符串
	 * 
	 * @param format
	 *            转换格式
	 * @param str
	 *            需要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String getStr2Str(String format, String str) {
		Date date = getStrToDate(format, str);
		return getDate2Str(format, date);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm:ss的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2LDate(String str) {
		return getStrToDate("yyyy-MM-dd HH:mm:ss", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy/MM/dd HH:mm:ss的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2LDate2(String str) {
		return getStrToDate("yyyy/MM/dd HH:mm:ss", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2MDate(String str) {
		return getStrToDate("yyyy-MM-dd HH:mm", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy/MM/dd HH:mm的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2MDate2(String str) {
		return getStrToDate("yyyy/MM/dd HH:mm", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy-MM-dd的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2SDate(String str) {
		return getStrToDate("yyyy-MM-dd", str);
	}

	/**
	 * 将某指定的字符串转换为型如：yyyy-MM-dd的时间
	 * 
	 * @param str
	 *            将被转换为Date的字符串
	 * @return 转换后的Date
	 */
	public static Date getStr2SDate2(String str) {
		return getStrToDate("yyyy/MM/dd", str);
	}

	/**
	 * 将某长整型数据转换为日期
	 * 
	 * @param l
	 *            长整型数据
	 * @return 转换后的日期
	 */
	public static Date getLongToDate(long l) {
		return new Date(l);
	}

	/**
	 * 以分钟的形式表示某长整型数据表示的时间到当前时间的间隔
	 * 
	 * @param l
	 *            长整型数据
	 * @return 相隔的分钟数
	 */
	public static int getOffMinutes(long l) {
		return getOffMinutes(l, getCurTimeMillis());
	}

	/**
	 * 以分钟的形式表示两个长整型数表示的时间间隔
	 * 
	 * @param from
	 *            开始的长整型数据
	 * @param to
	 *            结束的长整型数据
	 * @return 相隔的分钟数
	 */
	public static int getOffMinutes(long from, long to) {
		return (int) ((to - from) / 60000L);
	}

	/**
	 * 以微秒的形式赋值给一个Calendar实例
	 * 
	 * @param l
	 *            用来表示时间的长整型数据
	 */
	public static void setCalendar(long l) {
		CALENDAR.clear();
		CALENDAR.setTimeInMillis(l);
	}

	/**
	 * 以日期的形式赋值给某Calendar
	 * 
	 * @param date
	 *            指定日期
	 */
	public static void setCalendar(Date date) {
		CALENDAR.clear();
		CALENDAR.setTime(date);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某年
	 */
	public static int getYear() {
		return CALENDAR.get(1);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某月
	 */
	public static int getMonth() {
		return CALENDAR.get(2) + 1;
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某天
	 */
	public static int getDay() {
		return CALENDAR.get(5);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某小时
	 */
	public static int getHour() {
		return CALENDAR.get(11);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某分钟
	 */
	public static int getMinute() {
		return CALENDAR.get(12);
	}

	/**
	 * 在此之前要由一个Calendar实例的存在
	 * 
	 * @return 返回某秒
	 */
	public static int getSecond() {
		return CALENDAR.get(13);
	}

	/*****************************************************
	 * 根据指定的日期获得对应的星期字符串
	 * 
	 * @param vdate
	 *            指定的日期字符，格式yyyy-MM-dd或yyyy-MM-dd hh:mm:ss
	 * @return 日期字符串，如果给定的日期字符串不符合要求返回null
	 ****************************************************/
	public static String getWeek(String vdate) {
		Date date = null;
		try {
			date = getStr2SDate(vdate);
			if (date == null) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return C_WEAK[i];
	}

	/*****************************************************
	 * 取得指定日期N天后的日期
	 * 
	 * @param date
	 *            指定时间
	 * @param days
	 *            天数
	 * @return 指定时间days天后的日期对象
	 ****************************************************/
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	/*****************************************************
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return 两个日期相差的天数
	 ****************************************************/
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / 86400000;// (1000 * 3600 * 24 =
		// 86400000);

		return Integer.parseInt(String.valueOf(between_days));
	}
	/**
	 * 功能：格式化日期。 创建日期：(2002-8-18 下午 09:47:08)
	 * 
	 * @param vDate  :(日期对象java.util.Date)
	 * @param i      :  1 则返回YYYY-MM-DD格式的日期字符。 i = 2
	 *                                   则返回YYYY-MM-DD HH:MM:SS格式的日期字符。 i = 3
	 *                                   则返回YYYY格式的日期字符。 否则返回YYYY-MM-DD格式的日期字符。
	 * @return java.lang.String
	 */
	public static final String formatDateTime(java.util.Date vDate, String formate) {		
		if (vDate == null)
			return null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(formate);
		String dateString = formatter.format(vDate);
		return dateString;
	}
	public static void  main(String[] args){
		System.out.println(DateUtils.getYear());
	}
}
