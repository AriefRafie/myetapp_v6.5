package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujjenistanah entity provides the base persistence definition of
 * the Tblrujjenistanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenistanah implements java.io.Serializable {

	// Fields

	private Long idJenistanah;
	private String kodJenistanah;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenistanah() {
	}

	/** minimal constructor */
	public AbstractTblrujjenistanah(Long idJenistanah) {
		this.idJenistanah = idJenistanah;
	}

	/** full constructor */
	public AbstractTblrujjenistanah(Long idJenistanah, String kodJenistanah,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idJenistanah = idJenistanah;
		this.kodJenistanah = kodJenistanah;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenistanah() {
		return this.idJenistanah;
	}

	public void setIdJenistanah(Long idJenistanah) {
		this.idJenistanah = idJenistanah;
	}

	public String getKodJenistanah() {
		return this.kodJenistanah;
	}

	public void setKodJenistanah(String kodJenistanah) {
		this.kodJenistanah = kodJenistanah;
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