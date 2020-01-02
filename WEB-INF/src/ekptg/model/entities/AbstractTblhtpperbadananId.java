package ekptg.model.entities;

/**
 * AbstractTblhtpperbadananId entity provides the base persistence definition of
 * the TblhtpperbadananId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpperbadananId implements
		java.io.Serializable {

	// Fields

	private Long idKementerian;
	private Long idAgensi;

	// Constructors

	/** default constructor */
	public AbstractTblhtpperbadananId() {
	}

	/** full constructor */
	public AbstractTblhtpperbadananId(Long idKementerian, Long idAgensi) {
		this.idKementerian = idKementerian;
		this.idAgensi = idAgensi;
	}

	// Property accessors

	public Long getIdKementerian() {
		return this.idKementerian;
	}

	public void setIdKementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
	}

	public Long getIdAgensi() {
		return this.idAgensi;
	}

	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblhtpperbadananId))
			return false;
		AbstractTblhtpperbadananId castOther = (AbstractTblhtpperbadananId) other;

		return ((this.getIdKementerian() == castOther.getIdKementerian()) || (this
				.getIdKementerian() != null
				&& castOther.getIdKementerian() != null && this
				.getIdKementerian().equals(castOther.getIdKementerian())))
				&& ((this.getIdAgensi() == castOther.getIdAgensi()) || (this
						.getIdAgensi() != null
						&& castOther.getIdAgensi() != null && this
						.getIdAgensi().equals(castOther.getIdAgensi())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdKementerian() == null ? 0 : this.getIdKementerian()
						.hashCode());
		result = 37 * result
				+ (getIdAgensi() == null ? 0 : this.getIdAgensi().hashCode());
		return result;
	}

}