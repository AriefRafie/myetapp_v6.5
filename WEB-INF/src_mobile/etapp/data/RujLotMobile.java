package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJLOT")
public class RujLotMobile {
	
	@Id	@Column(name="ID_LOT")
	private long id;
	@Column(name="KOD_LOT")
	private String kodLot;
	@Column(name="KETERANGAN")
	private String keterangan;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKodLot() {
		return kodLot;
	}
	public void setKodLot(String kodLot) {
		this.kodLot = kodLot;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
}
