package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJJAWATAN")
public class RujJawatanMobile {

	@Id
	@Column(name="ID_JAWATAN")
	private long id;
	@Column(name="KOD_JAWATAN")
	private String kodJawatan;
	@Column(name="KETERANGAN")
	private String keterangan;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKodJawatan() {
		return kodJawatan;
	}
	public void setKodJawatan(String kodJawatan) {
		this.kodJawatan = kodJawatan;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
}
