package ekptg.model.entities;

/**
 * AbstractTblcapaianproses entity provides the base persistence definition of
 * the Tblcapaianproses entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblcapaianproses implements java.io.Serializable {

	// Fields

	private Long idCapaianProses;
	private String kodProses;
	private String kodKumpulan;

	// Constructors

	/** default constructor */
	public AbstractTblcapaianproses() {
	}

	/** full constructor */
	public AbstractTblcapaianproses(String kodProses, String kodKumpulan) {
		this.kodProses = kodProses;
		this.kodKumpulan = kodKumpulan;
	}

	// Property accessors

	public Long getIdCapaianProses() {
		return this.idCapaianProses;
	}

	public void setIdCapaianProses(Long idCapaianProses) {
		this.idCapaianProses = idCapaianProses;
	}

	public String getKodProses() {
		return this.kodProses;
	}

	public void setKodProses(String kodProses) {
		this.kodProses = kodProses;
	}

	public String getKodKumpulan() {
		return this.kodKumpulan;
	}

	public void setKodKumpulan(String kodKumpulan) {
		this.kodKumpulan = kodKumpulan;
	}

}