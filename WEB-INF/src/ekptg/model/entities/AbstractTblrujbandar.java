package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujbandar entity provides the base persistence definition of the
 * Tblrujbandar entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujbandar implements java.io.Serializable {

	// Fields

	private Long idBandar;
	private String kodBandar;
	private String keterangan;
	private Long idNegeri;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujbandar() {
	}

	/** minimal constructor */
	public AbstractTblrujbandar(Long idBandar) {
		this.idBandar = idBandar;
	}

	/** full constructor */
	public AbstractTblrujbandar(Long idBandar, String kodBandar,
			String keterangan, Long idNegeri, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idBandar = idBandar;
		this.kodBandar = kodBandar;
		this.keterangan = keterangan;
		this.idNegeri = idNegeri;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBandar() {
		return this.idBandar;
	}

	public void setIdBandar(Long idBandar) {
		this.idBandar = idBandar;
	}

	public String getKodBandar() {
		return this.kodBandar;
	}

	public void setKodBandar(String kodBandar) {
		this.kodBandar = kodBandar;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
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