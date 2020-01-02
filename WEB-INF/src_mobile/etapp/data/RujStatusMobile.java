package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJSTATUS")
public class RujStatusMobile {
	
	@Id @Column(name="ID_STATUS")
	private long id;
	@Column(name="KOD_STATUS")
	private String kodStatus;
	@Column(name="KETERANGAN")
	private String keterangan;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKodStatus() {
		return kodStatus;
	}
	public void setKodStatus(String kodStatus) {
		this.kodStatus = kodStatus;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}	
}