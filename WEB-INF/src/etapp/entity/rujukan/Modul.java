package etapp.entity.rujukan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MODULE")
public class Modul{
	// Fields
	@Id @Column(name="MODULE_ID")
	private String id;	
	@Column(name="MODULE_TITLE")
	private String tajuk;
	@Column(name="MODULE_CLASS")
	private String javaClass;
	@Column(name="MODULE_GROUP")
	private String group;
	@Column(name="MODULE_DESCRIPTION")
	private String keterangan;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTajuk() {
		return tajuk;
	}
	public void setTajuk(String tajuk) {
		this.tajuk = tajuk;
	}
	public String getJavaClass() {
		return javaClass;
	}
	public void setJavaClass(String javaClass) {
		this.javaClass = javaClass;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	

}