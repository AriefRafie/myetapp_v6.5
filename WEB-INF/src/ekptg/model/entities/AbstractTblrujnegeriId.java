package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujnegeriId entity provides the base persistence definition of the
 * TblrujnegeriId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujnegeriId implements java.io.Serializable {

	// Fields

	private Long idNegeri;
	private Long idNegara;
	private String kodNegeri;
	private String namaNegeri;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujnegeriId() {
	}

	/** minimal constructor */
	public AbstractTblrujnegeriId(Long idNegeri, Long idNegara) {
		this.idNegeri = idNegeri;
		this.idNegara = idNegara;
	}

	/** full constructor */
	public AbstractTblrujnegeriId(Long idNegeri, Long idNegara,
			String kodNegeri, String namaNegeri, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idNegeri = idNegeri;
		this.idNegara = idNegara;
		this.kodNegeri = kodNegeri;
		this.namaNegeri = namaNegeri;
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

	public Long getIdNegara() {
		return this.idNegara;
	}

	public void setIdNegara(Long idNegara) {
		this.idNegara = idNegara;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblrujnegeriId))
			return false;
		AbstractTblrujnegeriId castOther = (AbstractTblrujnegeriId) other;

		return ((this.getIdNegeri() == castOther.getIdNegeri()) || (this
				.getIdNegeri() != null
				&& castOther.getIdNegeri() != null && this.getIdNegeri()
				.equals(castOther.getIdNegeri())))
				&& ((this.getIdNegara() == castOther.getIdNegara()) || (this
						.getIdNegara() != null
						&& castOther.getIdNegara() != null && this
						.getIdNegara().equals(castOther.getIdNegara())))
				&& ((this.getKodNegeri() == castOther.getKodNegeri()) || (this
						.getKodNegeri() != null
						&& castOther.getKodNegeri() != null && this
						.getKodNegeri().equals(castOther.getKodNegeri())))
				&& ((this.getNamaNegeri() == castOther.getNamaNegeri()) || (this
						.getNamaNegeri() != null
						&& castOther.getNamaNegeri() != null && this
						.getNamaNegeri().equals(castOther.getNamaNegeri())))
				&& ((this.getIdMasuk() == castOther.getIdMasuk()) || (this
						.getIdMasuk() != null
						&& castOther.getIdMasuk() != null && this.getIdMasuk()
						.equals(castOther.getIdMasuk())))
				&& ((this.getTarikhMasuk() == castOther.getTarikhMasuk()) || (this
						.getTarikhMasuk() != null
						&& castOther.getTarikhMasuk() != null && this
						.getTarikhMasuk().equals(castOther.getTarikhMasuk())))
				&& ((this.getIdKemaskini() == castOther.getIdKemaskini()) || (this
						.getIdKemaskini() != null
						&& castOther.getIdKemaskini() != null && this
						.getIdKemaskini().equals(castOther.getIdKemaskini())))
				&& ((this.getTarikhKemaskini() == castOther
						.getTarikhKemaskini()) || (this.getTarikhKemaskini() != null
						&& castOther.getTarikhKemaskini() != null && this
						.getTarikhKemaskini().equals(
								castOther.getTarikhKemaskini())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdNegeri() == null ? 0 : this.getIdNegeri().hashCode());
		result = 37 * result
				+ (getIdNegara() == null ? 0 : this.getIdNegara().hashCode());
		result = 37 * result
				+ (getKodNegeri() == null ? 0 : this.getKodNegeri().hashCode());
		result = 37
				* result
				+ (getNamaNegeri() == null ? 0 : this.getNamaNegeri()
						.hashCode());
		result = 37 * result
				+ (getIdMasuk() == null ? 0 : this.getIdMasuk().hashCode());
		result = 37
				* result
				+ (getTarikhMasuk() == null ? 0 : this.getTarikhMasuk()
						.hashCode());
		result = 37
				* result
				+ (getIdKemaskini() == null ? 0 : this.getIdKemaskini()
						.hashCode());
		result = 37
				* result
				+ (getTarikhKemaskini() == null ? 0 : this.getTarikhKemaskini()
						.hashCode());
		return result;
	}

}