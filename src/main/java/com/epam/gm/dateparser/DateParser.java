package com.epam.gm.dateparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateParser {

	public static Date StringToSqlDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date parsed;

		try {
			parsed = format.parse(str);
			System.out.println("parsed" + parsed);

			System.out.println(new java.sql.Date(parsed.getTime()));
			return parsed;
		} catch (ParseException e) {

		}

		// return new java.sql.Date(115, 0, 1);
		return null;
	}

	public static String SqlDateToString(Date date) {
		String res = "";
		System.out.println(date);
		if (date != null) {
			String[] s = date.toString().split("\\s");
			//

			

			res = s[0];

		}

		return res;
	}

	public static String SqlHourToString(Date date) {
		String res = "";
		System.out.println(date);
		if (date != null) {
			String[] s = date.toString().split("\\s");
			String[] time = s[1].split(":");			

			res = time[0];
		}

		return res;

	}
	
	public static String SqlMinuteToString(Date date) {
		String res = "";
		System.out.println(date);
		if (date != null) {
			String[] s = date.toString().split("\\s");
			String[] time = s[1].split(":");			

			res = time[1];
		}

		return res;

	}

	public static Date trim(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);

		return calendar.getTime();
	}
}