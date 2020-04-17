package ekptg.model.entities;

/**
 * AbstractTbltugas entity provides the base persistence definition of the
 * Tbltugas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTbltugas implements java.io.Serializable {

	// Fields

	private Long idTugas;
	private String kodKumpulan;
	private String kodLangkah;

	// Constructors

	/** default constructor */
	public AbstractTbltugas() {
	}

	/** full constructor */
	public AbstractTbltugas(String kodKumpulan, String kodLangkah) {
		this.kodKumpulan = kodKumpulan;
		this.kodLangkah = kodLangkah;
	}

	// Property accessors

	public Long getIdTugas() {
		return this.idTugas;
	}

	public void setIdTugas(Long idTugas) {
		this.idTugas = idTugas;
	}

	public String getKodKumpulan() {
		return this.kodKumpulan;
	}

	public void setKodKumpulan(String kodKumpulan) {
		this.kodKumpulan = kodKumpulan;
	}

	public String getKodLangkah() {
		return this.kodLangkah;
	}

	public void setKodLangkah(String kodLangkah) {
		this.kodLangkah = kodLangkah;
	}

}