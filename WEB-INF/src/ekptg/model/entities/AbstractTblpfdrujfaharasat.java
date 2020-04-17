package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpfdrujfaharasat entity provides the base persistence definition of
 * the Tblpfdrujfaharasat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfdrujfaharasat implements
		java.io.Serializable {

	// Fields

	private Long idFaharasat;
	private String faharasat;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpfdrujfaharasat() {
	}

	/** full constructor */
	public AbstractTblpfdrujfaharasat(String faharasat, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.faharasat = faharasat;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdFaharasat() {
		return this.idFaharasat;
	}

	public void setIdFaharasat(Long idFaharasat) {
		this.idFaharasat = idFaharasat;
	}

	public String getFaharasat() {
		return this.faharasat;
	}

	public void setFaharasat(String faharasat) {
		this.faharasat = faharasat;
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