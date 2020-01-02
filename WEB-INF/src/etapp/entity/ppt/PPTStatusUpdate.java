package etapp.entity.ppt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lebah.template.UID;
import etapp.entity.User;

@Entity
@Table(name="PPTPERMOHONANSTATUSUPDATE")
public class PPTStatusUpdate {
	
	@Id
	@Column(name="ID_STATUS_UPDATE")
	private long id;
	@ManyToOne
	@JoinColumn(name="ID_STATUS")
	private PPTStatus status;
	@ManyToOne
	@JoinColumn(name="ID_PERMOHONAN")
	private PPTPermohonan permohonan;
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATE")
	private Date tarikhKemaskini;
	@Column(name="REMARK")
	private String remark;
	
	
	public PPTStatusUpdate() {
		setId(UID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PPTStatus getStatus() {
		return status;
	}
	public void setStatus(PPTStatus status) {
		this.status = status;
	}
	public PPTPermohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(PPTPermohonan permohonan) {
		this.permohonan = permohonan;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	

}
