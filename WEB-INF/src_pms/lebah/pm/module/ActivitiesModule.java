package lebah.pm.module;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import lebah.pm.entity.ActivityEvent;
import lebah.pm.entity.UserActivityEvent;
import lebah.portal.action.Command;
import lebah.portal.action.LebahModule;
import lebah.template.DbPersistence;

public class ActivitiesModule extends LebahModule {
	
	private String path = "pms/activities";
	private String vm = "";
	private String userId = "";
	private DbPersistence db = new DbPersistence();
	//private String flag_addevent = "";
	
	public void preProcess() {
		
		System.out.println("command=" + command);
		
		context.put("path", path);
		context.put("dateFormat", new SimpleDateFormat("dd-MM-yyyy"));
		context.put("timeFormat", new SimpleDateFormat("hh:mm a"));	
		context.put("numFormat", new DecimalFormat("#.00"));
		userId = (String) request.getSession().getAttribute("_portal_login");
		
		//System.out.println("userId==="+userId);
		context.put("userId", userId);
		
		//get today
		context.put("today", new Date());
	}

	@Override
	public String start() {
		Date eventDate = new Date();
		getListOfEvents(eventDate);
		
		return path + "/start.vm";
	}

	private void prepareTimes() {
		Date today = new Date();
		String _today = new SimpleDateFormat("dd-MM-yyyy").format(today);
		Date date = parseDateTime(_today + " 06:00 AM");
		//set interval
		int interval = 15;
		List<Date> times = new ArrayList<Date>();
		context.put("times", times);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for ( int i=0; i < 73; i++ ) {
			times.add(cal.getTime());
			cal.add(Calendar.MINUTE, interval);
		}
	}

	
	public static Date parseDateTime(String dateTxt) {
		if ( dateTxt != null && !"".equals(dateTxt)) {
			try {
				
				dateTxt = dateTxt.replace("/", "-");
				return new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(dateTxt);
				
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}
	
	public static Date parseDate(String dateTxt) {
		if ( dateTxt != null && !"".equals(dateTxt)) {
			try {
				
				dateTxt = dateTxt.replace("/", "-");
				return new SimpleDateFormat("dd-MM-yyyy").parse(dateTxt);
				
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}
	
	private String get(String s) {
		return request.getParameter(s) != null ? request.getParameter(s) : "";
	}
	
	@Command("save_activity_event")
	public String saveActivityEvent() throws Exception {
		context.put("flag_addevent", "");
		//find UserActivityEvent
		UserActivityEvent userActivityEvent = (UserActivityEvent) db.get("select u from UserActivityEvent u where u.userLogin = '" + userId + "'");
		if ( userActivityEvent == null ) {
			db.begin();
			userActivityEvent = new UserActivityEvent();
			userActivityEvent.setUserLogin(userId);
			db.persist(userActivityEvent);
			db.commit();
		}
		
		String activityEventId = get("activityEventId");
		
		
		String description = get("description");
		String locationRemark = get("locationRemark");
		String remark = get("remark");
		String displayColor = get("displayColor");

		String eventDateStart_ = get("eventDate");
		String startTime = get("startTime");
		Date startDateTime = parseDateTime(eventDateStart_ + " " + startTime);
		
		String eventDateEnd_ = get("eventDate");
		String endTime = get("endTime");
		Date endDateTime = parseDateTime(eventDateEnd_ + " " + endTime);
		
		ActivityEvent activityEvent = null;
		if ( !"".equals(activityEventId)) activityEvent = db.find(ActivityEvent.class, Long.parseLong(activityEventId));
		else activityEvent = new ActivityEvent();
		
		db.begin();
		
		activityEvent.setDescription(description);
		activityEvent.setEventDate(parseDate(get("eventDate")));
		activityEvent.setStartDateTime(startDateTime);
		activityEvent.setEndDateTime(endDateTime);
		activityEvent.setLocationRemark(locationRemark);
		activityEvent.setRemark(remark);
		activityEvent.setDisplayColor(displayColor);
		if ( "".equals(activityEventId)) {
			activityEvent.setUserActivityEvent(userActivityEvent);
			activityEvent.setCreateDate(new Date());
			db.persist(activityEvent);
			if ( userActivityEvent.getEvents() == null ) userActivityEvent.setEvents(new ArrayList<ActivityEvent>());
			userActivityEvent.getEvents().add(activityEvent);
		}
		
		db.commit();

		Date eventDate = parseDate(get("eventDate"));
		getListOfEvents(eventDate);
		
		return path + "/calendar_events.vm";
	}
	
	
	@Command("save_activity_event_long")//aishahlatip
	public String saveActivityEventLong() throws Exception {
		
		//flag_addevent = get("flag_addevent");
		System.out.println("userId========"+userId);
		context.put("flag_addevent", "Y");
		//find UserActivityEvent
		UserActivityEvent userActivityEvent = (UserActivityEvent) db.get("select u from UserActivityEvent u where u.userLogin = '" + userId + "'");
		if ( userActivityEvent == null ) {
			db.begin();
			userActivityEvent = new UserActivityEvent();
			userActivityEvent.setUserLogin(userId);
			db.persist(userActivityEvent);
			db.commit();
		}
		
		
		String masaMula = "";
		String activityEventId = get("activityEventId");
		
		String description = get("description2");
		String locationRemark = get("locationRemark2");
		String remark = get("remark2");
		String displayColor = get("displayColor2");

		String eventDateStart_ = get("eventDate");
		String startTime = get("startTime2");
		//Date startDateTime = parseDateTime(eventDateStart_ + " " + startTime);
		
		String tarikhMula = get("txtTarikhMula");
		String tarikhTamat = get("txtTarikhTamat");
		
		String eventDateEnd_ = get("eventDate");
		String endTime = get("endTime2");
		//Date endDateTime = parseDateTime(eventDateEnd_ + " " + endTime);
		//System.out.println("event date========"+eventDateStart_);
//		ActivityEvent activityEvent = null;
//		if ( !"".equals(activityEventId)) activityEvent = db.find(ActivityEvent.class, Long.parseLong(activityEventId));
//		else activityEvent = new ActivityEvent();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		
		Calendar calMula = new GregorianCalendar();
		Date dateMula = sdf.parse(sdf.format(sdf.parse(tarikhMula)));
		calMula.setTime(dateMula);
		
		Calendar calTamat = new GregorianCalendar();
		Date dateTamat = sdf.parse(sdf.format(sdf.parse(tarikhTamat)));
		calTamat.setTime(dateTamat);
		
		db.begin();
		
		
		while (calMula.getTime().before(calTamat.getTime())) {
			ActivityEvent activityEvent = null;
			if ( !"".equals(activityEventId)) activityEvent = db.find(ActivityEvent.class, Long.parseLong(activityEventId));
			else activityEvent = new ActivityEvent();
			
			tarikhMula = sdf.format(calMula.getTime());
			
			Date startDateTime = parseDateTime(tarikhMula + " " + startTime);
			//Date endDateTime = parseDateTime(tarikhMula + " " + "12:00 AM");	
			Date endDateTime = parseDateTime(tarikhTamat + " " + endTime);	//26/4/2017
			
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark(remark);
			activityEvent.setDisplayColor(displayColor);
			if ( "".equals(activityEventId)) {
				activityEvent.setUserActivityEvent(userActivityEvent);
				activityEvent.setCreateDate(new Date());
				db.persist(activityEvent);
				if ( userActivityEvent.getEvents() == null ) userActivityEvent.setEvents(new ArrayList<ActivityEvent>());
				userActivityEvent.getEvents().add(activityEvent);
			}
			
			calMula.add(Calendar.DATE, 1);
			masaMula = "0600";
		}
		
		if (calTamat.getTime().compareTo(calMula.getTime()) == 0){
			ActivityEvent activityEvent = null;
			if ( !"".equals(activityEventId)) activityEvent = db.find(ActivityEvent.class, Long.parseLong(activityEventId));
			else activityEvent = new ActivityEvent();
		
			tarikhMula = sdf.format(calMula.getTime());
			
			Date startDateTime = parseDateTime(tarikhMula + " " + startTime);
			Date endDateTime = parseDateTime(tarikhTamat + " " + endTime);	
			
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark(remark);
			activityEvent.setDisplayColor(displayColor);
			if ( "".equals(activityEventId)) {
				activityEvent.setUserActivityEvent(userActivityEvent);
				activityEvent.setCreateDate(new Date());
				db.persist(activityEvent);
				if ( userActivityEvent.getEvents() == null ) userActivityEvent.setEvents(new ArrayList<ActivityEvent>());
				userActivityEvent.getEvents().add(activityEvent);
			}
			
			
		} 	
		
		db.commit();
	
		Date eventDate = parseDate(get("eventDate"));
		getListOfEvents(eventDate);
		
		
		return path + "/calendar_events.vm";
		
	}
	
	@Command("delete_activity_event")
	public String deleteActivityEvent() throws Exception {
		context.put("flag_addevent", "");
		String activityEventId = get("deleteEventId");
		ActivityEvent e = db.find(ActivityEvent.class, Long.parseLong(activityEventId));
		UserActivityEvent u = e.getUserActivityEvent();
		db.begin();
		u.getEvents().remove(e);
		db.remove(e);
		db.commit();
		
		Date eventDate = parseDate(get("eventDate"));
		getListOfEvents(eventDate);
		
		return path + "/calendar_events.vm";
		
	}
	
	@Command("edit_activity_event")
	public String editActivityEvent() throws Exception {
		String activityEventId = get("editEventId");
		ActivityEvent e = db.find(ActivityEvent.class, Long.parseLong(activityEventId));
		context.put("activityEvent", e);
		prepareTimes();
		return path + "/event_entry.vm";
	}

	
	@Command("list_activity_event")
	public String listActivityEvent() throws Exception {
		context.put("flag_addevent", "");
		Date eventDate = parseDate(get("selectDate"));
		getListOfEvents(eventDate);
		return path + "/calendar_events.vm";
	}

	
	@Command("list_events")
	public String listEvents() throws Exception {
		context.put("flag_addevent", "");
		Date eventDate = parseDate(get("selectDate"));
		getListOfEvents(eventDate);
		return path + "/calendar_events.vm";
	}
	

	private void getListOfEvents(Date eventDate) {
		Date today = new Date();
		String _today = new SimpleDateFormat("dd-MM-yyyy").format(today);
		Date date = parseDateTime(_today + " 06:00 AM");
		int interval = 15;
		List<Date> times = new ArrayList<Date>();
		context.put("times", times);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for ( int i=0; i < 73; i++ ) {
			times.add(cal.getTime());
			cal.add(Calendar.MINUTE, interval);
		}
		
		Hashtable h = new Hashtable();
		h.put("eventDate", eventDate);
		context.put("eventDate", eventDate);
		List<ActivityEvent> events = db.list("select e from ActivityEvent e where e.eventDate = :eventDate and e.userActivityEvent.userLogin = '" + userId + "' order by e.startDateTime, e.createDate", h);
		context.put("events", events);
		System.out.println("eventDate calendar=="+eventDate);
		System.out.println("event calendar=="+userId);
		System.out.println("events=="+events);
		
		List<List<ActivityEvent>> conflicts = new ArrayList<List<ActivityEvent>>();
		List<ActivityEvent> eventList = new ArrayList<ActivityEvent>();
		eventList.addAll(events);
		boolean b = true;
		
		List<List<ActivityEvent>> calendarEvents = new ArrayList<List<ActivityEvent>>();
		context.put("calendarEvents", calendarEvents);
		while ( b ) {
			List<ActivityEvent> results = new ArrayList<ActivityEvent>();
			List<ActivityEvent> conflictEvents = checkEvents2(times, eventList, results);
			calendarEvents.add(results);
			if ( conflictEvents.size() == 0 ) break;
			conflicts.add(conflictEvents);
			eventList.clear();
			eventList.addAll(conflictEvents);
		}
		

	}
	
	private static List<ActivityEvent> checkEvents(List<Date> times, List<ActivityEvent> events) {
		List<ActivityEvent> conflictEvents = new ArrayList<ActivityEvent>();
		SimpleDateFormat f = new SimpleDateFormat("hh:mm a");
		int i = 0;
		boolean hasBegin = false, hasEnd = false;
		for ( Date d : times ) {
			if ( i < events.size() ) {
				ActivityEvent e = events.get(i);
				if ( f.format(e.getStartDateTime()).equals(f.format(d))) {
					hasBegin = true;
				}
				if ( f.format(e.getEndDateTime()).equals(f.format(d))) {
					if ( !hasBegin ) {
						conflictEvents.add(e);
					}
					hasBegin = false;
					i++;
				}
				if ( i < events.size() ) {
					e = events.get(i);
					if ( f.format(e.getStartDateTime()).equals(f.format(d))) {
						hasBegin = true;
					}
				}
			}
		}
		return conflictEvents;
	}
	
	private static List<ActivityEvent> checkEvents2(List<Date> times, List<ActivityEvent> events, List<ActivityEvent> results) {
		List<ActivityEvent> conflictEvents = new ArrayList<ActivityEvent>();
		
		List<ActivityEvent> previousEvents = new ArrayList<ActivityEvent>();
		for ( ActivityEvent e : events ) {
			boolean hasConflict = false;
			if ( previousEvents.size() > 0 ) {
				for ( ActivityEvent ev : previousEvents ) {
					if ( e.getId() != ev.getId() && e.getStartDateTime().before(ev.getEndDateTime())) {
						hasConflict = true;
						conflictEvents.add(e);
						break;
					}
				}
				
			}
			if ( !hasConflict ) results.add(e);
			previousEvents.add(e);
		}
		
		return conflictEvents;
	}
	
	private int getInt(String paramName) {
		int i = 0;
		String s = request.getParameter(paramName) != null ? request.getParameter(paramName).toUpperCase() : "";
		if ( !"".equals(s) ) {
			try {
				i = Integer.parseInt(s);
			} catch ( Exception e ) {}
		}
		return i;
	}
	
	@Command("select_calendar")
	public String selectCalendar() throws Exception {
		int day = 1;
		int month = getInt("month");
		int year = getInt("year");
		return new CalendarRequest(request, context).getCalendar(day, month, year);
	}

	@Command("get_calendar")
	public String getCalendar() {
		context.put("eventUtil", new EventUtil(userId));
		return new CalendarRequest(request, context).showCalendar();
	}
	
	@Command("get_next_calendar")
	public String getNextCalendar() throws Exception {
		context.put("eventUtil", new EventUtil(userId));
		return new CalendarRequest(request, context).getNext();
	}
	
	@Command("get_previous_calendar")
	public String getPreviousCalendar() throws Exception {
		context.put("eventUtil", new EventUtil(userId));
		return new CalendarRequest(request, context).getPrevious();
	}

	@Command("get_today_calendar")
	public String getTodayCalendar() throws Exception {
		context.put("eventUtil", new EventUtil(userId));
		return new CalendarRequest(request, context).getToday();
	}
	
	@Command("get_month_calendar")
	public String getMonthCalendar() throws Exception {
		context.put("eventUtil", new EventUtil(userId));
		return new CalendarRequest(request, context).getMonth();
	}
	
	public static void main(String[] args) throws Exception {
		
		Date today = new Date();
		String _today = new SimpleDateFormat("dd-MM-yyyy").format(today);
		Date date = parseDateTime(_today + " 06:00 AM");
		int interval = 15;
		List<Date> times = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for ( int i=0; i < 73; i++ ) {
			times.add(cal.getTime());
			cal.add(Calendar.MINUTE, interval);
		}
		
		DbPersistence db = new DbPersistence();
		Date eventDate = parseDate("12-07-2012");
		Hashtable h = new Hashtable();
		h.put("eventDate", eventDate);
		List<ActivityEvent> events = db.list("select e from ActivityEvent e where e.eventDate = :eventDate and e.userActivityEvent.userLogin = 'none' order by e.startDateTime", h);
		
		List<List<ActivityEvent>> conflicts = new ArrayList<List<ActivityEvent>>();
		List<ActivityEvent> eventList = new ArrayList<ActivityEvent>();
		eventList.addAll(events);
		boolean b = true;
		while ( b ) {
			List<ActivityEvent> results = new ArrayList<ActivityEvent>();
			List<ActivityEvent> conflictEvents = checkEvents2(times, eventList, results);
			if ( conflictEvents.size() == 0 ) break;
			conflicts.add(conflictEvents);
			eventList.clear();
			eventList.addAll(conflictEvents);
		}

		for ( List list : conflicts ) {
			System.out.println(list.size());
		}
		
		
		
	}
	
	

}
	
	
