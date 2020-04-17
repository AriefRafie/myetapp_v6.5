package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJLUAS")
public class RujLuasMobile {

	@Id	@Column(name="ID_LUAS")
	private long id;
	@Column(name="KOD_LUAS")
	private String kodLuas;
	@Column(name="KETERANGAN")
	private String keterangan;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKodLuas() {
		return kodLuas;
	}
	public void setKodLuas(String kodLuas) {
		this.kodLuas = kodLuas;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
}
