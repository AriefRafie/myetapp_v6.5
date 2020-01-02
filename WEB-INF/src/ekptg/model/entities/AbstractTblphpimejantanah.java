package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpimejantanah entity provides the base persistence definition of
 * the Tblphpimejantanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpimejantanah implements java.io.Serializable {

	// Fields

	private Long idImejantanah;
	private Tblphplawatantapak tblphplawatantapak;
	private Long idDokumen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpimejantanah() {
	}

	/** minimal constructor */
	public AbstractTblphpimejantanah(Long idImejantanah) {
		this.idImejantanah = idImejantanah;
	}

	/** full constructor */
	public AbstractTblphpimejantanah(Long idImejantanah,
			Tblphplawatantapak tblphplawatantapak, Long idDokumen,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idImejantanah = idImejantanah;
		this.tblphplawatantapak = tblphplawatantapak;
		this.idDokumen = idDokumen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdImejantanah() {
		return this.idImejantanah;
	}

	public void setIdImejantanah(Long idImejantanah) {
		this.idImejantanah = idImejantanah;
	}

	public Tblphplawatantapak getTblphplawatantapak() {
		return this.tblphplawatantapak;
	}

	public void setTblphplawatantapak(Tblphplawatantapak tblphplawatantapak) {
		this.tblphplawatantapak = tblphplawatantapak;
	}

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
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