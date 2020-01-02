package ekptg.model.entities;

/**
 * AbstractTblstatuspengguna entity provides the base persistence definition of
 * the Tblstatuspengguna entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblstatuspengguna implements java.io.Serializable {

	// Fields

	private Long idStatusPengguna;
	private String kodStatusPengguna;
	private String statusPengguna;

	// Constructors

	/** default constructor */
	public AbstractTblstatuspengguna() {
	}

	/** full constructor */
	public AbstractTblstatuspengguna(String kodStatusPengguna,
			String statusPengguna) {
		this.kodStatusPengguna = kodStatusPengguna;
		this.statusPengguna = statusPengguna;
	}

	// Property accessors

	public Long getIdStatusPengguna() {
		return this.idStatusPengguna;
	}

	public void setIdStatusPengguna(Long idStatusPengguna) {
		this.idStatusPengguna = idStatusPengguna;
	}

	public String getKodStatusPengguna() {
		return this.kodStatusPengguna;
	}

	public void setKodStatusPengguna(String kodStatusPengguna) {
		this.kodStatusPengguna = kodStatusPengguna;
	}

	public String getStatusPengguna() {
		return this.statusPengguna;
	}

	public void setStatusPengguna(String statusPengguna) {
		this.statusPengguna = statusPengguna;
	}

}