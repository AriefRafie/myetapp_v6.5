package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujseksyen entity provides the base persistence definition of the
 * Tblrujseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujseksyen implements java.io.Serializable {

	// Fields

	private Long idSeksyen;
	private String kodSeksyen;
	private String namaSeksyen;
	private String versiSeksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujseksyen() {
	}

	/** minimal constructor */
	public AbstractTblrujseksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	/** full constructor */
	public AbstractTblrujseksyen(Long idSeksyen, String kodSeksyen,
			String namaSeksyen, String versiSeksyen, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idSeksyen = idSeksyen;
		this.kodSeksyen = kodSeksyen;
		this.namaSeksyen = namaSeksyen;
		this.versiSeksyen = versiSeksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	public String getKodSeksyen() {
		return this.kodSeksyen;
	}

	public void setKodSeksyen(String kodSeksyen) {
		this.kodSeksyen = kodSeksyen;
	}

	public String getNamaSeksyen() {
		return this.namaSeksyen;
	}

	public void setNamaSeksyen(String namaSeksyen) {
		this.namaSeksyen = namaSeksyen;
	}

	public String getVersiSeksyen() {
		return this.versiSeksyen;
	}

	public void setVersiSeksyen(String versiSeksyen) {
		this.versiSeksyen = versiSeksyen;
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