package ekptg.model.entities;

/**
 * AbstractTblnegara entity provides the base persistence definition of the
 * Tblnegara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblnegara implements java.io.Serializable {

	// Fields

	private Long idNegara;
	private String kodNegara;
	private String namaNegara;

	// Constructors

	/** default constructor */
	public AbstractTblnegara() {
	}

	/** full constructor */
	public AbstractTblnegara(String kodNegara, String namaNegara) {
		this.kodNegara = kodNegara;
		this.namaNegara = namaNegara;
	}

	// Property accessors

	public Long getIdNegara() {
		return this.idNegara;
	}

	public void setIdNegara(Long idNegara) {
		this.idNegara = idNegara;
	}

	public String getKodNegara() {
		return this.kodNegara;
	}

	public void setKodNegara(String kodNegara) {
		this.kodNegara = kodNegara;
	}

	public String getNamaNegara() {
		return this.namaNegara;
	}

	public void setNamaNegara(String namaNegara) {
		this.namaNegara = namaNegara;
	}

}