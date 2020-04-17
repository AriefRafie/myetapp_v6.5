package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphplawatantapakpelepasan entity provides the base persistence
 * definition of the Tblphplawatantapakpelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphplawatantapakpelepasan implements
		java.io.Serializable {

	// Fields

	private Long idLawatantapakpelepasan;
	private Tblphppermohonanpelepasan tblphppermohonanpelepasan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idLawatantapak;

	// Constructors

	/** default constructor */
	public AbstractTblphplawatantapakpelepasan() {
	}

	/** minimal constructor */
	public AbstractTblphplawatantapakpelepasan(Long idLawatantapakpelepasan) {
		this.idLawatantapakpelepasan = idLawatantapakpelepasan;
	}

	/** full constructor */
	public AbstractTblphplawatantapakpelepasan(Long idLawatantapakpelepasan,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idLawatantapak) {
		this.idLawatantapakpelepasan = idLawatantapakpelepasan;
		this.tblphppermohonanpelepasan = tblphppermohonanpelepasan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idLawatantapak = idLawatantapak;
	}

	// Property accessors

	public Long getIdLawatantapakpelepasan() {
		return this.idLawatantapakpelepasan;
	}

	public void setIdLawatantapakpelepasan(Long idLawatantapakpelepasan) {
		this.idLawatantapakpelepasan = idLawatantapakpelepasan;
	}

	public Tblphppermohonanpelepasan getTblphppermohonanpelepasan() {
		return this.tblphppermohonanpelepasan;
	}

	public void setTblphppermohonanpelepasan(
			Tblphppermohonanpelepasan tblphppermohonanpelepasan) {
		this.tblphppermohonanpelepasan = tblphppermohonanpelepasan;
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