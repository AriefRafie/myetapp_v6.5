package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujjenisnopb entity provides the base persistence definition of
 * the Tblrujjenisnopb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenisnopb implements java.io.Serializable {

	// Fields

	private Long idJenisnopb;
	private String kodJenisNopb;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenisnopb() {
	}

	/** minimal constructor */
	public AbstractTblrujjenisnopb(Long idJenisnopb) {
		this.idJenisnopb = idJenisnopb;
	}

	/** full constructor */
	public AbstractTblrujjenisnopb(Long idJenisnopb, String kodJenisNopb,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idJenisnopb = idJenisnopb;
		this.kodJenisNopb = kodJenisNopb;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenisnopb() {
		return this.idJenisnopb;
	}

	public void setIdJenisnopb(Long idJenisnopb) {
		this.idJenisnopb = idJenisnopb;
	}

	public String getKodJenisNopb() {
		return this.kodJenisNopb;
	}

	public void setKodJenisNopb(String kodJenisNopb) {
		this.kodJenisNopb = kodJenisNopb;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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