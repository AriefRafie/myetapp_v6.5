package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujjawatan entity provides the base persistence definition of the
 * Tblrujjawatan entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjawatan implements java.io.Serializable {

	// Fields

	private Long idJawatan;
	private String kodJawatan;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujjawatan() {
	}

	/** full constructor */
	public AbstractTblrujjawatan(String kodJawatan, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.kodJawatan = kodJawatan;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJawatan() {
		return this.idJawatan;
	}

	public void setIdJawatan(Long idJawatan) {
		this.idJawatan = idJawatan;
	}

	public String getKodJawatan() {
		return this.kodJawatan;
	}

	public void setKodJawatan(String kodJawatan) {
		this.kodJawatan = kodJawatan;
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