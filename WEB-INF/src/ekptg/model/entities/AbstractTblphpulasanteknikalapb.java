package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpulasanteknikalapb entity provides the base persistence
 * definition of the Tblphpulasanteknikalapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpulasanteknikalapb implements
		java.io.Serializable {

	// Fields

	private Long idUlasanteknikalapb;
	private Tblphppmohonnjdualpertama tblphppmohonnjdualpertama;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idUlasanteknikal;

	// Constructors

	/** default constructor */
	public AbstractTblphpulasanteknikalapb() {
	}

	/** minimal constructor */
	public AbstractTblphpulasanteknikalapb(Long idUlasanteknikalapb) {
		this.idUlasanteknikalapb = idUlasanteknikalapb;
	}

	/** full constructor */
	public AbstractTblphpulasanteknikalapb(Long idUlasanteknikalapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idUlasanteknikal) {
		this.idUlasanteknikalapb = idUlasanteknikalapb;
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idUlasanteknikal = idUlasanteknikal;
	}

	// Property accessors

	public Long getIdUlasanteknikalapb() {
		return this.idUlasanteknikalapb;
	}

	public void setIdUlasanteknikalapb(Long idUlasanteknikalapb) {
		this.idUlasanteknikalapb = idUlasanteknikalapb;
	}

	public Tblphppmohonnjdualpertama getTblphppmohonnjdualpertama() {
		return this.tblphppmohonnjdualpertama;
	}

	public void setTblphppmohonnjdualpertama(
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama) {
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
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