package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	public static Date parseDate(String dateString) {
	
		Date date = new Date();
		try {
			date = formatter.parse("16-07-2000 20:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String makeDateString(Date date) {
		return formatter.format(date);
	}
}
