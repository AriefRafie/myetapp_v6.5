package etapp.entity.rujukan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJJENISPEJABAT")
public class JenisPejabat{

	// Fields
	@Id @Column(name="ID_JENISPEJABAT")
	private Long id;
	
	@Column(name="KOD_JENIS_PEJABAT")
	private String kod;
	@Column(name="KETERANGAN")
	private String keterangan;
//	@Column(name="ID_MASUK")
//	private Long idMasuk;
//	@Column(name="TARIKH_MASUK") 
//	private Date tarikhMasuk;
//	@Column(name="ID_KEMASKINI")
//	private Long idKemaskini;
//	@Column(name="TARIKH_KEMASKINI") 
//	private Date tarikhKemaskini;

	// Property accessors
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(String kodLot) {
		this.kod = kodLot;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

//	public Long getIdMasuk() {
//		return this.idMasuk;
//	}
//
//	public void setIdMasuk(Long idMasuk) {
//		this.idMasuk = idMasuk;
//	}
//
//	public Date getTarikhMasuk() {
//		return this.tarikhMasuk;
//	}
//
//	public void setTarikhMasuk(Date tarikhMasuk) {
//		this.tarikhMasuk = tarikhMasuk;
//	}
//
//	public Long getIdKemaskini() {
//		return this.idKemaskini;
//	}
//
//	public void setIdKemaskini(Long idKemaskini) {
//		this.idKemaskini = idKemaskini;
//	}
//
//	public Date getTarikhKemaskini() {
//		return this.tarikhKemaskini;
//	}
//
//	public void setTarikhKemaskini(Date tarikhKemaskini) {
//		this.tarikhKemaskini = tarikhKemaskini;
//	}

}