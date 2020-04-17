package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujjenispb entity provides the base persistence definition of the
 * Tblrujjenispb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenispb implements java.io.Serializable {

	// Fields

	private Long idJenispb;
	private String kodJenisPb;
	private String keterangan;
	private String jenisDaftarPb;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenispb() {
	}

	/** minimal constructor */
	public AbstractTblrujjenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
	}

	/** full constructor */
	public AbstractTblrujjenispb(Long idJenispb, String kodJenisPb,
			String keterangan, String jenisDaftarPb, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idJenispb = idJenispb;
		this.kodJenisPb = kodJenisPb;
		this.keterangan = keterangan;
		this.jenisDaftarPb = jenisDaftarPb;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenispb() {
		return this.idJenispb;
	}

	public void setIdJenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
	}

	public String getKodJenisPb() {
		return this.kodJenisPb;
	}

	public void setKodJenisPb(String kodJenisPb) {
		this.kodJenisPb = kodJenisPb;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getJenisDaftarPb() {
		return this.jenisDaftarPb;
	}

	public void setJenisDaftarPb(String jenisDaftarPb) {
		this.jenisDaftarPb = jenisDaftarPb;
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