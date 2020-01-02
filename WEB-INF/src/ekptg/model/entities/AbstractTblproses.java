package ekptg.model.entities;

/**
 * AbstractTblproses entity provides the base persistence definition of the
 * Tblproses entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblproses implements java.io.Serializable {

	// Fields

	private Long idProses;
	private String kodProses;
	private String namaProses;
	private String keterangan;
	private Long bmTenagakerja;

	// Constructors

	/** default constructor */
	public AbstractTblproses() {
	}

	/** full constructor */
	public AbstractTblproses(String kodProses, String namaProses,
			String keterangan, Long bmTenagakerja) {
		this.kodProses = kodProses;
		this.namaProses = namaProses;
		this.keterangan = keterangan;
		this.bmTenagakerja = bmTenagakerja;
	}

	// Property accessors

	public Long getIdProses() {
		return this.idProses;
	}

	public void setIdProses(Long idProses) {
		this.idProses = idProses;
	}

	public String getKodProses() {
		return this.kodProses;
	}

	public void setKodProses(String kodProses) {
		this.kodProses = kodProses;
	}

	public String getNamaProses() {
		return this.namaProses;
	}

	public void setNamaProses(String namaProses) {
		this.namaProses = namaProses;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Long getBmTenagakerja() {
		return this.bmTenagakerja;
	}

	public void setBmTenagakerja(Long bmTenagakerja) {
		this.bmTenagakerja = bmTenagakerja;
	}

}