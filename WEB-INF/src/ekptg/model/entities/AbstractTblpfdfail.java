package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpfdfail entity provides the base persistence definition of the
 * Tblpfdfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfdfail implements java.io.Serializable {

	// Fields

	private Long idFail;
	private String kodJabatan;
	private Long idTarafkeselamatan;
	private Long idSeksyen;
	private Long idUrusan;
	private Long idSuburusan;
	private Date tarikhDaftarFail;
	private String tajukFail;
	private String noFail;
	private String noFailRoot;
	private Long  idLokasifail;
	private Long idNegeri;
	private Long idKementerian;
	private Long idFaharasat;
	private Long flagFail;
	private Long idStatus;
	private String catatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpfdfail() {
	}

	/** full constructor */
	public AbstractTblpfdfail(String kodJabatan, Long idTarafkeselamatan,
			Long idSeksyen, Long idUrusan, Long idSuburusan,
			Date tarikhDaftarFail, String tajukFail, String noFail,
			String noFailRoot, Long idLokasifail, Long idNegeri,
			Long idKementerian, Long idFaharasat, Long flagFail, Long idStatus,
			String catatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.kodJabatan = kodJabatan;
		this.idTarafkeselamatan = idTarafkeselamatan;
		this.idSeksyen = idSeksyen;
		this.idUrusan = idUrusan;
		this.idSuburusan = idSuburusan;
		this.tarikhDaftarFail = tarikhDaftarFail;
		this.tajukFail = tajukFail;
		this.noFail = noFail;
		this.noFailRoot = noFailRoot;
		this.idLokasifail = idLokasifail;
		this.idNegeri = idNegeri;
		this.idKementerian = idKementerian;
		this.idFaharasat = idFaharasat;
		this.flagFail = flagFail;
		this.idStatus = idStatus;
		this.catatan = catatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public String getKodJabatan() {
		return this.kodJabatan;
	}

	public void setKodJabatan(String kodJabatan) {
		this.kodJabatan = kodJabatan;
	}

	public Long getIdTarafkeselamatan() {
		return this.idTarafkeselamatan;
	}

	public void setIdTarafkeselamatan(Long idTarafkeselamatan) {
		this.idTarafkeselamatan = idTarafkeselamatan;
	}

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	public Long getIdUrusan() {
		return this.idUrusan;
	}

	public void setIdUrusan(Long idUrusan) {
		this.idUrusan = idUrusan;
	}

	public Long getIdSuburusan() {
		return this.idSuburusan;
	}

	public void setIdSuburusan(Long idSuburusan) {
		this.idSuburusan = idSuburusan;
	}

	public Date getTarikhDaftarFail() {
		return this.tarikhDaftarFail;
	}

	public void setTarikhDaftarFail(Date tarikhDaftarFail) {
		this.tarikhDaftarFail = tarikhDaftarFail;
	}

	public String getTajukFail() {
		return this.tajukFail;
	}

	public void setTajukFail(String tajukFail) {
		this.tajukFail = tajukFail;
	}

	public String getNoFail() {
		return this.noFail;
	}

	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}

	public String getNoFailRoot() {
		return this.noFailRoot;
	}

	public void setNoFailRoot(String noFailRoot) {
		this.noFailRoot = noFailRoot;
	}

	public Long getIdLokasifail() {
		return this.idLokasifail;
	}

	public void setIdLokasifail(Long idLokasifail) {
		this.idLokasifail = idLokasifail;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdKementerian() {
		return this.idKementerian;
	}

	public void setIdKementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
	}

	public Long getIdFaharasat() {
		return this.idFaharasat;
	}

	public void setIdFaharasat(Long idFaharasat) {
		this.idFaharasat = idFaharasat;
	}

	public Long getFlagFail() {
		return this.flagFail;
	}

	public void setFlagFail(Long flagFail) {
		this.flagFail = flagFail;
	}

	public Long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
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