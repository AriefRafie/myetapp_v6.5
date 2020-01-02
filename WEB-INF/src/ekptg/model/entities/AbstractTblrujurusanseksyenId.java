package ekptg.model.entities;

/**
 * AbstractTblrujurusanseksyenId entity provides the base persistence definition
 * of the TblrujurusanseksyenId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujurusanseksyenId implements
		java.io.Serializable {

	// Fields

	private String idUrusanseksyen;
	private Tblrujurusan tblrujurusan;

	// Constructors

	/** default constructor */
	public AbstractTblrujurusanseksyenId() {
	}

	/** full constructor */
	public AbstractTblrujurusanseksyenId(String idUrusanseksyen,
			Tblrujurusan tblrujurusan) {
		this.idUrusanseksyen = idUrusanseksyen;
		this.tblrujurusan = tblrujurusan;
	}

	// Property accessors

	public String getIdUrusanseksyen() {
		return this.idUrusanseksyen;
	}

	public void setIdUrusanseksyen(String idUrusanseksyen) {
		this.idUrusanseksyen = idUrusanseksyen;
	}

	public Tblrujurusan getTblrujurusan() {
		return this.tblrujurusan;
	}

	public void setTblrujurusan(Tblrujurusan tblrujurusan) {
		this.tblrujurusan = tblrujurusan;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblrujurusanseksyenId))
			return false;
		AbstractTblrujurusanseksyenId castOther = (AbstractTblrujurusanseksyenId) other;

		return ((this.getIdUrusanseksyen() == castOther.getIdUrusanseksyen()) || (this
				.getIdUrusanseksyen() != null
				&& castOther.getIdUrusanseksyen() != null && this
				.getIdUrusanseksyen().equals(castOther.getIdUrusanseksyen())))
				&& ((this.getTblrujurusan() == castOther.getTblrujurusan()) || (this
						.getTblrujurusan() != null
						&& castOther.getTblrujurusan() != null && this
						.getTblrujurusan().equals(castOther.getTblrujurusan())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdUrusanseksyen() == null ? 0 : this.getIdUrusanseksyen()
						.hashCode());
		result = 37
				* result
				+ (getTblrujurusan() == null ? 0 : this.getTblrujurusan()
						.hashCode());
		return result;
	}

}