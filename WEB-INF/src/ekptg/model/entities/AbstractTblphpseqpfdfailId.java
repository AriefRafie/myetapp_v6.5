package ekptg.model.entities;

/**
 * AbstractTblphpseqpfdfailId entity provides the base persistence definition of
 * the TblphpseqpfdfailId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpseqpfdfailId implements
		java.io.Serializable {

	// Fields

	private String kodSeksyen;
	private String kodUrusan;
	private String kodKementerian;
	private String kodNegeri;

	// Constructors

	/** default constructor */
	public AbstractTblphpseqpfdfailId() {
	}

	/** full constructor */
	public AbstractTblphpseqpfdfailId(String kodSeksyen, String kodUrusan,
			String kodKementerian, String kodNegeri) {
		this.kodSeksyen = kodSeksyen;
		this.kodUrusan = kodUrusan;
		this.kodKementerian = kodKementerian;
		this.kodNegeri = kodNegeri;
	}

	// Property accessors

	public String getKodSeksyen() {
		return this.kodSeksyen;
	}

	public void setKodSeksyen(String kodSeksyen) {
		this.kodSeksyen = kodSeksyen;
	}

	public String getKodUrusan() {
		return this.kodUrusan;
	}

	public void setKodUrusan(String kodUrusan) {
		this.kodUrusan = kodUrusan;
	}

	public String getKodKementerian() {
		return this.kodKementerian;
	}

	public void setKodKementerian(String kodKementerian) {
		this.kodKementerian = kodKementerian;
	}

	public String getKodNegeri() {
		return this.kodNegeri;
	}

	public void setKodNegeri(String kodNegeri) {
		this.kodNegeri = kodNegeri;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblphpseqpfdfailId))
			return false;
		AbstractTblphpseqpfdfailId castOther = (AbstractTblphpseqpfdfailId) other;

		return ((this.getKodSeksyen() == castOther.getKodSeksyen()) || (this
				.getKodSeksyen() != null
				&& castOther.getKodSeksyen() != null && this.getKodSeksyen()
				.equals(castOther.getKodSeksyen())))
				&& ((this.getKodUrusan() == castOther.getKodUrusan()) || (this
						.getKodUrusan() != null
						&& castOther.getKodUrusan() != null && this
						.getKodUrusan().equals(castOther.getKodUrusan())))
				&& ((this.getKodKementerian() == castOther.getKodKementerian()) || (this
						.getKodKementerian() != null
						&& castOther.getKodKementerian() != null && this
						.getKodKementerian().equals(
								castOther.getKodKementerian())))
				&& ((this.getKodNegeri() == castOther.getKodNegeri()) || (this
						.getKodNegeri() != null
						&& castOther.getKodNegeri() != null && this
						.getKodNegeri().equals(castOther.getKodNegeri())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getKodSeksyen() == null ? 0 : this.getKodSeksyen()
						.hashCode());
		result = 37 * result
				+ (getKodUrusan() == null ? 0 : this.getKodUrusan().hashCode());
		result = 37
				* result
				+ (getKodKementerian() == null ? 0 : this.getKodKementerian()
						.hashCode());
		result = 37 * result
				+ (getKodNegeri() == null ? 0 : this.getKodNegeri().hashCode());
		return result;
	}

}