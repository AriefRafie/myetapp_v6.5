package etapp.entity.htp.rekod;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLHTPRUJSTATUSSAH")
public class StatusSah{

	// Fields
	@Id @Column(name="ID_STATUSSAH")
	private Long id;
	
	@Column(name="STATUS_SAH")
	private String kod;
	@Column(name="KETERANGAN")
	private String keterangan;
	@Column(name="AKTIF")
	private String aktif;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Column(name="TARIKH_MASUK") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Column(name="TARIKH_KEMASKINI") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date tarikhKemaskini;

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

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public String getAktif() {
		return aktif;
	}

	public void setAktif(String aktif) {
		this.aktif = aktif;
	}

}