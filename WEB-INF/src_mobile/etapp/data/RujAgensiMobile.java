package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJAGENSI")
public class RujAgensiMobile {

	@Id @Column(name="ID_AGENSI")
	private long id;
	@Column(name="KOD_AGENSI")
	private String kodAgensi;
	@Column(name="NAMA_AGENSI")
	private String namaAgensi;
	
	@ManyToOne
	@JoinColumn(name="ID_NEGERI")
	private RujNegeriMobile negeri;
	@ManyToOne
	@JoinColumn(name="ID_KEMENTERIAN")
	private RujKementerianMobile kementerian;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKodAgensi() {
		return kodAgensi;
	}
	public void setKodAgensi(String kodAgensi) {
		this.kodAgensi = kodAgensi;
	}
	public String getNamaAgensi() {
		return namaAgensi;
	}
	public void setNamaAgensi(String namaAgensi) {
		this.namaAgensi = namaAgensi;
	}
	public RujNegeriMobile getNegeri() {
		return negeri;
	}
	public void setNegeri(RujNegeriMobile negeri) {
		this.negeri = negeri;
	}
	public RujKementerianMobile getKementerian() {
		return kementerian;
	}
	public void setKementerian(RujKementerianMobile kementerian) {
		this.kementerian = kementerian;
	}
}
