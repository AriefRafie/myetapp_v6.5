package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkborangapemohon entity provides the base persistence definition
 * of the Tblppkborangapemohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkborangapemohon implements
		java.io.Serializable {

	// Fields

	private Long idBorangapemohon;
	private Tblppkboranga tblppkboranga;
	private Tblrujppktarafkptg tblrujppktarafkptg;
	private Tblrujppksaudara tblrujppksaudara;
	private String namaPemohon;
	private String noKpBaru;
	private String noKpLama;
	private String jenisKp;
	private String noKpLain;
	private String jantina;
	private String jenisAgama;
	private String jenisWarga;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String bandar;
	private String poskod;
	private String noHp;
	private String noTel;
	private String emel;
	private String noFax;
	private String catatan;
	private Long idNegeri;
	private String statusPeguam;
	private Long umur;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangapeguams = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkborangapemohon() {
	}

	/** minimal constructor */
	public AbstractTblppkborangapemohon(Long idBorangapemohon,
			Tblppkboranga tblppkboranga, Tblrujppktarafkptg tblrujppktarafkptg,
			Tblrujppksaudara tblrujppksaudara) {
		this.idBorangapemohon = idBorangapemohon;
		this.tblppkboranga = tblppkboranga;
		this.tblrujppktarafkptg = tblrujppktarafkptg;
		this.tblrujppksaudara = tblrujppksaudara;
	}

	/** full constructor */
	public AbstractTblppkborangapemohon(Long idBorangapemohon,
			Tblppkboranga tblppkboranga, Tblrujppktarafkptg tblrujppktarafkptg,
			Tblrujppksaudara tblrujppksaudara, String namaPemohon,
			String noKpBaru, String noKpLama, String jenisKp, String noKpLain,
			String jantina, String jenisAgama, String jenisWarga,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, String noHp, String noTel, String emel,
			String noFax, String catatan, Long idNegeri, String statusPeguam,
			Long umur, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkborangapeguams) {
		this.idBorangapemohon = idBorangapemohon;
		this.tblppkboranga = tblppkboranga;
		this.tblrujppktarafkptg = tblrujppktarafkptg;
		this.tblrujppksaudara = tblrujppksaudara;
		this.namaPemohon = namaPemohon;
		this.noKpBaru = noKpBaru;
		this.noKpLama = noKpLama;
		this.jenisKp = jenisKp;
		this.noKpLain = noKpLain;
		this.jantina = jantina;
		this.jenisAgama = jenisAgama;
		this.jenisWarga = jenisWarga;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.bandar = bandar;
		this.poskod = poskod;
		this.noHp = noHp;
		this.noTel = noTel;
		this.emel = emel;
		this.noFax = noFax;
		this.catatan = catatan;
		this.idNegeri = idNegeri;
		this.statusPeguam = statusPeguam;
		this.umur = umur;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangapeguams = tblppkborangapeguams;
	}

	// Property accessors

	public Long getIdBorangapemohon() {
		return this.idBorangapemohon;
	}

	public void setIdBorangapemohon(Long idBorangapemohon) {
		this.idBorangapemohon = idBorangapemohon;
	}

	public Tblppkboranga getTblppkboranga() {
		return this.tblppkboranga;
	}

	public void setTblppkboranga(Tblppkboranga tblppkboranga) {
		this.tblppkboranga = tblppkboranga;
	}

	public Tblrujppktarafkptg getTblrujppktarafkptg() {
		return this.tblrujppktarafkptg;
	}

	public void setTblrujppktarafkptg(Tblrujppktarafkptg tblrujppktarafkptg) {
		this.tblrujppktarafkptg = tblrujppktarafkptg;
	}

	public Tblrujppksaudara getTblrujppksaudara() {
		return this.tblrujppksaudara;
	}

	public void setTblrujppksaudara(Tblrujppksaudara tblrujppksaudara) {
		this.tblrujppksaudara = tblrujppksaudara;
	}

	public String getNamaPemohon() {
		return this.namaPemohon;
	}

	public void setNamaPemohon(String namaPemohon) {
		this.namaPemohon = namaPemohon;
	}

	public String getNoKpBaru() {
		return this.noKpBaru;
	}

	public void setNoKpBaru(String noKpBaru) {
		this.noKpBaru = noKpBaru;
	}

	public String getNoKpLama() {
		return this.noKpLama;
	}

	public void setNoKpLama(String noKpLama) {
		this.noKpLama = noKpLama;
	}

	public String getJenisKp() {
		return this.jenisKp;
	}

	public void setJenisKp(String jenisKp) {
		this.jenisKp = jenisKp;
	}

	public String getNoKpLain() {
		return this.noKpLain;
	}

	public void setNoKpLain(String noKpLain) {
		this.noKpLain = noKpLain;
	}

	public String getJantina() {
		return this.jantina;
	}

	public void setJantina(String jantina) {
		this.jantina = jantina;
	}

	public String getJenisAgama() {
		return this.jenisAgama;
	}

	public void setJenisAgama(String jenisAgama) {
		this.jenisAgama = jenisAgama;
	}

	public String getJenisWarga() {
		return this.jenisWarga;
	}

	public void setJenisWarga(String jenisWarga) {
		this.jenisWarga = jenisWarga;
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

	public String getBandar() {
		return this.bandar;
	}

	public void setBandar(String bandar) {
		this.bandar = bandar;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public String getNoHp() {
		return this.noHp;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public String getNoTel() {
		return this.noTel;
	}

	public void setNoTel(String noTel) {
		this.noTel = noTel;
	}

	public String getEmel() {
		return this.emel;
	}

	public void setEmel(String emel) {
		this.emel = emel;
	}

	public String getNoFax() {
		return this.noFax;
	}

	public void setNoFax(String noFax) {
		this.noFax = noFax;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getStatusPeguam() {
		return this.statusPeguam;
	}

	public void setStatusPeguam(String statusPeguam) {
		this.statusPeguam = statusPeguam;
	}

	public Long getUmur() {
		return this.umur;
	}

	public void setUmur(Long umur) {
		this.umur = umur;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Set getTblppkborangapeguams() {
		return this.tblppkborangapeguams;
	}

	public void setTblppkborangapeguams(Set tblppkborangapeguams) {
		this.tblppkborangapeguams = tblppkborangapeguams;
	}

}