package ekptg.model.entities;

/**
 * AbstractTblrujsuburusanId entity provides the base persistence definition of
 * the TblrujsuburusanId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujsuburusanId implements java.io.Serializable {

	// Fields

	private Long idSuburusan;
	private Tblrujurusan tblrujurusan;

	// Constructors

	/** default constructor */
	public AbstractTblrujsuburusanId() {
	}

	/** full constructor */
	public AbstractTblrujsuburusanId(Long idSuburusan, Tblrujurusan tblrujurusan) {
		this.idSuburusan = idSuburusan;
		this.tblrujurusan = tblrujurusan;
	}

	// Property accessors

	public Long getIdSuburusan() {
		return this.idSuburusan;
	}

	public void setIdSuburusan(Long idSuburusan) {
		this.idSuburusan = idSuburusan;
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
		if (!(other instanceof AbstractTblrujsuburusanId))
			return false;
		AbstractTblrujsuburusanId castOther = (AbstractTblrujsuburusanId) other;

		return ((this.getIdSuburusan() == castOther.getIdSuburusan()) || (this
				.getIdSuburusan() != null
				&& castOther.getIdSuburusan() != null && this.getIdSuburusan()
				.equals(castOther.getIdSuburusan())))
				&& ((this.getTblrujurusan() == castOther.getTblrujurusan()) || (this
						.getTblrujurusan() != null
						&& castOther.getTblrujurusan() != null && this
						.getTblrujurusan().equals(castOther.getTblrujurusan())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdSuburusan() == null ? 0 : this.getIdSuburusan()
						.hashCode());
		result = 37
				* result
				+ (getTblrujurusan() == null ? 0 : this.getTblrujurusan()
						.hashCode());
		return result;
	}

}