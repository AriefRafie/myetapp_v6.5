package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPKSIMATI")
public class PPKSimatiMobile {
	
	@Id @Column(name="ID_SIMATI")
	private long id;
	@Column(name="NAMA_SIMATI")
	private String namaSimati;
	@Column(name="NO_KP_BARU")
	private String noKPBaru;
	@Column(name="NO_KP_LAMA")
	private String noKPLama;
	@Column(name="NO_KP_LAIN")
	private String noKPLain;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNamaSimati() {
		return namaSimati;
	}
	public void setNamaSimati(String namaSimati) {
		this.namaSimati = namaSimati;
	}
	public String getNoKPBaru() {
		return noKPBaru;
	}
	public void setNoKPBaru(String noKPBaru) {
		this.noKPBaru = noKPBaru;
	}
	public String getNoKPLama() {
		return noKPLama;
	}
	public void setNoKPLama(String noKPLama) {
		this.noKPLama = noKPLama;
	}
	public String getNoKPLain() {
		return noKPLain;
	}
	public void setNoKPLain(String noKPLain) {
		this.noKPLain = noKPLain;
	}
}