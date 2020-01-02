package ekptg.model.entities;

/**
 * AbstractTblstatusaktiviti entity provides the base persistence definition of
 * the Tblstatusaktiviti entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblstatusaktiviti implements java.io.Serializable {

	// Fields

	private Long idStatusAktiviti;
	private String kodStatusAktiviti;
	private String statusAktiviti;

	// Constructors

	/** default constructor */
	public AbstractTblstatusaktiviti() {
	}

	/** full constructor */
	public AbstractTblstatusaktiviti(String kodStatusAktiviti,
			String statusAktiviti) {
		this.kodStatusAktiviti = kodStatusAktiviti;
		this.statusAktiviti = statusAktiviti;
	}

	// Property accessors

	public Long getIdStatusAktiviti() {
		return this.idStatusAktiviti;
	}

	public void setIdStatusAktiviti(Long idStatusAktiviti) {
		this.idStatusAktiviti = idStatusAktiviti;
	}

	public String getKodStatusAktiviti() {
		return this.kodStatusAktiviti;
	}

	public void setKodStatusAktiviti(String kodStatusAktiviti) {
		this.kodStatusAktiviti = kodStatusAktiviti;
	}

	public String getStatusAktiviti() {
		return this.statusAktiviti;
	}

	public void setStatusAktiviti(String statusAktiviti) {
		this.statusAktiviti = statusAktiviti;
	}

}