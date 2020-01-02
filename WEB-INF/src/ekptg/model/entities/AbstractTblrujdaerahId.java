package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujdaerahId entity provides the base persistence definition of the
 * TblrujdaerahId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujdaerahId implements java.io.Serializable {

	// Fields

	private Long idDaerah;
	private String kodDaerah;
	private String kodDaerahPTG;
	private String namaDaerah;
	private Long idNegeri;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujdaerahId() {
	}

	/** minimal constructor */
	public AbstractTblrujdaerahId(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	/** full constructor */
	public AbstractTblrujdaerahId(Long idDaerah, String kodDaerah, String kodDaerahPTG,
			String namaDaerah, Long idNegeri, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idDaerah = idDaerah;
		this.kodDaerah = kodDaerah;
		this.kodDaerahPTG = kodDaerahPTG;
		this.namaDaerah = namaDaerah;
		this.idNegeri = idNegeri;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public String getKodDaerah() {
		return this.kodDaerah;
	}

	public void setKodDaerah(String kodDaerah) {
		this.kodDaerah = kodDaerah;
	}
	
	public String getKodDaerahPTG() {
		return this.kodDaerahPTG;
	}
	
	public void setKodDaerahPTG(String kodDaerahPTG) {
		this.kodDaerahPTG = kodDaerahPTG;
	}
	
	public String getNamaDaerah() {
		return this.namaDaerah;
	}

	public void setNamaDaerah(String namaDaerah) {
		this.namaDaerah = namaDaerah;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblrujdaerahId))
			return false;
		AbstractTblrujdaerahId castOther = (AbstractTblrujdaerahId) other;

		return ((this.getIdDaerah() == castOther.getIdDaerah()) || (this
				.getIdDaerah() != null
				&& castOther.getIdDaerah() != null && this.getIdDaerah()
				.equals(castOther.getIdDaerah())))
				&& ((this.getKodDaerah() == castOther.getKodDaerah()) || (this
						.getKodDaerah() != null
						&& castOther.getKodDaerah() != null && this
						.getKodDaerah().equals(castOther.getKodDaerah())))
				&& ((this.getKodDaerahPTG() == castOther.getKodDaerahPTG()) || (this
						.getKodDaerahPTG() != null
						&& castOther.getKodDaerahPTG() != null && this
						.getKodDaerahPTG().equals(castOther.getKodDaerahPTG())))		
				&& ((this.getNamaDaerah() == castOther.getNamaDaerah()) || (this
						.getNamaDaerah() != null
						&& castOther.getNamaDaerah() != null && this
						.getNamaDaerah().equals(castOther.getNamaDaerah())))
				&& ((this.getIdNegeri() == castOther.getIdNegeri()) || (this
						.getIdNegeri() != null
						&& castOther.getIdNegeri() != null && this
						.getIdNegeri().equals(castOther.getIdNegeri())))
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
				+ (getIdDaerah() == null ? 0 : this.getIdDaerah().hashCode());
		result = 37 * result
				+ (getKodDaerah() == null ? 0 : this.getKodDaerah().hashCode());
		result = 37 * result
				+ (getKodDaerahPTG() == null ? 0 : this.getKodDaerahPTG().hashCode());
		result = 37
				* result
				+ (getNamaDaerah() == null ? 0 : this.getNamaDaerah()
						.hashCode());
		result = 37 * result
				+ (getIdNegeri() == null ? 0 : this.getIdNegeri().hashCode());
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