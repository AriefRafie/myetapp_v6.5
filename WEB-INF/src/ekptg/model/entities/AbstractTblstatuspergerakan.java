package ekptg.model.entities;

/**
 * AbstractTblstatuspergerakan entity provides the base persistence definition
 * of the Tblstatuspergerakan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblstatuspergerakan implements
		java.io.Serializable {

	// Fields

	private Long idStatusPergerakan;
	private String kodStatusPergerakan;
	private String statusPergerakan;

	// Constructors

	/** default constructor */
	public AbstractTblstatuspergerakan() {
	}

	/** full constructor */
	public AbstractTblstatuspergerakan(String kodStatusPergerakan,
			String statusPergerakan) {
		this.kodStatusPergerakan = kodStatusPergerakan;
		this.statusPergerakan = statusPergerakan;
	}

	// Property accessors

	public Long getIdStatusPergerakan() {
		return this.idStatusPergerakan;
	}

	public void setIdStatusPergerakan(Long idStatusPergerakan) {
		this.idStatusPergerakan = idStatusPergerakan;
	}

	public String getKodStatusPergerakan() {
		return this.kodStatusPergerakan;
	}

	public void setKodStatusPergerakan(String kodStatusPergerakan) {
		this.kodStatusPergerakan = kodStatusPergerakan;
	}

	public String getStatusPergerakan() {
		return this.statusPergerakan;
	}

	public void setStatusPergerakan(String statusPergerakan) {
		this.statusPergerakan = statusPergerakan;
	}

}