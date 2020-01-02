package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujnegaraId entity provides the base persistence definition of the
 * TblrujnegaraId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujnegaraId implements java.io.Serializable {

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
	public AbstractTblrujnegaraId() {
	}

	/** minimal constructor */
	public AbstractTblrujnegaraId(Long idNegara) {
		this.idNegara = idNegara;
	}

	/** full constructor */
	public AbstractTblrujnegaraId(Long idNegara, String kodNegara,
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblrujnegaraId))
			return false;
		AbstractTblrujnegaraId castOther = (AbstractTblrujnegaraId) other;

		return ((this.getIdNegara() == castOther.getIdNegara()) || (this
				.getIdNegara() != null
				&& castOther.getIdNegara() != null && this.getIdNegara()
				.equals(castOther.getIdNegara())))
				&& ((this.getKodNegara() == castOther.getKodNegara()) || (this
						.getKodNegara() != null
						&& castOther.getKodNegara() != null && this
						.getKodNegara().equals(castOther.getKodNegara())))
				&& ((this.getNamaNegara() == castOther.getNamaNegara()) || (this
						.getNamaNegara() != null
						&& castOther.getNamaNegara() != null && this
						.getNamaNegara().equals(castOther.getNamaNegara())))
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
				+ (getIdNegara() == null ? 0 : this.getIdNegara().hashCode());
		result = 37 * result
				+ (getKodNegara() == null ? 0 : this.getKodNegara().hashCode());
		result = 37
				* result
				+ (getNamaNegara() == null ? 0 : this.getNamaNegara()
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