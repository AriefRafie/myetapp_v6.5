package lebah.pm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lebah.template.UID;

@Entity
@Table(name = "TBLUSERACTIVITYEVENT")
public class UserActivityEvent {

	
	@Id 
	@Column(name="ID_USERACTIVITYEVENT")
	private long id;
	@Column(name="USER_LOGIN")
	private String userLogin;
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="userActivityEvent")
	private List<ActivityEvent> events;
	
	public UserActivityEvent() {
		setId(UID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public List<ActivityEvent> getEvents() {
		return events;
	}
	public void setEvents(List<ActivityEvent> events) {
		this.events = events;
	}
	
	
	
}
