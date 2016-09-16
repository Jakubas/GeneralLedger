package my.generalledger.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Utils {

	public static Calendar stringToCalendar(String calendarStr) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			cal.setTime(sdf.parse(calendarStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}
	
	/* remove everything that isn't a numeric value from a String and return an Integer, 
	   only supports positive values */
	public static int CurrencyStringToInt(String currencyStr) {
		currencyStr = currencyStr.replaceAll("[^0-9]", "");
		return Integer.valueOf(currencyStr);
	}

}
