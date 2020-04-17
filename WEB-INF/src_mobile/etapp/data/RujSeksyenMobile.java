package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJSEKSYEN")
public class RujSeksyenMobile {
	
	@Id @Column(name="ID_SEKSYEN")
	private Long id;
	@Column(name="KOD_SEKSYEN")
	private String kodSeksyen;
	@Column(name="NAMA_SEKSYEN")
	private String namaSeksyen;
	@Column(name="VERSI_SEKSYEN")
	private String versiSeksyen;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKodSeksyen() {
		return kodSeksyen;
	}
	public void setKodSeksyen(String kodSeksyen) {
		this.kodSeksyen = kodSeksyen;
	}
	public String getNamaSeksyen() {
		return namaSeksyen;
	}
	public void setNamaSeksyen(String namaSeksyen) {
		this.namaSeksyen = namaSeksyen;
	}
	public String getVersiSeksyen() {
		return versiSeksyen;
	}
	public void setVersiSeksyen(String versiSeksyen) {
		this.versiSeksyen = versiSeksyen;
	}
}