package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujbangsa entity provides the base persistence definition of the
 * Tblrujbangsa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujbangsa implements java.io.Serializable {

	// Fields

	private Long idBangsa;
	private String kodBangsa;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujbangsa() {
	}

	/** minimal constructor */
	public AbstractTblrujbangsa(Long idBangsa) {
		this.idBangsa = idBangsa;
	}

	/** full constructor */
	public AbstractTblrujbangsa(Long idBangsa, String kodBangsa,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idBangsa = idBangsa;
		this.kodBangsa = kodBangsa;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBangsa() {
		return this.idBangsa;
	}

	public void setIdBangsa(Long idBangsa) {
		this.idBangsa = idBangsa;
	}

	public String getKodBangsa() {
		return this.kodBangsa;
	}

	public void setKodBangsa(String kodBangsa) {
		this.kodBangsa = kodBangsa;
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