package ekptg.model.entities;

/**
 * AbstractTblborang entity provides the base persistence definition of the
 * Tblborang entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblborang implements java.io.Serializable {

	// Fields

	private Long idBorang;
	private String kodBorang;
	private String namaBorang;
	private String keterangan;

	// Constructors

	/** default constructor */
	public AbstractTblborang() {
	}

	/** full constructor */
	public AbstractTblborang(String kodBorang, String namaBorang,
			String keterangan) {
		this.kodBorang = kodBorang;
		this.namaBorang = namaBorang;
		this.keterangan = keterangan;
	}

	// Property accessors

	public Long getIdBorang() {
		return this.idBorang;
	}

	public void setIdBorang(Long idBorang) {
		this.idBorang = idBorang;
	}

	public String getKodBorang() {
		return this.kodBorang;
	}

	public void setKodBorang(String kodBorang) {
		this.kodBorang = kodBorang;
	}

	public String getNamaBorang() {
		return this.namaBorang;
	}

	public void setNamaBorang(String namaBorang) {
		this.namaBorang = namaBorang;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}