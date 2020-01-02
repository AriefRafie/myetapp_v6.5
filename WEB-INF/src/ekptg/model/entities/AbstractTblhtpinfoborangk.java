package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpinfoborangk entity provides the base persistence definition of
 * the Tblhtpinfoborangk entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpinfoborangk implements java.io.Serializable {

	// Fields

	private Long idInfoborangk;
	private Long idBorangk;
	private Long idHtphakmilik;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpinfoborangk() {
	}

	/** minimal constructor */
	public AbstractTblhtpinfoborangk(Long idInfoborangk, Long idBorangk,
			Long idHtphakmilik) {
		this.idInfoborangk = idInfoborangk;
		this.idBorangk = idBorangk;
		this.idHtphakmilik = idHtphakmilik;
	}

	/** full constructor */
	public AbstractTblhtpinfoborangk(Long idInfoborangk, Long idBorangk,
			Long idHtphakmilik, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idInfoborangk = idInfoborangk;
		this.idBorangk = idBorangk;
		this.idHtphakmilik = idHtphakmilik;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdInfoborangk() {
		return this.idInfoborangk;
	}

	public void setIdInfoborangk(Long idInfoborangk) {
		this.idInfoborangk = idInfoborangk;
	}

	public Long getIdBorangk() {
		return this.idBorangk;
	}

	public void setIdBorangk(Long idBorangk) {
		this.idBorangk = idBorangk;
	}

	public Long getIdHtphakmilik() {
		return this.idHtphakmilik;
	}

	public void setIdHtphakmilik(Long idHtphakmilik) {
		this.idHtphakmilik = idHtphakmilik;
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