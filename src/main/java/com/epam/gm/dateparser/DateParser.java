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
			System.out.println("parsed"+parsed);
			
			System.out.println(new java.sql.Date(parsed.getTime()));
			return parsed;
		} catch (ParseException e) {

		}

		// return new java.sql.Date(115, 0, 1);
		return null;
	}

	public static String SqlDateToString(Date date) {
		String res = "";

		if (date != null) {
			String[] s = date.toString().split("-");
			// 2015-06-10 to 06/10/2015

			res = new StringBuilder().append(s[1]).append("/").append(s[2])
					.append("/").append(s[0]).toString();

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