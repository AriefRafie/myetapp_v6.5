package lebah.pm.module;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;

public class CalendarRequest {
	
	private HttpServletRequest request;
	private VelocityContext context;
	private String path = "pms/calendar/";
	
	public CalendarRequest(HttpServletRequest req, VelocityContext con) {
		this.request = req;
		this.context = con;
	}
	
	
	public String showCalendar() {
		Calendar calendar = Calendar.getInstance();
		CalendarViewer cv = new CalendarViewer(calendar);
		String elementId = request.getParameter("element_id");
		context.put("element_id", elementId);
		String divId = request.getParameter("div_id");
		context.put("div_id", divId);
		context.put("calendar_viewer", cv);
		context.put("year_selected", Integer.toString(calendar.get(Calendar.YEAR)));
		context.put("month_selected", Integer.toString(calendar.get(Calendar.MONTH) + 1));
		
		//year range, before or after this year
		String yearRange = request.getParameter("year_range");
		context.put("year_range", yearRange);
		context.put("year_now", calendar.get(Calendar.YEAR));
		
		String _today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		context.put("_today", _today);
		
		
		
		return path + "start.vm";
	}
	
	public String getNext() throws Exception {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		int day = 1;
		month = month + 1;
		if ( month == 13 ) {
			month = 1;
			year = year + 1;
		}
		return getCalendar(day, month, year);
	}
	
	public String getPrevious() throws Exception {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		int day = 1;
		month = month - 1;
		if ( month == 0 ) {
			month = 12;
			year = year - 1;
		}
		return getCalendar(day, month, year);
	}
	
	public String getMonth() throws Exception {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		int day = 1;
		return getCalendar(day, month, year);
	}
	
	public String getToday() throws Exception {
		return showCalendar();
	}

	public String getCalendar(int day, int month, int year) throws Exception {
		context.put("year_selected", Integer.toString(year));
		context.put("month_selected", Integer.toString(month));
		Calendar calendar = Calendar.getInstance();
		String strDate = day < 10 ? "0" + day + "-": day + "-";
		strDate += month < 10 ? "0" + month + "-": month + "-";
		strDate += year;
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
		calendar.setTime(date);
		CalendarViewer cv = new CalendarViewer(calendar);
		context.put("calendar_viewer", cv);
		return path + "start.vm";
	}


}
