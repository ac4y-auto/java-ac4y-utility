package ac4y.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {
	
	private static String DATEFORMAT = "yyyy-MM-dd";
	private static String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public Date getShiftedDate(Date aDate, int aShift) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(aDate);
		calendar.add(Calendar.DATE, aShift);
		
		return calendar.getTime();
		
	} // getShiftedDate

	public Date getDateFromString(String aDate) throws ParseException{
		
		Date result = null;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATEFORMAT);
		
		result = simpleDateFormat.parse(aDate);

		return result;
		
	} // getDateFromString

	public Date getTimeFromString(String aTime) throws ParseException{
		
		Date result = null;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIMEFORMAT);
		
		result = simpleDateFormat.parse(aTime);

		return result;
		
	} // getTimeFromString


	public String getStringFromDate(Date aDate){
		
		String result = "";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATEFORMAT);
		
		return simpleDateFormat.format(aDate);
			
	} // getStringFromDate

	public String getStringFromTime(Date aDate){
		
		String result = "";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIMEFORMAT);
		
		return simpleDateFormat.format(aDate);
			
	} // getStringFromTime
	
} // DateHandler