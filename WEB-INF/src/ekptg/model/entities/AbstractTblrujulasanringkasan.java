package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujulasanringkasan entity provides the base persistence definition of the
 * Tblrujulasanringkasan entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujulasanringkasan implements java.io.Serializable {

	// Fields

	private Long idUlasanringkasan;
	private String kodUlasanringkasan;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujulasanringkasan() {
	}

	/** full constructor */
	public AbstractTblrujulasanringkasan(String kodUlasanringkasan, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.kodUlasanringkasan = kodUlasanringkasan;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdUlasanringkasan() {
		return this.idUlasanringkasan;
	}

	public void setIdUlasanringkasan(Long idUlasanringkasan) {
		this.idUlasanringkasan = idUlasanringkasan;
	}

	public String getKodUlasanringkasan() {
		return this.kodUlasanringkasan;
	}

	public void setKodUlasanringkasan(String kodUlasanringkasan) {
		this.kodUlasanringkasan = kodUlasanringkasan;
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