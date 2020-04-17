package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujstatus entity provides the base persistence definition of the
 * Tblrujstatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujstatus implements java.io.Serializable {

	// Fields

	private Long idStatus;
	private String kodStatus;
	private String keterangan;
	private Long idMasuk;
	private Long idKemaskini;
	private Date tarikhMasuk;
	private Date tarikhKemaskini;
	private Long idSeksyen;

	// Constructors

	/** default constructor */
	public AbstractTblrujstatus() {
	}

	/** full constructor */
	public AbstractTblrujstatus(String kodStatus, String keterangan,
			Long idMasuk, Long idKemaskini, Date tarikhMasuk,
			Date tarikhKemaskini, Long idSeksyen) {
		this.kodStatus = kodStatus;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhMasuk = tarikhMasuk;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idSeksyen = idSeksyen;
	}

	// Property accessors

	public Long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getKodStatus() {
		return this.kodStatus;
	}

	public void setKodStatus(String kodStatus) {
		this.kodStatus = kodStatus;
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

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

}