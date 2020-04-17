package ekptg.model.entities;

/**
 * AbstractTblkumpulanpengguna entity provides the base persistence definition
 * of the Tblkumpulanpengguna entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblkumpulanpengguna implements
		java.io.Serializable {

	// Fields

	private Long idKumpulanPengguna;
	private String kodPengguna;
	private String kodKumpulan;

	// Constructors

	/** default constructor */
	public AbstractTblkumpulanpengguna() {
	}

	/** full constructor */
	public AbstractTblkumpulanpengguna(String kodPengguna, String kodKumpulan) {
		this.kodPengguna = kodPengguna;
		this.kodKumpulan = kodKumpulan;
	}

	// Property accessors

	public Long getIdKumpulanPengguna() {
		return this.idKumpulanPengguna;
	}

	public void setIdKumpulanPengguna(Long idKumpulanPengguna) {
		this.idKumpulanPengguna = idKumpulanPengguna;
	}

	public String getKodPengguna() {
		return this.kodPengguna;
	}

	public void setKodPengguna(String kodPengguna) {
		this.kodPengguna = kodPengguna;
	}

	public String getKodKumpulan() {
		return this.kodKumpulan;
	}

	public void setKodKumpulan(String kodKumpulan) {
		this.kodKumpulan = kodKumpulan;
	}

}