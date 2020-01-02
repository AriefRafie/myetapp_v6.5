package etapp.entity.htp;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLHTPBANGUNAN")
public class Bangunan {

	// Fields
	@Id @Column(name="ID_BANGUNAN")
	private Long id;
	
	@ManyToOne @JoinColumn(name="ID_HAKMILIKURUSAN")
	private HakmilikUrusan hakmilikUrusan;
	@Column(name="NOBANGUNAN")
	private String noBangunan;
	@Column(name="NOTINGKAT")
	private String noTingkat;
	@Column(name="NOPETAK")
	private String noPetak;
	
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;

	// Property accessors
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoBangunan() {
		return this.noBangunan;
	}

	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}

	public String getNoTingkat() {
		return this.noTingkat;
	}

	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}

	public String getNoPetak() {
		return this.noPetak;
	}

	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
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

	public void setTarikhMasuk(Timestamp tarikhMasuk) {
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

	public void setTarikhKemaskini(Timestamp tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public HakmilikUrusan getHakmilikUrusan() {
		return hakmilikUrusan;
	}

	public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
		this.hakmilikUrusan = hakmilikUrusan;
	}
	
//	public List<HakmilikUrusan> getListHakmilikUrusan() {
//		return listHakmilikUrusan;
//	}
//	public void setListHakmilikUrusan(List<HakmilikUrusan> listHakmilikUrusan) {
//		this.listHakmilikUrusan = listHakmilikUrusan;
//	}

}