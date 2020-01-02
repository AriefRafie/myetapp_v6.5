package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujjenisbayaran entity provides the base persistence definition of
 * the Tblrujjenisbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenisbayaran implements
		java.io.Serializable {

	// Fields

	private Long idJenisbayaran;
	private String kodJenisBayar;
	private String keterangan;
	private Long idBayaran;
	private Double amaun;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenisbayaran() {
	}

	/** minimal constructor */
	public AbstractTblrujjenisbayaran(Long idJenisbayaran,
			String kodJenisBayar) {
		this.idJenisbayaran = idJenisbayaran;
		this.kodJenisBayar = kodJenisBayar;
	}

	/** full constructor */
	public AbstractTblrujjenisbayaran(Long idJenisbayaran,
			String kodJenisBayar, String keterangan, Long idBayaran,
			Double amaun, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idJenisbayaran = idJenisbayaran;
		this.kodJenisBayar = kodJenisBayar;
		this.keterangan = keterangan;
		this.idBayaran = idBayaran;
		this.amaun = amaun;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenisbayaran() {
		return this.idJenisbayaran;
	}

	public void setIdJenisbayaran(Long idJenisbayaran) {
		this.idJenisbayaran = idJenisbayaran;
	}

	public String getKodJenisBayar() {
		return this.kodJenisBayar;
	}

	public void setKodJenisBayar(String kodJenisBayar) {
		this.kodJenisBayar = kodJenisBayar;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Long getIdBayaran() {
		return this.idBayaran;
	}

	public void setIdBayaran(Long idBayaran) {
		this.idBayaran = idBayaran;
	}

	public Double getAmaun() {
		return this.amaun;
	}

	public void setAmaun(Double amaun) {
		this.amaun = amaun;
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