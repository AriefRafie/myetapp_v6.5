package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujpihakberkepentingan entity provides the base persistence
 * definition of the Tblrujpihakberkepentingan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujpihakberkepentingan implements
		java.io.Serializable {

	// Fields

	private Long idRujpihakberkepentingan;
	private String kodNopb;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujpihakberkepentingan() {
	}

	/** minimal constructor */
	public AbstractTblrujpihakberkepentingan(Long idRujpihakberkepentingan) {
		this.idRujpihakberkepentingan = idRujpihakberkepentingan;
	}

	/** full constructor */
	public AbstractTblrujpihakberkepentingan(Long idRujpihakberkepentingan,
			String kodNopb, String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idRujpihakberkepentingan = idRujpihakberkepentingan;
		this.kodNopb = kodNopb;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdRujpihakberkepentingan() {
		return this.idRujpihakberkepentingan;
	}

	public void setIdRujpihakberkepentingan(Long idRujpihakberkepentingan) {
		this.idRujpihakberkepentingan = idRujpihakberkepentingan;
	}

	public String getKodNopb() {
		return this.kodNopb;
	}

	public void setKodNopb(String kodNopb) {
		this.kodNopb = kodNopb;
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