package lebah.pm.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lebah.template.UID;

@Entity
@Table(name = "TBLACTIVITYEVENT")
public class ActivityEvent {
	@Id 
	@Column(name="ID_ACTIVITYEVENT")
	private long id;
	@Column(name="EVENT_DATE")
	@Temporal(TemporalType.DATE)
	private Date eventDate;
	@Column(name="START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	@Column(name="END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDateTime;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="LOCATION_REMARK")
	private String locationRemark;
	@Column(name="REMARK")
	private String remark;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="ID_USERACTIVITYEVENT")
	private UserActivityEvent userActivityEvent;
	@Column(name="CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(name="DISPLAY_COLOR")
	private String displayColor;
	@Column(name="RECURRING")
	private int recurring;
	@Column(name="RECURRING_ID")
	private long recurringId;
	@Transient
	private Date endDateTime2;
	
	@Column(name="ID_PERBICARAAN")
	private long idPerbicaraan;
	
	@Column(name="ID_AKTIVITI_PEGAWAI")
	private long idAktivitiPegawai;
	

	public ActivityEvent() {
		setId(UID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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

	public UserActivityEvent getUserActivityEvent() {
		return userActivityEvent;
	}

	public void setUserActivityEvent(UserActivityEvent userActivityEvent) {
		this.userActivityEvent = userActivityEvent;
	}

	public String getLocationRemark() {
		return locationRemark;
	}

	public void setLocationRemark(String locationRemark) {
		this.locationRemark = locationRemark;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDisplayColor() {
		return displayColor;
	}

	public void setDisplayColor(String displayColor) {
		this.displayColor = displayColor;
	}

	public boolean getRecurring() {
		return recurring == 1;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring ? 1 : 0;
	}

	public long getRecurringId() {
		return recurringId;
	}

	public void setRecurringId(long recurringId) {
		this.recurringId = recurringId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getIdPerbicaraan() {
		return idPerbicaraan;
	}

	public void setIdPerbicaraan(long idPerbicaraan) {
		this.idPerbicaraan = idPerbicaraan;
	}

	public long getIdAktivitiPegawai() {
		return idAktivitiPegawai;
	}

	public void setIdAktivitiPegawai(long idAktivitiPegawai) {
		this.idAktivitiPegawai = idAktivitiPegawai;
	}


}
