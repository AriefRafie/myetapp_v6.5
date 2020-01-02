package lebah.pm.module;

import java.util.Calendar;
import java.util.Date;

public class Event {
	
	private Date startDateTime;
	private Date endDateTime;
	private Date endDateTime2;
	
	private String description;
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEndDateTime2() {
		Calendar c = Calendar.getInstance();
		c.setTime(endDateTime);
		c.add(Calendar.MINUTE, -15);
		endDateTime2 = c.getTime();
		return endDateTime2;
	}

}
