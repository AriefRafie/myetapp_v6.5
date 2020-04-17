package ekptg.model.entities;


/**
 * AbstractTblpdtrujdokumenpekeliling entity provides the base persistence
 * definition of the Tblpdtrujdokumenpekeliling entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractTblpdtrujdokumenpekeliling implements
		java.io.Serializable {

	// Fields

	private Long idDokumenpekeliling;
	private String jenisDokumenPekeliling;
	private String kodJenisDokumenpekeliling;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpdtrujdokumenpekeliling() {
	}

	/** full constructor */
	public AbstractTblpdtrujdokumenpekeliling(String jenisDokumenPekeliling,
			String kodJenisDokumenpekeliling, Long idDb) {
		this.jenisDokumenPekeliling = jenisDokumenPekeliling;
		this.kodJenisDokumenpekeliling = kodJenisDokumenpekeliling;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdDokumenpekeliling() {
		return this.idDokumenpekeliling;
	}

	public void setIdDokumenpekeliling(Long idDokumenpekeliling) {
		this.idDokumenpekeliling = idDokumenpekeliling;
	}

	public String getJenisDokumenPekeliling() {
		return this.jenisDokumenPekeliling;
	}

	public void setJenisDokumenPekeliling(String jenisDokumenPekeliling) {
		this.jenisDokumenPekeliling = jenisDokumenPekeliling;
	}

	public String getKodJenisDokumenpekeliling() {
		return this.kodJenisDokumenpekeliling;
	}

	public void setKodJenisDokumenpekeliling(String kodJenisDokumenpekeliling) {
		this.kodJenisDokumenpekeliling = kodJenisDokumenpekeliling;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}