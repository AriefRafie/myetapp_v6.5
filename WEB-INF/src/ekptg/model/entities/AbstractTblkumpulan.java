package ekptg.model.entities;

/**
 * AbstractTblkumpulan entity provides the base persistence definition of the
 * Tblkumpulan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblkumpulan implements java.io.Serializable {

	// Fields

	private Long idKumpulan;
	private String kodKumpulan;
	private String kumpulan;
	private String keterangan;

	// Constructors

	/** default constructor */
	public AbstractTblkumpulan() {
	}

	/** full constructor */
	public AbstractTblkumpulan(String kodKumpulan, String kumpulan,
			String keterangan) {
		this.kodKumpulan = kodKumpulan;
		this.kumpulan = kumpulan;
		this.keterangan = keterangan;
	}

	// Property accessors

	public Long getIdKumpulan() {
		return this.idKumpulan;
	}

	public void setIdKumpulan(Long idKumpulan) {
		this.idKumpulan = idKumpulan;
	}

	public String getKodKumpulan() {
		return this.kodKumpulan;
	}

	public void setKodKumpulan(String kodKumpulan) {
		this.kodKumpulan = kodKumpulan;
	}

	public String getKumpulan() {
		return this.kumpulan;
	}

	public void setKumpulan(String kumpulan) {
		this.kumpulan = kumpulan;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}