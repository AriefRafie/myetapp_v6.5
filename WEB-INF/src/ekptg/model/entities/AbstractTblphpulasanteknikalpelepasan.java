package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpulasanteknikalpelepasan entity provides the base persistence
 * definition of the Tblphpulasanteknikalpelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpulasanteknikalpelepasan implements
		java.io.Serializable {

	// Fields

	private Long idUlasanteknikalpelepasan;
	private Tblphppermohonanpelepasan tblphppermohonanpelepasan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idUlasanteknikal;

	// Constructors

	/** default constructor */
	public AbstractTblphpulasanteknikalpelepasan() {
	}

	/** minimal constructor */
	public AbstractTblphpulasanteknikalpelepasan(Long idUlasanteknikalpelepasan) {
		this.idUlasanteknikalpelepasan = idUlasanteknikalpelepasan;
	}

	/** full constructor */
	public AbstractTblphpulasanteknikalpelepasan(
			Long idUlasanteknikalpelepasan,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idUlasanteknikal) {
		this.idUlasanteknikalpelepasan = idUlasanteknikalpelepasan;
		this.tblphppermohonanpelepasan = tblphppermohonanpelepasan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idUlasanteknikal = idUlasanteknikal;
	}

	// Property accessors

	public Long getIdUlasanteknikalpelepasan() {
		return this.idUlasanteknikalpelepasan;
	}

	public void setIdUlasanteknikalpelepasan(Long idUlasanteknikalpelepasan) {
		this.idUlasanteknikalpelepasan = idUlasanteknikalpelepasan;
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

	public Long getIdUlasanteknikal() {
		return this.idUlasanteknikal;
	}

	public void setIdUlasanteknikal(Long idUlasanteknikal) {
		this.idUlasanteknikal = idUlasanteknikal;
	}

}