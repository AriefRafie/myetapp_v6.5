package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujurusanstatus entity provides the base persistence definition of
 * the Tblrujurusanstatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujurusanstatus implements
		java.io.Serializable {

	// Fields

	private Long idUrusanstatus;
	private Long idPermohonan;
	private Long idStatusFail;
	private Long idUrusan;
	private Date tarikhMula;
	private Date tarikhAkhir;
	private String aktif;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujurusanstatus() {
	}

	/** minimal constructor */
	public AbstractTblrujurusanstatus(Long idUrusanstatus, Long idPermohonan,
			Long idStatusFail, Long idUrusan) {
		this.idUrusanstatus = idUrusanstatus;
		this.idPermohonan = idPermohonan;
		this.idStatusFail = idStatusFail;
		this.idUrusan = idUrusan;
	}

	/** full constructor */
	public AbstractTblrujurusanstatus(Long idUrusanstatus, Long idPermohonan,
			Long idStatusFail, Long idUrusan, Date tarikhMula,
			Date tarikhAkhir, String aktif, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idUrusanstatus = idUrusanstatus;
		this.idPermohonan = idPermohonan;
		this.idStatusFail = idStatusFail;
		this.idUrusan = idUrusan;
		this.tarikhMula = tarikhMula;
		this.tarikhAkhir = tarikhAkhir;
		this.aktif = aktif;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdUrusanstatus() {
		return this.idUrusanstatus;
	}

	public void setIdUrusanstatus(Long idUrusanstatus) {
		this.idUrusanstatus = idUrusanstatus;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdStatusFail() {
		return this.idStatusFail;
	}

	public void setIdStatusFail(Long idStatusFail) {
		this.idStatusFail = idStatusFail;
	}

	public Long getIdUrusan() {
		return this.idUrusan;
	}

	public void setIdUrusan(Long idUrusan) {
		this.idUrusan = idUrusan;
	}

	public Date getTarikhMula() {
		return this.tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhAkhir() {
		return this.tarikhAkhir;
	}

	public void setTarikhAkhir(Date tarikhAkhir) {
		this.tarikhAkhir = tarikhAkhir;
	}

	public String getAktif() {
		return this.aktif;
	}

	public void setAktif(String aktif) {
		this.aktif = aktif;
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