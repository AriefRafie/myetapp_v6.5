package ekptg.model.entities;

/**
 * AbstractTblstatus entity provides the base persistence definition of the
 * Tblstatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblstatus implements java.io.Serializable {

	// Fields

	private Long idStatus;
	private String kodStatus;
	private String status;

	// Constructors

	/** default constructor */
	public AbstractTblstatus() {
	}

	/** full constructor */
	public AbstractTblstatus(String kodStatus, String status) {
		this.kodStatus = kodStatus;
		this.status = status;
	}

	// Property accessors

	public Long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getKodStatus() {
		return this.kodStatus;
	}

	public void setKodStatus(String kodStatus) {
		this.kodStatus = kodStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}