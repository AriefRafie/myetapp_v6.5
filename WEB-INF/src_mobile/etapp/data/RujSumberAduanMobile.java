package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJSUMBERADUAN")
public class RujSumberAduanMobile {

	@Id @Column(name="ID_SUMBERADUAN")
	private long id;
	@Column(name="KOD_SUMBER")
	private String kodSumber;
	@Column(name="NAMA_SUMBER")
	private String namaSumber;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKodSumber() {
		return kodSumber;
	}
	public void setKodSumber(String kodSumber) {
		this.kodSumber = kodSumber;
	}
	public String getNamaSumber() {
		return namaSumber;
	}
	public void setNamaSumber(String namaSumber) {
		this.namaSumber = namaSumber;
	}
}