package ekptg.model.entities;

/**
 * AbstractTblborangproses entity provides the base persistence definition of
 * the Tblborangproses entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblborangproses implements java.io.Serializable {

	// Fields

	private Long idBorangProses;
	private String kodBorang;
	private String kodProses;

	// Constructors

	/** default constructor */
	public AbstractTblborangproses() {
	}

	/** full constructor */
	public AbstractTblborangproses(String kodBorang, String kodProses) {
		this.kodBorang = kodBorang;
		this.kodProses = kodProses;
	}

	// Property accessors

	public Long getIdBorangProses() {
		return this.idBorangProses;
	}

	public void setIdBorangProses(Long idBorangProses) {
		this.idBorangProses = idBorangProses;
	}

	public String getKodBorang() {
		return this.kodBorang;
	}

	public void setKodBorang(String kodBorang) {
		this.kodBorang = kodBorang;
	}

	public String getKodProses() {
		return this.kodProses;
	}

	public void setKodProses(String kodProses) {
		this.kodProses = kodProses;
	}

}