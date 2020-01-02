package ekptg.model.entities;

/**
 * AbstractTbljenisunitorganisasi entity provides the base persistence
 * definition of the Tbljenisunitorganisasi entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTbljenisunitorganisasi implements
		java.io.Serializable {

	// Fields

	private Long idJenisUnitOrganisasi;
	private String kodJenisUnitOrganisasi;
	private String jenisUnitOrganisasi;

	// Constructors

	/** default constructor */
	public AbstractTbljenisunitorganisasi() {
	}

	/** full constructor */
	public AbstractTbljenisunitorganisasi(String kodJenisUnitOrganisasi,
			String jenisUnitOrganisasi) {
		this.kodJenisUnitOrganisasi = kodJenisUnitOrganisasi;
		this.jenisUnitOrganisasi = jenisUnitOrganisasi;
	}

	// Property accessors

	public Long getIdJenisUnitOrganisasi() {
		return this.idJenisUnitOrganisasi;
	}

	public void setIdJenisUnitOrganisasi(Long idJenisUnitOrganisasi) {
		this.idJenisUnitOrganisasi = idJenisUnitOrganisasi;
	}

	public String getKodJenisUnitOrganisasi() {
		return this.kodJenisUnitOrganisasi;
	}

	public void setKodJenisUnitOrganisasi(String kodJenisUnitOrganisasi) {
		this.kodJenisUnitOrganisasi = kodJenisUnitOrganisasi;
	}

	public String getJenisUnitOrganisasi() {
		return this.jenisUnitOrganisasi;
	}

	public void setJenisUnitOrganisasi(String jenisUnitOrganisasi) {
		this.jenisUnitOrganisasi = jenisUnitOrganisasi;
	}

}