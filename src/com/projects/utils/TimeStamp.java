package com.projects.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeStamp {
	
	public static String getTimeStampt()
	{
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(Calendar.getInstance().getTime());
		return timeStamp;	
	}
	
	public static String getSimpleTimeStampt()
	{
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss:SS").format(Calendar.getInstance().getTime());
		return timeStamp;	
	}

}
