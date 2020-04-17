package ekptg.model.entities;

/**
 * AbstractTbllangkah entity provides the base persistence definition of the
 * Tbllangkah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTbllangkah implements java.io.Serializable {

	// Fields

	private Long idLangkah;
	private String kodProses;
	private String kodLangkah;
	private String namaLangkah;
	private String keterangan;
	private Long bmMasa;
	private Long bmKos;
	private String alamatLangkah;

	// Constructors

	/** default constructor */
	public AbstractTbllangkah() {
	}

	/** full constructor */
	public AbstractTbllangkah(String kodProses, String kodLangkah,
			String namaLangkah, String keterangan, Long bmMasa, Long bmKos,
			String alamatLangkah) {
		this.kodProses = kodProses;
		this.kodLangkah = kodLangkah;
		this.namaLangkah = namaLangkah;
		this.keterangan = keterangan;
		this.bmMasa = bmMasa;
		this.bmKos = bmKos;
		this.alamatLangkah = alamatLangkah;
	}

	// Property accessors

	public Long getIdLangkah() {
		return this.idLangkah;
	}

	public void setIdLangkah(Long idLangkah) {
		this.idLangkah = idLangkah;
	}

	public String getKodProses() {
		return this.kodProses;
	}

	public void setKodProses(String kodProses) {
		this.kodProses = kodProses;
	}

	public String getKodLangkah() {
		return this.kodLangkah;
	}

	public void setKodLangkah(String kodLangkah) {
		this.kodLangkah = kodLangkah;
	}

	public String getNamaLangkah() {
		return this.namaLangkah;
	}

	public void setNamaLangkah(String namaLangkah) {
		this.namaLangkah = namaLangkah;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Long getBmMasa() {
		return this.bmMasa;
	}

	public void setBmMasa(Long bmMasa) {
		this.bmMasa = bmMasa;
	}

	public Long getBmKos() {
		return this.bmKos;
	}

	public void setBmKos(Long bmKos) {
		this.bmKos = bmKos;
	}

	public String getAlamatLangkah() {
		return this.alamatLangkah;
	}

	public void setAlamatLangkah(String alamatLangkah) {
		this.alamatLangkah = alamatLangkah;
	}

}