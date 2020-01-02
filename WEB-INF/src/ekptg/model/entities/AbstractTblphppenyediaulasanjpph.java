package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppenyediaulasanjpph entity provides the base persistence
 * definition of the Tblphppenyediaulasanjpph entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppenyediaulasanjpph implements
		java.io.Serializable {

	// Fields

	private Long idPenyediaulasanjpph;
	private Tblphpulasanjpph tblphpulasanjpph;
	private Tblphpulasanteknikal tblphpulasanteknikal;
	private String namaPenyedia;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphppenyediaulasanjpph() {
	}

	/** minimal constructor */
	public AbstractTblphppenyediaulasanjpph(Long idPenyediaulasanjpph) {
		this.idPenyediaulasanjpph = idPenyediaulasanjpph;
	}

	/** full constructor */
	public AbstractTblphppenyediaulasanjpph(Long idPenyediaulasanjpph,
			Tblphpulasanjpph tblphpulasanjpph,
			Tblphpulasanteknikal tblphpulasanteknikal, String namaPenyedia,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPenyediaulasanjpph = idPenyediaulasanjpph;
		this.tblphpulasanjpph = tblphpulasanjpph;
		this.tblphpulasanteknikal = tblphpulasanteknikal;
		this.namaPenyedia = namaPenyedia;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPenyediaulasanjpph() {
		return this.idPenyediaulasanjpph;
	}

	public void setIdPenyediaulasanjpph(Long idPenyediaulasanjpph) {
		this.idPenyediaulasanjpph = idPenyediaulasanjpph;
	}

	public Tblphpulasanjpph getTblphpulasanjpph() {
		return this.tblphpulasanjpph;
	}

	public void setTblphpulasanjpph(Tblphpulasanjpph tblphpulasanjpph) {
		this.tblphpulasanjpph = tblphpulasanjpph;
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