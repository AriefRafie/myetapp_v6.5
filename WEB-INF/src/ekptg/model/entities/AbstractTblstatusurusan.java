package ekptg.model.entities;

/**
 * AbstractTblstatusurusan entity provides the base persistence definition of
 * the Tblstatusurusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblstatusurusan implements java.io.Serializable {

	// Fields

	private Long idStatusUrusan;
	private String kodStatusUrusan;
	private String statusUrusan;

	// Constructors

	/** default constructor */
	public AbstractTblstatusurusan() {
	}

	/** full constructor */
	public AbstractTblstatusurusan(String kodStatusUrusan, String statusUrusan) {
		this.kodStatusUrusan = kodStatusUrusan;
		this.statusUrusan = statusUrusan;
	}

	// Property accessors

	public Long getIdStatusUrusan() {
		return this.idStatusUrusan;
	}

	public void setIdStatusUrusan(Long idStatusUrusan) {
		this.idStatusUrusan = idStatusUrusan;
	}

	public String getKodStatusUrusan() {
		return this.kodStatusUrusan;
	}

	public void setKodStatusUrusan(String kodStatusUrusan) {
		this.kodStatusUrusan = kodStatusUrusan;
	}

	public String getStatusUrusan() {
		return this.statusUrusan;
	}

	public void setStatusUrusan(String statusUrusan) {
		this.statusUrusan = statusUrusan;
	}

}