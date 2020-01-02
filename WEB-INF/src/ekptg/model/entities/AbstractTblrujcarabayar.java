package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujcarabayar entity provides the base persistence definition of
 * the Tblrujcarabayar entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujcarabayar implements java.io.Serializable {

	// Fields

	private Long idCarabayar;
	private String kodCaraBayar;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujcarabayar() {
	}

	/** minimal constructor */
	public AbstractTblrujcarabayar(Long idCarabayar) {
		this.idCarabayar = idCarabayar;
	}

	/** full constructor */
	public AbstractTblrujcarabayar(Long idCarabayar, String kodCaraBayar,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idCarabayar = idCarabayar;
		this.kodCaraBayar = kodCaraBayar;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdCarabayar() {
		return this.idCarabayar;
	}

	public void setIdCarabayar(Long idCarabayar) {
		this.idCarabayar = idCarabayar;
	}

	public String getKodCaraBayar() {
		return this.kodCaraBayar;
	}

	public void setKodCaraBayar(String kodCaraBayar) {
		this.kodCaraBayar = kodCaraBayar;
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