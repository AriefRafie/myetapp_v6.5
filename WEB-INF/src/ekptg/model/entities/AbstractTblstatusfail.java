package ekptg.model.entities;

/**
 * AbstractTblstatusfail entity provides the base persistence definition of the
 * Tblstatusfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblstatusfail implements java.io.Serializable {

	// Fields

	private Long idStatusFail;
	private String kodStatusFail;
	private String statusFail;

	// Constructors

	/** default constructor */
	public AbstractTblstatusfail() {
	}

	/** full constructor */
	public AbstractTblstatusfail(String kodStatusFail, String statusFail) {
		this.kodStatusFail = kodStatusFail;
		this.statusFail = statusFail;
	}

	// Property accessors

	public Long getIdStatusFail() {
		return this.idStatusFail;
	}

	public void setIdStatusFail(Long idStatusFail) {
		this.idStatusFail = idStatusFail;
	}

	public String getKodStatusFail() {
		return this.kodStatusFail;
	}

	public void setKodStatusFail(String kodStatusFail) {
		this.kodStatusFail = kodStatusFail;
	}

	public String getStatusFail() {
		return this.statusFail;
	}

	public void setStatusFail(String statusFail) {
		this.statusFail = statusFail;
	}

}