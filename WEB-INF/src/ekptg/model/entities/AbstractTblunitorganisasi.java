package ekptg.model.entities;

/**
 * AbstractTblunitorganisasi entity provides the base persistence definition of
 * the Tblunitorganisasi entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblunitorganisasi implements java.io.Serializable {

	// Fields

	private Long idUnitOrganisasi;
	private String kodUnitOrganisasi;
	private String namaUnitOrganisasi;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String bandar;
	private String kodMpb;
	private String kodNegeri;
	private String kodNegara;
	private String noTelefon;
	private String noFak;
	private String email;
	private String kodStatus;

	// Constructors

	/** default constructor */
	public AbstractTblunitorganisasi() {
	}

	/** full constructor */
	public AbstractTblunitorganisasi(String kodUnitOrganisasi,
			String namaUnitOrganisasi, String alamat1, String alamat2,
			String alamat3, String poskod, String bandar, String kodMpb,
			String kodNegeri, String kodNegara, String noTelefon, String noFak,
			String email, String kodStatus) {
		this.kodUnitOrganisasi = kodUnitOrganisasi;
		this.namaUnitOrganisasi = namaUnitOrganisasi;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.bandar = bandar;
		this.kodMpb = kodMpb;
		this.kodNegeri = kodNegeri;
		this.kodNegara = kodNegara;
		this.noTelefon = noTelefon;
		this.noFak = noFak;
		this.email = email;
		this.kodStatus = kodStatus;
	}

	// Property accessors

	public Long getIdUnitOrganisasi() {
		return this.idUnitOrganisasi;
	}

	public void setIdUnitOrganisasi(Long idUnitOrganisasi) {
		this.idUnitOrganisasi = idUnitOrganisasi;
	}

	public String getKodUnitOrganisasi() {
		return this.kodUnitOrganisasi;
	}

	public void setKodUnitOrganisasi(String kodUnitOrganisasi) {
		this.kodUnitOrganisasi = kodUnitOrganisasi;
	}

	public String getNamaUnitOrganisasi() {
		return this.namaUnitOrganisasi;
	}

	public void setNamaUnitOrganisasi(String namaUnitOrganisasi) {
		this.namaUnitOrganisasi = namaUnitOrganisasi;
	}

	public String getAlamat1() {
		return this.alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return this.alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return this.alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public String getBandar() {
		return this.bandar;
	}

	public void setBandar(String bandar) {
		this.bandar = bandar;
	}

	public String getKodMpb() {
		return this.kodMpb;
	}

	public void setKodMpb(String kodMpb) {
		this.kodMpb = kodMpb;
	}

	public String getKodNegeri() {
		return this.kodNegeri;
	}

	public void setKodNegeri(String kodNegeri) {
		this.kodNegeri = kodNegeri;
	}

	public String getKodNegara() {
		return this.kodNegara;
	}

	public void setKodNegara(String kodNegara) {
		this.kodNegara = kodNegara;
	}

	public String getNoTelefon() {
		return this.noTelefon;
	}

	public void setNoTelefon(String noTelefon) {
		this.noTelefon = noTelefon;
	}

	public String getNoFak() {
		return this.noFak;
	}

	public void setNoFak(String noFak) {
		this.noFak = noFak;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKodStatus() {
		return this.kodStatus;
	}

	public void setKodStatus(String kodStatus) {
		this.kodStatus = kodStatus;
	}

}