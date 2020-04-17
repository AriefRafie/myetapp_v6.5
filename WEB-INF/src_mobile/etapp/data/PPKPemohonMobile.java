package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPKPEMOHON")
public class PPKPemohonMobile {
	
	@Id @Column(name="ID_PEMOHON")
	private long id;
	@Column(name="NAMA_PEMOHON")
	private String namaPemohon;
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
	public String getNamaPemohon() {
		return namaPemohon;
	}
	public void setNamaPemohon(String namaPemohon) {
		this.namaPemohon = namaPemohon;
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