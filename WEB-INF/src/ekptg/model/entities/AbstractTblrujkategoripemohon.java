package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujkategoripemohon entity provides the base persistence definition
 * of the Tblrujkategoripemohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujkategoripemohon implements
		java.io.Serializable {

	// Fields

	private Long idKategoripemohon;
	private String kodKategoripemohon;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujkategoripemohon() {
	}

	/** full constructor */
	public AbstractTblrujkategoripemohon(String kodKategoripemohon,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.kodKategoripemohon = kodKategoripemohon;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdKategoripemohon() {
		return this.idKategoripemohon;
	}

	public void setIdKategoripemohon(Long idKategoripemohon) {
		this.idKategoripemohon = idKategoripemohon;
	}

	public String getKodKategoripemohon() {
		return this.kodKategoripemohon;
	}

	public void setKodKategoripemohon(String kodKategoripemohon) {
		this.kodKategoripemohon = kodKategoripemohon;
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