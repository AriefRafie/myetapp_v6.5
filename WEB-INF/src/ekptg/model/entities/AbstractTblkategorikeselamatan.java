package ekptg.model.entities;

/**
 * AbstractTblkategorikeselamatan entity provides the base persistence
 * definition of the Tblkategorikeselamatan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblkategorikeselamatan implements
		java.io.Serializable {

	// Fields

	private Long idKategoriKeselamatan;
	private String kodKategoriKeselamatan;
	private String kategoryKeselamatan;
	private String keterangan;

	// Constructors

	/** default constructor */
	public AbstractTblkategorikeselamatan() {
	}

	/** full constructor */
	public AbstractTblkategorikeselamatan(String kodKategoriKeselamatan,
			String kategoryKeselamatan, String keterangan) {
		this.kodKategoriKeselamatan = kodKategoriKeselamatan;
		this.kategoryKeselamatan = kategoryKeselamatan;
		this.keterangan = keterangan;
	}

	// Property accessors

	public Long getIdKategoriKeselamatan() {
		return this.idKategoriKeselamatan;
	}

	public void setIdKategoriKeselamatan(Long idKategoriKeselamatan) {
		this.idKategoriKeselamatan = idKategoriKeselamatan;
	}

	public String getKodKategoriKeselamatan() {
		return this.kodKategoriKeselamatan;
	}

	public void setKodKategoriKeselamatan(String kodKategoriKeselamatan) {
		this.kodKategoriKeselamatan = kodKategoriKeselamatan;
	}

	public String getKategoryKeselamatan() {
		return this.kategoryKeselamatan;
	}

	public void setKategoryKeselamatan(String kategoryKeselamatan) {
		this.kategoryKeselamatan = kategoryKeselamatan;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}