package ekptg.model.entities;

/**
 * AbstractTbljenispengguna entity provides the base persistence definition of
 * the Tbljenispengguna entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTbljenispengguna implements java.io.Serializable {

	// Fields

	private Long idJenisPengguna;
	private String kodJenisPengguna;
	private String jenisPengguna;
	private String keterangan;

	// Constructors

	/** default constructor */
	public AbstractTbljenispengguna() {
	}

	/** full constructor */
	public AbstractTbljenispengguna(String kodJenisPengguna,
			String jenisPengguna, String keterangan) {
		this.kodJenisPengguna = kodJenisPengguna;
		this.jenisPengguna = jenisPengguna;
		this.keterangan = keterangan;
	}

	// Property accessors

	public Long getIdJenisPengguna() {
		return this.idJenisPengguna;
	}

	public void setIdJenisPengguna(Long idJenisPengguna) {
		this.idJenisPengguna = idJenisPengguna;
	}

	public String getKodJenisPengguna() {
		return this.kodJenisPengguna;
	}

	public void setKodJenisPengguna(String kodJenisPengguna) {
		this.kodJenisPengguna = kodJenisPengguna;
	}

	public String getJenisPengguna() {
		return this.jenisPengguna;
	}

	public void setJenisPengguna(String jenisPengguna) {
		this.jenisPengguna = jenisPengguna;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}