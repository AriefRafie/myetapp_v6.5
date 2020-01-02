package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujdaerahpenggawaId entity provides the base persistence definition of the
 * TblrujdaerahpenggawaId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractTblrujdaerahpenggawaId implements java.io.Serializable {

	// Fields

	private Long idDaerahPenggawa;
	private String kodDaerahPenggawa;
	private String namaDaerahPenggawa;
	private Long idDaerah;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujdaerahpenggawaId() {
	}

	/** minimal constructor */
	public AbstractTblrujdaerahpenggawaId(Long idDaerahPenggawa) {
		this.idDaerahPenggawa = idDaerahPenggawa;
	}

	/** full constructor */
	public AbstractTblrujdaerahpenggawaId(Long idDaerahPenggawa, String kodDaerahPenggawa,
			String namaDaerahPenggawa, Long idDaerah, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idDaerahPenggawa = idDaerahPenggawa;
		this.kodDaerahPenggawa = kodDaerahPenggawa;
		this.namaDaerahPenggawa = namaDaerahPenggawa;
		this.idDaerah = idDaerah;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdDaerahPenggawa() {
		return this.idDaerahPenggawa;
	}

	public void setIdDaerahPenggawa(Long idDaerahPenggawa) {
		this.idDaerahPenggawa = idDaerahPenggawa;
	}

	public String getKodDaerahPenggawa() {
		return this.kodDaerahPenggawa;
	}

	public void setKodDaerahPenggawa(String kodDaerahPenggawa) {
		this.kodDaerahPenggawa = kodDaerahPenggawa;
	}

	public String getNamaDaerahPenggawa() {
		return this.namaDaerahPenggawa;
	}

	public void setNamaDaerahPenggawa(String namaDaerahPenggawa) {
		this.namaDaerahPenggawa = namaDaerahPenggawa;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
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
		if (!(other instanceof AbstractTblrujdaerahpenggawaId))
			return false;
		AbstractTblrujdaerahpenggawaId castOther = (AbstractTblrujdaerahpenggawaId) other;

		return ((this.getIdDaerahPenggawa() == castOther.getIdDaerahPenggawa()) || (this
				.getIdDaerahPenggawa() != null
				&& castOther.getIdDaerahPenggawa() != null && this.getIdDaerahPenggawa()
				.equals(castOther.getIdDaerah())))
				&& ((this.getKodDaerahPenggawa() == castOther.getKodDaerahPenggawa()) || (this
						.getKodDaerahPenggawa() != null
						&& castOther.getKodDaerahPenggawa() != null && this
						.getKodDaerahPenggawa().equals(castOther.getKodDaerahPenggawa())))
				&& ((this.getNamaDaerahPenggawa() == castOther.getNamaDaerahPenggawa()) || (this
						.getNamaDaerahPenggawa() != null
						&& castOther.getNamaDaerahPenggawa() != null && this
						.getNamaDaerahPenggawa().equals(castOther.getNamaDaerahPenggawa())))
				&& ((this.getIdDaerah() == castOther.getIdDaerah()) || (this
						.getIdDaerah() != null
						&& castOther.getIdDaerah() != null && this
						.getIdDaerah().equals(castOther.getIdDaerah())))
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
				+ (getIdDaerahPenggawa() == null ? 0 : this.getIdDaerahPenggawa().hashCode());
		result = 37 * result
				+ (getKodDaerahPenggawa() == null ? 0 : this.getKodDaerahPenggawa().hashCode());
		result = 37
				* result
				+ (getNamaDaerahPenggawa() == null ? 0 : this.getNamaDaerahPenggawa()
						.hashCode());
		result = 37 * result
				+ (getIdDaerah() == null ? 0 : this.getIdDaerah().hashCode());
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