
package com.ihsinformatics.tbreach3tanzania.mobile.util;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil
{

	public static String getCurrentDate ()
	{
		Calendar c = Calendar.getInstance ();
		c.setTime (new Date (System.currentTimeMillis ()));

		String year = new Integer (c.get (Calendar.YEAR)).toString ();
		String month = new Integer (c.get (Calendar.MONTH) + 1).toString ();
		String date = new Integer (c.get (Calendar.DATE)).toString ();

		String dateString = "";

		dateString = date.length () == 2 ? dateString + date : dateString + "0" + date;
		dateString += "/";
		dateString = month.length () == 2 ? dateString + month : dateString + "0" + month;
		dateString += "/" + year;

		return dateString;
	}

	public static String getDate (Date d)
	{
		Calendar c = Calendar.getInstance ();
		c.setTime (d);

		String year = new Integer (c.get (Calendar.YEAR)).toString ();
		String month = new Integer (c.get (Calendar.MONTH) + 1).toString ();
		String date = new Integer (c.get (Calendar.DATE)).toString ();

		String dateString = year + "-";
		dateString = month.length () == 2 ? dateString + month : dateString + "0" + month;
		dateString += "-";
		dateString = date.length () == 2 ? dateString + date : dateString + "0" + date;
		return dateString;
	}

	public static String getTime (Date d)
	{
		String timeString = "";
		Calendar c = Calendar.getInstance ();
		c.setTime (d);

		c.setTime (new Date (System.currentTimeMillis ()));
		String hour = new Integer (c.get (Calendar.HOUR_OF_DAY)).toString ();
		System.out.println (hour);
		String minute = new Integer (c.get (Calendar.MINUTE)).toString ();
		String second = new Integer (c.get (Calendar.SECOND)).toString ();

		timeString = hour.length () == 2 ? timeString + hour : timeString + "0" + hour;
		timeString += ":";
		timeString = minute.length () == 2 ? timeString + minute : timeString + "0" + minute;
		timeString += ":";
		timeString = second.length () == 2 ? timeString + second : timeString + "0" + second;

		return timeString;
	}

	public static String getDateTime (Date d)
	{
		String date = getDate (d);
		String time = getTime (d);
		return date + " " + time;
	}

	public static boolean isDateInFuture (Date date)
	{
		boolean result = false;

		Date nowDate = new Date ();
		if (date.getTime () > nowDate.getTime ())
			return true;

		return result;
	}
	
	public static boolean isDateAfter (Date date1 , Date date2)
	{
		boolean result = false;
		
		if(date1.getTime() > date2.getTime ())
		   return true;
		
		return result;
	}
	
	

}
