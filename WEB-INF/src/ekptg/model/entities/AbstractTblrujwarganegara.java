package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujwarganegara entity provides the base persistence definition of
 * the Tblrujwarganegara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujwarganegara implements java.io.Serializable {

	// Fields

	private Long idWarganegara;
	private String kodWarga;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujwarganegara() {
	}

	/** minimal constructor */
	public AbstractTblrujwarganegara(Long idWarganegara) {
		this.idWarganegara = idWarganegara;
	}

	/** full constructor */
	public AbstractTblrujwarganegara(Long idWarganegara, String kodWarga,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idWarganegara = idWarganegara;
		this.kodWarga = kodWarga;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdWarganegara() {
		return this.idWarganegara;
	}

	public void setIdWarganegara(Long idWarganegara) {
		this.idWarganegara = idWarganegara;
	}

	public String getKodWarga() {
		return this.kodWarga;
	}

	public void setKodWarga(String kodWarga) {
		this.kodWarga = kodWarga;
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