package ekptg.model.entities;

/**
 * AbstractTblarus entity provides the base persistence definition of the
 * Tblarus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblarus implements java.io.Serializable {

	// Fields

	private Long idArus;
	private String kodLangkah;
	private String kodArus;
	private String namaArus;
	private String keterangan;

	// Constructors

	/** default constructor */
	public AbstractTblarus() {
	}

	/** full constructor */
	public AbstractTblarus(String kodLangkah, String kodArus, String namaArus,
			String keterangan) {
		this.kodLangkah = kodLangkah;
		this.kodArus = kodArus;
		this.namaArus = namaArus;
		this.keterangan = keterangan;
	}

	// Property accessors

	public Long getIdArus() {
		return this.idArus;
	}

	public void setIdArus(Long idArus) {
		this.idArus = idArus;
	}

	public String getKodLangkah() {
		return this.kodLangkah;
	}

	public void setKodLangkah(String kodLangkah) {
		this.kodLangkah = kodLangkah;
	}

	public String getKodArus() {
		return this.kodArus;
	}

	public void setKodArus(String kodArus) {
		this.kodArus = kodArus;
	}

	public String getNamaArus() {
		return this.namaArus;
	}

	public void setNamaArus(String namaArus) {
		this.namaArus = namaArus;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}