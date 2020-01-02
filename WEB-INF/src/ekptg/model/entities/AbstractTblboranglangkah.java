package ekptg.model.entities;

/**
 * AbstractTblboranglangkah entity provides the base persistence definition of
 * the Tblboranglangkah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblboranglangkah implements java.io.Serializable {

	// Fields

	private Long idBorangLangkah;
	private String kodBorang;
	private String kodLangkah;

	// Constructors

	/** default constructor */
	public AbstractTblboranglangkah() {
	}

	/** full constructor */
	public AbstractTblboranglangkah(String kodBorang, String kodLangkah) {
		this.kodBorang = kodBorang;
		this.kodLangkah = kodLangkah;
	}

	// Property accessors

	public Long getIdBorangLangkah() {
		return this.idBorangLangkah;
	}

	public void setIdBorangLangkah(Long idBorangLangkah) {
		this.idBorangLangkah = idBorangLangkah;
	}

	public String getKodBorang() {
		return this.kodBorang;
	}

	public void setKodBorang(String kodBorang) {
		this.kodBorang = kodBorang;
	}

	public String getKodLangkah() {
		return this.kodLangkah;
	}

	public void setKodLangkah(String kodLangkah) {
		this.kodLangkah = kodLangkah;
	}

}