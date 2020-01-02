package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpjaminan entity provides the base persistence definition of the
 * Tblhtpjaminan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpjaminan implements java.io.Serializable {

	// Fields

	private Long idJaminan;
	private Long idHtphakmilik;
	private String ulasan;
	private Date tarikhSerah;
	private Date tarikhSerahSebenar;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpjaminan() {
	}

	/** minimal constructor */
	public AbstractTblhtpjaminan(Long idJaminan, Long idHtphakmilik,
			String ulasan) {
		this.idJaminan = idJaminan;
		this.idHtphakmilik = idHtphakmilik;
		this.ulasan = ulasan;
	}

	/** full constructor */
	public AbstractTblhtpjaminan(Long idJaminan, Long idHtphakmilik,
			String ulasan, Date tarikhSerah, Date tarikhSerahSebenar,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idJaminan = idJaminan;
		this.idHtphakmilik = idHtphakmilik;
		this.ulasan = ulasan;
		this.tarikhSerah = tarikhSerah;
		this.tarikhSerahSebenar = tarikhSerahSebenar;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJaminan() {
		return this.idJaminan;
	}

	public void setIdJaminan(Long idJaminan) {
		this.idJaminan = idJaminan;
	}

	public Long getIdHtphakmilik() {
		return this.idHtphakmilik;
	}

	public void setIdHtphakmilik(Long idHtphakmilik) {
		this.idHtphakmilik = idHtphakmilik;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public Date getTarikhSerah() {
		return this.tarikhSerah;
	}

	public void setTarikhSerah(Date tarikhSerah) {
		this.tarikhSerah = tarikhSerah;
	}

	public Date getTarikhSerahSebenar() {
		return this.tarikhSerahSebenar;
	}

	public void setTarikhSerahSebenar(Date tarikhSerahSebenar) {
		this.tarikhSerahSebenar = tarikhSerahSebenar;
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