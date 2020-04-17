package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujmukim entity provides the base persistence definition of the
 * Tblrujmukim entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujmukim implements java.io.Serializable {

	// Fields

	private Long idMukim;
	private String kodMukim;
	private String namaMukim;
	private Long idDaerah;
	private Long idNegeri;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujmukim() {
	}

	/** minimal constructor */
	public AbstractTblrujmukim(Long idMukim, Long idDaerah, Long idNegeri) {
		this.idMukim = idMukim;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblrujmukim(Long idMukim, String kodMukim, String namaMukim,
			Long idDaerah, Long idNegeri, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idMukim = idMukim;
		this.kodMukim = kodMukim;
		this.namaMukim = namaMukim;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(Long idMukim) {
		this.idMukim = idMukim;
	}

	public String getKodMukim() {
		return this.kodMukim;
	}

	public void setKodMukim(String kodMukim) {
		this.kodMukim = kodMukim;
	}

	public String getNamaMukim() {
		return this.namaMukim;
	}

	public void setNamaMukim(String namaMukim) {
		this.namaMukim = namaMukim;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
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