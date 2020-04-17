package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphplawatantapakpenyewaan entity provides the base persistence
 * definition of the Tblphplawatantapakpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphplawatantapakpenyewaan implements
		java.io.Serializable {

	// Fields

	private Long idLawatantapakpenyewaan;
	private Tblphppermohonanpenyewaan tblphppermohonanpenyewaan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idLawatantapak;

	// Constructors

	/** default constructor */
	public AbstractTblphplawatantapakpenyewaan() {
	}

	/** minimal constructor */
	public AbstractTblphplawatantapakpenyewaan(Long idLawatantapakpenyewaan) {
		this.idLawatantapakpenyewaan = idLawatantapakpenyewaan;
	}

	/** full constructor */
	public AbstractTblphplawatantapakpenyewaan(Long idLawatantapakpenyewaan,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idLawatantapak) {
		this.idLawatantapakpenyewaan = idLawatantapakpenyewaan;
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idLawatantapak = idLawatantapak;
	}

	// Property accessors

	public Long getIdLawatantapakpenyewaan() {
		return this.idLawatantapakpenyewaan;
	}

	public void setIdLawatantapakpenyewaan(Long idLawatantapakpenyewaan) {
		this.idLawatantapakpenyewaan = idLawatantapakpenyewaan;
	}

	public Tblphppermohonanpenyewaan getTblphppermohonanpenyewaan() {
		return this.tblphppermohonanpenyewaan;
	}

	public void setTblphppermohonanpenyewaan(
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan) {
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
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

	public Long getIdLawatantapak() {
		return this.idLawatantapak;
	}

	public void setIdLawatantapak(Long idLawatantapak) {
		this.idLawatantapak = idLawatantapak;
	}

}