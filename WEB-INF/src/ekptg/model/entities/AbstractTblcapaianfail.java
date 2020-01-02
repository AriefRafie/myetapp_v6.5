package ekptg.model.entities;

/**
 * AbstractTblcapaianfail entity provides the base persistence definition of the
 * Tblcapaianfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblcapaianfail implements java.io.Serializable {

	// Fields

	private Long idCapaianFail;
	private String kodFail;
	private String kodKumpulan;

	// Constructors

	/** default constructor */
	public AbstractTblcapaianfail() {
	}

	/** full constructor */
	public AbstractTblcapaianfail(String kodFail, String kodKumpulan) {
		this.kodFail = kodFail;
		this.kodKumpulan = kodKumpulan;
	}

	// Property accessors

	public Long getIdCapaianFail() {
		return this.idCapaianFail;
	}

	public void setIdCapaianFail(Long idCapaianFail) {
		this.idCapaianFail = idCapaianFail;
	}

	public String getKodFail() {
		return this.kodFail;
	}

	public void setKodFail(String kodFail) {
		this.kodFail = kodFail;
	}

	public String getKodKumpulan() {
		return this.kodKumpulan;
	}

	public void setKodKumpulan(String kodKumpulan) {
		this.kodKumpulan = kodKumpulan;
	}

}