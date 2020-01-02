package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkborangppenghutang entity provides the base persistence
 * definition of the Tblppkborangppenghutang entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkborangppenghutang implements
		java.io.Serializable {

	// Fields

	private Long idBorangppenghutang;
	private Tblppkborangpsimati tblppkborangpsimati;
	private String namaPenghutang;
	private String noKpBaru;
	private String noKpLama;
	private String noKpLain;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String bandar;
	private String poskod;
	private String noAkaun;
	private String namaBank;
	private Double jumlahHutang;
	private String catatan;
	private Long idNegeri;
	private String jenisPenghutang;
	private String jenisAgama;
	private String jenisWarga;
	private String jenisKp;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkborangppenghutang() {
	}

	/** minimal constructor */
	public AbstractTblppkborangppenghutang(Long idBorangppenghutang,
			Tblppkborangpsimati tblppkborangpsimati) {
		this.idBorangppenghutang = idBorangppenghutang;
		this.tblppkborangpsimati = tblppkborangpsimati;
	}

	/** full constructor */
	public AbstractTblppkborangppenghutang(Long idBorangppenghutang,
			Tblppkborangpsimati tblppkborangpsimati, String namaPenghutang,
			String noKpBaru, String noKpLama, String noKpLain, String alamat1,
			String alamat2, String alamat3, String bandar, String poskod,
			String noAkaun, String namaBank, Double jumlahHutang,
			String catatan, Long idNegeri, String jenisPenghutang,
			String jenisAgama, String jenisWarga, String jenisKp, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idBorangppenghutang = idBorangppenghutang;
		this.tblppkborangpsimati = tblppkborangpsimati;
		this.namaPenghutang = namaPenghutang;
		this.noKpBaru = noKpBaru;
		this.noKpLama = noKpLama;
		this.noKpLain = noKpLain;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.bandar = bandar;
		this.poskod = poskod;
		this.noAkaun = noAkaun;
		this.namaBank = namaBank;
		this.jumlahHutang = jumlahHutang;
		this.catatan = catatan;
		this.idNegeri = idNegeri;
		this.jenisPenghutang = jenisPenghutang;
		this.jenisAgama = jenisAgama;
		this.jenisWarga = jenisWarga;
		this.jenisKp = jenisKp;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBorangppenghutang() {
		return this.idBorangppenghutang;
	}

	public void setIdBorangppenghutang(Long idBorangppenghutang) {
		this.idBorangppenghutang = idBorangppenghutang;
	}

	public Tblppkborangpsimati getTblppkborangpsimati() {
		return this.tblppkborangpsimati;
	}

	public void setTblppkborangpsimati(Tblppkborangpsimati tblppkborangpsimati) {
		this.tblppkborangpsimati = tblppkborangpsimati;
	}

	public String getNamaPenghutang() {
		return this.namaPenghutang;
	}

	public void setNamaPenghutang(String namaPenghutang) {
		this.namaPenghutang = namaPenghutang;
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

	public String getNoKpLain() {
		return this.noKpLain;
	}

	public void setNoKpLain(String noKpLain) {
		this.noKpLain = noKpLain;
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

	public String getNoAkaun() {
		return this.noAkaun;
	}

	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
	}

	public String getNamaBank() {
		return this.namaBank;
	}

	public void setNamaBank(String namaBank) {
		this.namaBank = namaBank;
	}

	public Double getJumlahHutang() {
		return this.jumlahHutang;
	}

	public void setJumlahHutang(Double jumlahHutang) {
		this.jumlahHutang = jumlahHutang;
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

	public String getJenisPenghutang() {
		return this.jenisPenghutang;
	}

	public void setJenisPenghutang(String jenisPenghutang) {
		this.jenisPenghutang = jenisPenghutang;
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

	public String getJenisKp() {
		return this.jenisKp;
	}

	public void setJenisKp(String jenisKp) {
		this.jenisKp = jenisKp;
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

}