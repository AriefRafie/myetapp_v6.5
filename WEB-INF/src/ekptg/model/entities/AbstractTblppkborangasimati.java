package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkborangasimati entity provides the base persistence definition
 * of the Tblppkborangasimati entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkborangasimati implements
		java.io.Serializable {

	// Fields

	private Long idBorangasimati;
	private Tblppkboranga tblppkboranga;
	private Tblrujppkbuktimati tblrujppkbuktimati;
	private String namaSimati;
	private String namaLain;
	private String noKpBaru;
	private String noKpLama;
	private String jenisKp;
	private String n0KpLain;
	private Long umur;
	private String jantina;
	private String noSijilMati;
	private String tempatMati;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String bandar;
	private String poskod;
	private Date tarikhMati;
	private String waktuKematian;
	private String jenisWaktuKematian;
	private String sebabMati;
	private String catatan;
	private Long idNegeri;
	private String jenisAgama;
	private String jenisWarga;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangahtas = new HashSet(0);
	private Set tblppkborangaobs = new HashSet(0);
	private Set tblppkborangapenghutangs = new HashSet(0);
	private Set tblppkborangahas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkborangasimati() {
	}

	/** minimal constructor */
	public AbstractTblppkborangasimati(Long idBorangasimati,
			Tblppkboranga tblppkboranga, String namaSimati) {
		this.idBorangasimati = idBorangasimati;
		this.tblppkboranga = tblppkboranga;
		this.namaSimati = namaSimati;
	}

	/** full constructor */
	public AbstractTblppkborangasimati(Long idBorangasimati,
			Tblppkboranga tblppkboranga, Tblrujppkbuktimati tblrujppkbuktimati,
			String namaSimati, String namaLain, String noKpBaru,
			String noKpLama, String jenisKp, String n0KpLain, Long umur,
			String jantina, String noSijilMati, String tempatMati,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, Date tarikhMati, String waktuKematian,
			String jenisWaktuKematian, String sebabMati, String catatan,
			Long idNegeri, String jenisAgama, String jenisWarga, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkborangahtas, Set tblppkborangaobs,
			Set tblppkborangapenghutangs, Set tblppkborangahas) {
		this.idBorangasimati = idBorangasimati;
		this.tblppkboranga = tblppkboranga;
		this.tblrujppkbuktimati = tblrujppkbuktimati;
		this.namaSimati = namaSimati;
		this.namaLain = namaLain;
		this.noKpBaru = noKpBaru;
		this.noKpLama = noKpLama;
		this.jenisKp = jenisKp;
		this.n0KpLain = n0KpLain;
		this.umur = umur;
		this.jantina = jantina;
		this.noSijilMati = noSijilMati;
		this.tempatMati = tempatMati;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.bandar = bandar;
		this.poskod = poskod;
		this.tarikhMati = tarikhMati;
		this.waktuKematian = waktuKematian;
		this.jenisWaktuKematian = jenisWaktuKematian;
		this.sebabMati = sebabMati;
		this.catatan = catatan;
		this.idNegeri = idNegeri;
		this.jenisAgama = jenisAgama;
		this.jenisWarga = jenisWarga;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangahtas = tblppkborangahtas;
		this.tblppkborangaobs = tblppkborangaobs;
		this.tblppkborangapenghutangs = tblppkborangapenghutangs;
		this.tblppkborangahas = tblppkborangahas;
	}

	// Property accessors

	public Long getIdBorangasimati() {
		return this.idBorangasimati;
	}

	public void setIdBorangasimati(Long idBorangasimati) {
		this.idBorangasimati = idBorangasimati;
	}

	public Tblppkboranga getTblppkboranga() {
		return this.tblppkboranga;
	}

	public void setTblppkboranga(Tblppkboranga tblppkboranga) {
		this.tblppkboranga = tblppkboranga;
	}

	public Tblrujppkbuktimati getTblrujppkbuktimati() {
		return this.tblrujppkbuktimati;
	}

	public void setTblrujppkbuktimati(Tblrujppkbuktimati tblrujppkbuktimati) {
		this.tblrujppkbuktimati = tblrujppkbuktimati;
	}

	public String getNamaSimati() {
		return this.namaSimati;
	}

	public void setNamaSimati(String namaSimati) {
		this.namaSimati = namaSimati;
	}

	public String getNamaLain() {
		return this.namaLain;
	}

	public void setNamaLain(String namaLain) {
		this.namaLain = namaLain;
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

	public String getN0KpLain() {
		return this.n0KpLain;
	}

	public void setN0KpLain(String n0KpLain) {
		this.n0KpLain = n0KpLain;
	}

	public Long getUmur() {
		return this.umur;
	}

	public void setUmur(Long umur) {
		this.umur = umur;
	}

	public String getJantina() {
		return this.jantina;
	}

	public void setJantina(String jantina) {
		this.jantina = jantina;
	}

	public String getNoSijilMati() {
		return this.noSijilMati;
	}

	public void setNoSijilMati(String noSijilMati) {
		this.noSijilMati = noSijilMati;
	}

	public String getTempatMati() {
		return this.tempatMati;
	}

	public void setTempatMati(String tempatMati) {
		this.tempatMati = tempatMati;
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

	public Date getTarikhMati() {
		return this.tarikhMati;
	}

	public void setTarikhMati(Date tarikhMati) {
		this.tarikhMati = tarikhMati;
	}

	public String getWaktuKematian() {
		return this.waktuKematian;
	}

	public void setWaktuKematian(String waktuKematian) {
		this.waktuKematian = waktuKematian;
	}

	public String getJenisWaktuKematian() {
		return this.jenisWaktuKematian;
	}

	public void setJenisWaktuKematian(String jenisWaktuKematian) {
		this.jenisWaktuKematian = jenisWaktuKematian;
	}

	public String getSebabMati() {
		return this.sebabMati;
	}

	public void setSebabMati(String sebabMati) {
		this.sebabMati = sebabMati;
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

	public Set getTblppkborangahtas() {
		return this.tblppkborangahtas;
	}

	public void setTblppkborangahtas(Set tblppkborangahtas) {
		this.tblppkborangahtas = tblppkborangahtas;
	}

	public Set getTblppkborangaobs() {
		return this.tblppkborangaobs;
	}

	public void setTblppkborangaobs(Set tblppkborangaobs) {
		this.tblppkborangaobs = tblppkborangaobs;
	}

	public Set getTblppkborangapenghutangs() {
		return this.tblppkborangapenghutangs;
	}

	public void setTblppkborangapenghutangs(Set tblppkborangapenghutangs) {
		this.tblppkborangapenghutangs = tblppkborangapenghutangs;
	}

	public Set getTblppkborangahas() {
		return this.tblppkborangahas;
	}

	public void setTblppkborangahas(Set tblppkborangahas) {
		this.tblppkborangahas = tblppkborangahas;
	}

}