package com.appium.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class StringUtil {

	public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    }
	
	public static String getRandomNumber() {
        final Random rand = new Random();
        return String.valueOf(rand.nextInt(10000) + 1);
    }
}
