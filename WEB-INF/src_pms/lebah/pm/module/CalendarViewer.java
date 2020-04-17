package lebah.pm.module;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarViewer {
	
	private int year;
	private int month;
	private int day;
	private int days;
	private int dayOfWeek;
	private String dateAsString;
	private Calendar calendar;
	
	public CalendarViewer() {
		this.calendar = Calendar.getInstance();
	}
	
	public CalendarViewer(Calendar calendar) {
		this.calendar = calendar;
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		dateAsString = df.format(calendar.getTime());
		dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.getMinimum(Calendar.DAY_OF_WEEK);
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDateAsString() {
		return dateAsString;
	}

	public void setDateAsString(String dateAsString) {
		this.dateAsString = dateAsString;
	}
	
	public int getStartDay() {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = 1;
		String strDate = day < 10 ? "0" + day + "-": day + "-";
		strDate += month < 10 ? "0" + month + "-": month + "-";
		strDate += year;
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int startDay = c.get(Calendar.DAY_OF_WEEK);
		return startDay;
	}
	
	public String getMonthName() {
		int m = month - 1;
		String[] monthNames = {"January","February","March","April","May","Jun","July","August","September","October","November","December"};
		return monthNames[m];
	}
	
	public String getDayName() {
		String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		return dayNames[day];
	}
	
	public Date getDate() {
		return calendar.getTime();
	}
	
	public static void main(String[] args) throws Exception {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = 1;
		String strDate = day < 10 ? "0" + day + "-": day + "-";
		strDate += month < 10 ? "0" + month + "-": month + "-";
		strDate += year;
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
		c.setTime(date);
	}
}
