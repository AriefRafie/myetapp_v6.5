package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppenyediaulasanjupem entity provides the base persistence
 * definition of the Tblphppenyediaulasanjupem entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppenyediaulasanjupem implements
		java.io.Serializable {

	// Fields

	private Long idPenyediaulasanjupem;
	private Tblphpulasanjupem tblphpulasanjupem;
	private Long idUlasanteknikal;
	private String namaPenyedia;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphppenyediaulasanjupem() {
	}

	/** minimal constructor */
	public AbstractTblphppenyediaulasanjupem(Long idPenyediaulasanjupem) {
		this.idPenyediaulasanjupem = idPenyediaulasanjupem;
	}

	/** full constructor */
	public AbstractTblphppenyediaulasanjupem(Long idPenyediaulasanjupem,
			Tblphpulasanjupem tblphpulasanjupem, Long idUlasanteknikal,
			String namaPenyedia, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idPenyediaulasanjupem = idPenyediaulasanjupem;
		this.tblphpulasanjupem = tblphpulasanjupem;
		this.idUlasanteknikal = idUlasanteknikal;
		this.namaPenyedia = namaPenyedia;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPenyediaulasanjupem() {
		return this.idPenyediaulasanjupem;
	}

	public void setIdPenyediaulasanjupem(Long idPenyediaulasanjupem) {
		this.idPenyediaulasanjupem = idPenyediaulasanjupem;
	}

	public Tblphpulasanjupem getTblphpulasanjupem() {
		return this.tblphpulasanjupem;
	}

	public void setTblphpulasanjupem(Tblphpulasanjupem tblphpulasanjupem) {
		this.tblphpulasanjupem = tblphpulasanjupem;
	}

	public Long getIdUlasanteknikal() {
		return this.idUlasanteknikal;
	}

	public void setIdUlasanteknikal(Long idUlasanteknikal) {
		this.idUlasanteknikal = idUlasanteknikal;
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