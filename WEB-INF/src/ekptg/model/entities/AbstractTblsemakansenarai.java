package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblsemakansenarai entity provides the base persistence definition of
 * the Tblsemakansenarai entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblsemakansenarai implements java.io.Serializable {

	// Fields

	private Long idSemakansenarai;
	private Long idSemakan;
	private Long idUrusan;
	private String kodForm;
	private Long aturan;
	private String catatan;
	private Long idSuburusan;
	private Long idKategoripemohon;
	private String statusDokumen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblsemakansenarai() {
	}

	/** minimal constructor */
	public AbstractTblsemakansenarai(Long idSemakan, Long idUrusan,
			String kodForm) {
		this.idSemakan = idSemakan;
		this.idUrusan = idUrusan;
		this.kodForm = kodForm;
	}

	/** full constructor */
	public AbstractTblsemakansenarai(Long idSemakan, Long idUrusan,
			String kodForm, Long aturan, String catatan, Long idSuburusan,
			Long idKategoripemohon, String statusDokumen, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idSemakan = idSemakan;
		this.idUrusan = idUrusan;
		this.kodForm = kodForm;
		this.aturan = aturan;
		this.catatan = catatan;
		this.idSuburusan = idSuburusan;
		this.idKategoripemohon = idKategoripemohon;
		this.statusDokumen = statusDokumen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdSemakansenarai() {
		return this.idSemakansenarai;
	}

	public void setIdSemakansenarai(Long idSemakansenarai) {
		this.idSemakansenarai = idSemakansenarai;
	}

	public Long getIdSemakan() {
		return this.idSemakan;
	}

	public void setIdSemakan(Long idSemakan) {
		this.idSemakan = idSemakan;
	}

	public Long getIdUrusan() {
		return this.idUrusan;
	}

	public void setIdUrusan(Long idUrusan) {
		this.idUrusan = idUrusan;
	}

	public String getKodForm() {
		return this.kodForm;
	}

	public void setKodForm(String kodForm) {
		this.kodForm = kodForm;
	}

	public Long getAturan() {
		return this.aturan;
	}

	public void setAturan(Long aturan) {
		this.aturan = aturan;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Long getIdSuburusan() {
		return this.idSuburusan;
	}

	public void setIdSuburusan(Long idSuburusan) {
		this.idSuburusan = idSuburusan;
	}

	public Long getIdKategoripemohon() {
		return this.idKategoripemohon;
	}

	public void setIdKategoripemohon(Long idKategoripemohon) {
		this.idKategoripemohon = idKategoripemohon;
	}

	public String getStatusDokumen() {
		return this.statusDokumen;
	}

	public void setStatusDokumen(String statusDokumen) {
		this.statusDokumen = statusDokumen;
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

}