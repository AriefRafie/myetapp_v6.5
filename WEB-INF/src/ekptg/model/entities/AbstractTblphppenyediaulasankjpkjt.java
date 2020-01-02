package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppenyediaulasankjpkjt entity provides the base persistence
 * definition of the Tblphppenyediaulasankjpkjt entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppenyediaulasankjpkjt implements
		java.io.Serializable {

	// Fields

	private Long idPenyediaulasankjpkjt;
	private Tblphpulasanteknikal tblphpulasanteknikal;
	private String namaPenyedia;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphppenyediaulasankjpkjt() {
	}

	/** minimal constructor */
	public AbstractTblphppenyediaulasankjpkjt(Long idPenyediaulasankjpkjt) {
		this.idPenyediaulasankjpkjt = idPenyediaulasankjpkjt;
	}

	/** full constructor */
	public AbstractTblphppenyediaulasankjpkjt(Long idPenyediaulasankjpkjt,
			Tblphpulasanteknikal tblphpulasanteknikal, String namaPenyedia,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPenyediaulasankjpkjt = idPenyediaulasankjpkjt;
		this.tblphpulasanteknikal = tblphpulasanteknikal;
		this.namaPenyedia = namaPenyedia;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPenyediaulasankjpkjt() {
		return this.idPenyediaulasankjpkjt;
	}

	public void setIdPenyediaulasankjpkjt(Long idPenyediaulasankjpkjt) {
		this.idPenyediaulasankjpkjt = idPenyediaulasankjpkjt;
	}

	public Tblphpulasanteknikal getTblphpulasanteknikal() {
		return this.tblphpulasanteknikal;
	}

	public void setTblphpulasanteknikal(
			Tblphpulasanteknikal tblphpulasanteknikal) {
		this.tblphpulasanteknikal = tblphpulasanteknikal;
	}

	public String getNamaPenyedia() {
		return this.namaPenyedia;
	}

	public void setNamaPenyedia(String namaPenyedia) {
		this.namaPenyedia = namaPenyedia;
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