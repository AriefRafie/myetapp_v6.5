package ekptg.model.entities;

/**
 * AbstractTblnegeri entity provides the base persistence definition of the
 * Tblnegeri entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblnegeri implements java.io.Serializable {

	// Fields

	private Long idNegeri;
	private String kodNegeri;
	private String kodNegara;
	private String namaNegeri;

	// Constructors

	/** default constructor */
	public AbstractTblnegeri() {
	}

	/** full constructor */
	public AbstractTblnegeri(String kodNegeri, String kodNegara,
			String namaNegeri) {
		this.kodNegeri = kodNegeri;
		this.kodNegara = kodNegara;
		this.namaNegeri = namaNegeri;
	}

	// Property accessors

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getKodNegeri() {
		return this.kodNegeri;
	}

	public void setKodNegeri(String kodNegeri) {
		this.kodNegeri = kodNegeri;
	}

	public String getKodNegara() {
		return this.kodNegara;
	}

	public void setKodNegara(String kodNegara) {
		this.kodNegara = kodNegara;
	}

	public String getNamaNegeri() {
		return this.namaNegeri;
	}

	public void setNamaNegeri(String namaNegeri) {
		this.namaNegeri = namaNegeri;
	}

}