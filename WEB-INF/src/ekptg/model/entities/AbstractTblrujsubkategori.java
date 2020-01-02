package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujsubkategori entity provides the base persistence definition of
 * the Tblrujsubkategori entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujsubkategori implements java.io.Serializable {

	// Fields

	private Long idSubkategori;
	private Long idKategori;
	private String kodSubkategori;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujsubkategori() {
	}

	/** minimal constructor */
	public AbstractTblrujsubkategori(Long idKategori) {
		this.idKategori = idKategori;
	}

	/** full constructor */
	public AbstractTblrujsubkategori(Long idKategori, String kodSubkategori,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idKategori = idKategori;
		this.kodSubkategori = kodSubkategori;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdSubkategori() {
		return this.idSubkategori;
	}

	public void setIdSubkategori(Long idSubkategori) {
		this.idSubkategori = idSubkategori;
	}

	public Long getIdKategori() {
		return this.idKategori;
	}

	public void setIdKategori(Long idKategori) {
		this.idKategori = idKategori;
	}

	public String getKodSubkategori() {
		return this.kodSubkategori;
	}

	public void setKodSubkategori(String kodSubkategori) {
		this.kodSubkategori = kodSubkategori;
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