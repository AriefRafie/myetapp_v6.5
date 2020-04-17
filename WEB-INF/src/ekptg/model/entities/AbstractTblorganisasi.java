package ekptg.model.entities;

/**
 * AbstractTblorganisasi entity provides the base persistence definition of the
 * Tblorganisasi entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblorganisasi implements java.io.Serializable {

	// Fields

	private Long idOrganisasi;
	private String kodOrganisasi;
	private String namaOrganisasi;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String bandar;
	private String kodMpb;
	private String kodNegeri;
	private String kodNegara;
	private String kodStatus;
	private String noTelefon;
	private String noFak;
	private String alamatWeb;
	private String email;

	// Constructors

	/** default constructor */
	public AbstractTblorganisasi() {
	}

	/** full constructor */
	public AbstractTblorganisasi(String kodOrganisasi, String namaOrganisasi,
			String alamat1, String alamat2, String alamat3, String poskod,
			String bandar, String kodMpb, String kodNegeri, String kodNegara,
			String kodStatus, String noTelefon, String noFak, String alamatWeb,
			String email) {
		this.kodOrganisasi = kodOrganisasi;
		this.namaOrganisasi = namaOrganisasi;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.bandar = bandar;
		this.kodMpb = kodMpb;
		this.kodNegeri = kodNegeri;
		this.kodNegara = kodNegara;
		this.kodStatus = kodStatus;
		this.noTelefon = noTelefon;
		this.noFak = noFak;
		this.alamatWeb = alamatWeb;
		this.email = email;
	}

	// Property accessors

	public Long getIdOrganisasi() {
		return this.idOrganisasi;
	}

	public void setIdOrganisasi(Long idOrganisasi) {
		this.idOrganisasi = idOrganisasi;
	}

	public String getKodOrganisasi() {
		return this.kodOrganisasi;
	}

	public void setKodOrganisasi(String kodOrganisasi) {
		this.kodOrganisasi = kodOrganisasi;
	}

	public String getNamaOrganisasi() {
		return this.namaOrganisasi;
	}

	public void setNamaOrganisasi(String namaOrganisasi) {
		this.namaOrganisasi = namaOrganisasi;
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

	public String getKodStatus() {
		return this.kodStatus;
	}

	public void setKodStatus(String kodStatus) {
		this.kodStatus = kodStatus;
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

	public String getAlamatWeb() {
		return this.alamatWeb;
	}

	public void setAlamatWeb(String alamatWeb) {
		this.alamatWeb = alamatWeb;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}