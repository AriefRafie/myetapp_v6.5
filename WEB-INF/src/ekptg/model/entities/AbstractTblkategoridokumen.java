package ekptg.model.entities;

/**
 * AbstractTblkategoridokumen entity provides the base persistence definition of
 * the Tblkategoridokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblkategoridokumen implements
		java.io.Serializable {

	// Fields

	private Long idKategoriDokumen;
	private String kodKategoriDokumen;
	private String kategoriDokumen;
	private String keterangan;

	// Constructors

	/** default constructor */
	public AbstractTblkategoridokumen() {
	}

	/** full constructor */
	public AbstractTblkategoridokumen(String kodKategoriDokumen,
			String kategoriDokumen, String keterangan) {
		this.kodKategoriDokumen = kodKategoriDokumen;
		this.kategoriDokumen = kategoriDokumen;
		this.keterangan = keterangan;
	}

	// Property accessors

	public Long getIdKategoriDokumen() {
		return this.idKategoriDokumen;
	}

	public void setIdKategoriDokumen(Long idKategoriDokumen) {
		this.idKategoriDokumen = idKategoriDokumen;
	}

	public String getKodKategoriDokumen() {
		return this.kodKategoriDokumen;
	}

	public void setKodKategoriDokumen(String kodKategoriDokumen) {
		this.kodKategoriDokumen = kodKategoriDokumen;
	}

	public String getKategoriDokumen() {
		return this.kategoriDokumen;
	}

	public void setKategoriDokumen(String kategoriDokumen) {
		this.kategoriDokumen = kategoriDokumen;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}