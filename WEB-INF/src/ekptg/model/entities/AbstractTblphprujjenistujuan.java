package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphprujjenistujuan entity provides the base persistence definition of the
 * Tblrujjawatan entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphprujjenistujuan implements java.io.Serializable {

	// Fields

	private Long idJenistujuan;
	private String kodJenistujuan;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphprujjenistujuan() {
	}

	/** full constructor */
	public AbstractTblphprujjenistujuan(String kodJenistujuan, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.kodJenistujuan = kodJenistujuan;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenistujuan() {
		return this.idJenistujuan;
	}

	public void setIdJenistujuan(Long idJenistujuan) {
		this.idJenistujuan = idJenistujuan;
	}

	public String getKodJenistujuan() {
		return this.kodJenistujuan;
	}

	public void setKodJenistujuan(String kodJenistujuan) {
		this.kodJenistujuan = kodJenistujuan;
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