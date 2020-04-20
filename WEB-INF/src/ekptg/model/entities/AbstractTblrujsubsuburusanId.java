package ekptg.model.entities;

/**
 * AbstractTblrujsuburusanId entity provides the base persistence definition of
 * the TblrujsuburusanId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujsubsuburusanId implements java.io.Serializable {

	// Fields

	private Long idSubsuburusan;
	private Tblrujsuburusan tblrujsuburusan;

	// Constructors

	/** default constructor */
	public AbstractTblrujsubsuburusanId() {
	}

	/** full constructor */
	public AbstractTblrujsubsuburusanId(Long idSubsuburusan, Tblrujsuburusan tblrujsuburusan) {
		this.idSubsuburusan = idSubsuburusan;
		this.tblrujsuburusan = tblrujsuburusan;
	}

	// Property accessors

	public Long getIdSubsuburusan() {
		return this.idSubsuburusan;
	}

	public void setIdSubsuburusan(Long idSubsuburusan) {
		this.idSubsuburusan = idSubsuburusan;
	}

	public Tblrujsuburusan getTblrujsuburusan() {
		return this.tblrujsuburusan;
	}

	public void setTblrujsuburusan(Tblrujsuburusan tblrujsuburusan) {
		this.tblrujsuburusan = tblrujsuburusan;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblrujsubsuburusanId))
			return false;
		AbstractTblrujsubsuburusanId castOther = (AbstractTblrujsubsuburusanId) other;

		return ((this.getIdSubsuburusan() == castOther.getIdSubsuburusan()) || (this
				.getIdSubsuburusan() != null
				&& castOther.getIdSubsuburusan() != null && this.getIdSubsuburusan()
				.equals(castOther.getIdSubsuburusan())))
				&& ((this.getTblrujsuburusan() == castOther.getTblrujsuburusan()) || (this
						.getTblrujsuburusan() != null
						&& castOther.getTblrujsuburusan() != null && this
						.getTblrujsuburusan().equals(castOther.getTblrujsuburusan())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdSubsuburusan() == null ? 0 : this.getIdSubsuburusan()
						.hashCode());
		result = 37
				* result
				+ (getIdSubsuburusan() == null ? 0 : this.getIdSubsuburusan()
						.hashCode());
		return result;
	}

}