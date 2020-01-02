package etapp.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MODULE")
public class Module {
	
	@Id @Column(name="MODULE_ID")
	private String id;
	@Column(name="MODULE_TITLE")
	private String moTitle;
	@Column(name="MODULE_CLASS")
	private String moClass;
	@Column(name="MODULE_GROUP")
	private String moGroup;
	@Column(name="MODULE_DESCRIPTION")
	private String moDesc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMoTitle() {
		return moTitle;
	}
	public void setMoTitle(String moTitle) {
		this.moTitle = moTitle;
	}
	public String getMoClass() {
		return moClass;
	}
	public void setMoClass(String moClass) {
		this.moClass = moClass;
	}
	public String getMoGroup() {
		return moGroup;
	}
	public void setMoGroup(String moGroup) {
		this.moGroup = moGroup;
	}
	public String getMoDesc() {
		return moDesc;
	}
	public void setMoDesc(String moDesc) {
		this.moDesc = moDesc;
	}
}