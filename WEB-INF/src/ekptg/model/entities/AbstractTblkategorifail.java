package ekptg.model.entities;

/**
 * AbstractTblkategorifail entity provides the base persistence definition of
 * the Tblkategorifail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblkategorifail implements java.io.Serializable {

	// Fields

	private Long idKategoriFail;
	private String kodKategoriFail;
	private String kategoriFail;
	private String keterangan;

	// Constructors

	/** default constructor */
	public AbstractTblkategorifail() {
	}

	/** full constructor */
	public AbstractTblkategorifail(String kodKategoriFail, String kategoriFail,
			String keterangan) {
		this.kodKategoriFail = kodKategoriFail;
		this.kategoriFail = kategoriFail;
		this.keterangan = keterangan;
	}

	// Property accessors

	public Long getIdKategoriFail() {
		return this.idKategoriFail;
	}

	public void setIdKategoriFail(Long idKategoriFail) {
		this.idKategoriFail = idKategoriFail;
	}

	public String getKodKategoriFail() {
		return this.kodKategoriFail;
	}

	public void setKodKategoriFail(String kodKategoriFail) {
		this.kodKategoriFail = kodKategoriFail;
	}

	public String getKategoriFail() {
		return this.kategoriFail;
	}

	public void setKategoriFail(String kategoriFail) {
		this.kategoriFail = kategoriFail;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}