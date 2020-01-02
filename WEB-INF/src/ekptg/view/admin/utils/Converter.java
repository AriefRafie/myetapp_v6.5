package ekptg.view.admin.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Mohd Nazrul
 * @version 1.0
 *
 */

public class Converter {
		
	/**
	 * Simple converter string 
	 * @param txt
	 * this method created by @author Azam
	 */
	public String getStringValue(String txt) throws Exception {
		if (txt == null) return "";
		else return txt;
	}
		
	/**
	 * Simple converter string to integer
	 * @param txt
	 */
	public int getIntValue(String txt) throws Exception {
		if (txt == null) return 0;
		else return Integer.parseInt(txt);
	}
	
	/**
	 * <h1><b>CONVERTOR DATE</b></h1>
	 * Comment: This converter is for convert the string date into Date format.
	 * 			Please change the format at setDateString method with @param date.
	 */
	private Date date;
	private String formatDate;
	
	/**
	 * 
	 * @param date
	 * @exception Exception
	 */
	public void setDate(Object date) throws Exception {
		if (date instanceof Date)
			this.date = (Date) date;
		else if (date instanceof String)
			setDate(date);
	}
	
	/**
	 * 
	 * @param date
	 * @throws Exception
	 */
	public void setDate(String date) throws Exception {
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy");
		Date st = (Date) simpleDate.parse(date);
		this.formatDate = simpleDate.parse(date).toString();
		setDate(st);
	}

	/**
	 * 
	 * @return {@link Date}
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getFormatDate() {
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public static String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());

    }

	/**
	 * <h1><b>CONVERTER TIME</b></h1>
	 * Comment: This converter is for convert the string time into Date time format.
	 * 			Others include the converter time 24 hours into 12 hours format.
	 */
	private Date sdate;
	
	public void setSDate(Object sdate) {
		if (sdate instanceof Date)
			this.sdate = (Date) sdate;
		else if (sdate instanceof String)
			setSDate(sdate);
	}

	public void setSDate(String sdate) {
		String seperator = "/";
		int day = Integer.parseInt(sdate.substring(0,
				sdate.indexOf(seperator)).trim()) - 1;
		int month = Integer.parseInt(sdate.substring(
				sdate.indexOf(seperator) + 1, sdate.lastIndexOf(seperator))
				.trim());
		int year = Integer.parseInt(sdate.substring(
				sdate.lastIndexOf(seperator) + 1).trim());
		setSDate(new GregorianCalendar(year, month, day).getTime());
	}

	public Date getSDate() {
		return sdate;
	}
	


	
	

}
