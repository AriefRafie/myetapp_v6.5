package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujnegeri entity provides the base persistence definition of the
 * Tblrujnegeri entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujnegeri implements java.io.Serializable {

	// Fields

	private Long idNegeri;
	private String kodNegeri;
	private String namaNegeri;
	private Long idNegara;
	private String kodMampu;
	private String abbrev;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujnegeri() {
	}

	/** minimal constructor */
	public AbstractTblrujnegeri(Long idNegeri, Long idNegara) {
		this.idNegeri = idNegeri;
		this.idNegara = idNegara;
	}

	/** full constructor */
	public AbstractTblrujnegeri(Long idNegeri, String kodNegeri,
			String namaNegeri, Long idNegara, String kodMampu, String abbrev,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idNegeri = idNegeri;
		this.kodNegeri = kodNegeri;
		this.namaNegeri = namaNegeri;
		this.idNegara = idNegara;
		this.kodMampu = kodMampu;
		this.abbrev = abbrev;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getKodNegeri() {
		return this.kodNegeri;
	}

	public void setKodNegeri(String kodNegeri) {
		this.kodNegeri = kodNegeri;
	}

	public String getNamaNegeri() {
		return this.namaNegeri;
	}

	public void setNamaNegeri(String namaNegeri) {
		this.namaNegeri = namaNegeri;
	}

	public Long getIdNegara() {
		return this.idNegara;
	}

	public void setIdNegara(Long idNegara) {
		this.idNegara = idNegara;
	}

	public String getKodMampu() {
		return this.kodMampu;
	}

	public void setKodMampu(String kodMampu) {
		this.kodMampu = kodMampu;
	}

	public String getAbbrev() {
		return this.abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
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