package com.faceye.feature.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil
{
	public static String date2String(Date date)
	{
		String result = "";
		if (null != date)
		{
			Date now = new Date();
			long period = now.getTime() - date.getTime();
			long minutesPeriod = period / (1000 * 60);
			if (minutesPeriod <= 5)
			{
				result = "刚刚";
			} else if (minutesPeriod > 5 && minutesPeriod <= 60)
			{
				result = minutesPeriod + "分钟前";
			} else if (minutesPeriod > 60 && minutesPeriod <= 24 * 60)
			{
				result = minutesPeriod / 60 + "小时前";
			} 
				else if (minutesPeriod > 24 * 60 && minutesPeriod <= 24 * 60 * 7)
			{
//				result = minutesPeriod / (24 * 60) + "天前";
				result=formatDate(date,"MM-dd");
			} else if (minutesPeriod > 24 * 60 * 7 && minutesPeriod <= 24 * 60 * 7 * 4)
			{
//				result = minutesPeriod / (24 * 60 * 7) + "周前";
				result=formatDate(date,"MM-dd");
			} else if (minutesPeriod >24 * 60 * 7 * 4 && minutesPeriod <= 24 * 60 * 365)
			{
//				result = minutesPeriod / (24 * 60 * 30) + "个月前";
				result=formatDate(date,"MM-dd");
			} else if (minutesPeriod >= 24 * 60 * 365)
			{
//				result = minutesPeriod / (24 * 60 * 365) + "年前";
				result=formatDate(date,"yyyy-MM-dd");
			}
			else
			{
				result = formatDate(date);
			}
		} else
		{
			result = "";
		}
		return result;
	}

	public static String date2String(Object dateString)
	{
		if(dateString==null){
			return "";
		}
		Date date = getDateFromString(dateString.toString());
		String date2String = date2String(date);
		return date2String;
	}

	public static String formatDate(Date date)
	{
		return formatDate(date, null);
	}

	public static Date getDateFromString(String dateString, String pattern)
	{
		Date date = null;
		if (StringUtils.isEmpty(pattern))
		{
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (StringUtils.isNotEmpty(dateString))
		{
			try
			{
				date = sdf.parse(dateString);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		return date;
	}

	public static Date getDateFromString(String dateString)
	{
		return getDateFromString(dateString, null);
	}
	

	public static String formatDate(Date date, String pattern)
	{
		String format = "";
		if (StringUtils.isEmpty(pattern))
		{
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (null != date)
		{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			format = sdf.format(date);
		}
		return format;
	}

	public static Date getStartDateByTimePeriod(String timePeriod)
	{
		Long times = null;
		Date startDate = null;
		if (StringUtils.isNotEmpty(timePeriod))
		{
			times = StringPool.PERIOD.get(timePeriod.toUpperCase());
		}
		if (null != times)
		{
			startDate = new Date(System.currentTimeMillis() - times);
		}
		return startDate;
	}

	public static void main(String[] args)
	{
       Date date=DateUtil.getDateFromString("2014-10-05 5:00:00");
       System.out.println(date.getTime());
       Date date2=DateUtil.getDateFromString("2014-10-12 23:00:00");
       System.out.print(date2.getTime());
	}
}
