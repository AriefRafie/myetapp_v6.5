package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujdb entity provides the base persistence definition of the
 * Tblrujdb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujdb implements java.io.Serializable {

	// Fields

	private Long idDb;
	private String kodDb;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujdb() {
	}

	/** minimal constructor */
	public AbstractTblrujdb(Long idDb) {
		this.idDb = idDb;
	}

	/** full constructor */
	public AbstractTblrujdb(Long idDb, String kodDb, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idDb = idDb;
		this.kodDb = kodDb;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

	public String getKodDb() {
		return this.kodDb;
	}

	public void setKodDb(String kodDb) {
		this.kodDb = kodDb;
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