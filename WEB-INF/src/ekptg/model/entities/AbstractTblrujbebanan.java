package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujbebanan entity provides the base persistence definition of the
 * Tblrujbebanan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujbebanan implements java.io.Serializable {

	// Fields

	private Long idRujbebanan;
	private String kodBebanan;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujbebanan() {
	}

	/** minimal constructor */
	public AbstractTblrujbebanan(Long idRujbebanan) {
		this.idRujbebanan = idRujbebanan;
	}

	/** full constructor */
	public AbstractTblrujbebanan(Long idRujbebanan, String kodBebanan,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idRujbebanan = idRujbebanan;
		this.kodBebanan = kodBebanan;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdRujbebanan() {
		return this.idRujbebanan;
	}

	public void setIdRujbebanan(Long idRujbebanan) {
		this.idRujbebanan = idRujbebanan;
	}

	public String getKodBebanan() {
		return this.kodBebanan;
	}

	public void setKodBebanan(String kodBebanan) {
		this.kodBebanan = kodBebanan;
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