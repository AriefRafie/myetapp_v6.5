package lebah.pm.module;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import lebah.template.DbPersistence;

public class EventUtil {
	
	private String userId;
	private DbPersistence db = new DbPersistence();
	
	public EventUtil() {}
	
	public EventUtil(String uid) {
		this.userId = uid;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getEventCount(Date eventDate) throws Exception {
		String txtDate = new SimpleDateFormat("dd-MM-yyyy").format(eventDate);
		eventDate = new SimpleDateFormat("dd-MM-yyyy").parse(txtDate);
		int cnt = 0;
		Hashtable h = new Hashtable();
		h.put("eventDate", eventDate);
		List list = db.list("select e from ActivityEvent e where e.eventDate = :eventDate and e.userActivityEvent.userLogin = '" + userId + "'", h);
		cnt = list.size();
		return cnt;
	}
	
	public int getEventCount(int date, int month, int year) throws Exception {
		Calendar c = Calendar.getInstance();
		month = month - 1;
		c.set(year, month, date);
		return getEventCount(c.getTime());
	}
	
	public static void main(String[] args) throws Exception {
		
		int c = new EventUtil("none").getEventCount(31, 7, 2012);
		
	}

}
