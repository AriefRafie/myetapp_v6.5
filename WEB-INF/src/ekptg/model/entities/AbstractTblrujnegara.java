package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujnegara entity provides the base persistence definition of the
 * Tblrujnegara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujnegara implements java.io.Serializable {

	// Fields

	private Long idNegara;
	private String kodNegara;
	private String namaNegara;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujnegara() {
	}

	/** minimal constructor */
	public AbstractTblrujnegara(Long idNegara) {
		this.idNegara = idNegara;
	}

	/** full constructor */
	public AbstractTblrujnegara(Long idNegara, String kodNegara,
			String namaNegara, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idNegara = idNegara;
		this.kodNegara = kodNegara;
		this.namaNegara = namaNegara;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdNegara() {
		return this.idNegara;
	}

	public void setIdNegara(Long idNegara) {
		this.idNegara = idNegara;
	}

	public String getKodNegara() {
		return this.kodNegara;
	}

	public void setKodNegara(String kodNegara) {
		this.kodNegara = kodNegara;
	}

	public String getNamaNegara() {
		return this.namaNegara;
	}

	public void setNamaNegara(String namaNegara) {
		this.namaNegara = namaNegara;
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