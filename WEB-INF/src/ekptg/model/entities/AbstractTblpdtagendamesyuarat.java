package ekptg.model.entities;



/**
 * AbstractTblpdtagendamesyuarat entity provides the base persistence definition
 * of the Tblpdtagendamesyuarat entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtagendamesyuarat implements
		java.io.Serializable {

	// Fields

	private Long idAgendamesyuarat;
	private Long idMesyuarat;
	private String agendaMesyuarat;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpdtagendamesyuarat() {
	}

	/** full constructor */
	public AbstractTblpdtagendamesyuarat(Long idMesyuarat,
			String agendaMesyuarat, Long idDb) {
		this.idMesyuarat = idMesyuarat;
		this.agendaMesyuarat = agendaMesyuarat;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdAgendamesyuarat() {
		return this.idAgendamesyuarat;
	}

	public void setIdAgendamesyuarat(Long idAgendamesyuarat) {
		this.idAgendamesyuarat = idAgendamesyuarat;
	}

	public Long getIdMesyuarat() {
		return this.idMesyuarat;
	}

	public void setIdMesyuarat(Long idMesyuarat) {
		this.idMesyuarat = idMesyuarat;
	}

	public String getAgendaMesyuarat() {
		return this.agendaMesyuarat;
	}

	public void setAgendaMesyuarat(String agendaMesyuarat) {
		this.agendaMesyuarat = agendaMesyuarat;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}