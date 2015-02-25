package com.psu.helper;

import android.util.Log;

public class Controls {
	public  static String getDate(String date)
	{
		Log.i("getDate",date);
		if(date.trim().equals("")) return "";
		String year = date.split("-")[2];
		String month = date.split("-")[1];
		String day = date.split("-")[0];
		return year+"-"+month+"-"+day;
	}
}
