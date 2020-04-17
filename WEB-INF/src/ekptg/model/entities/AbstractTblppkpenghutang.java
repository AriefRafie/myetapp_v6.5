package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkpenghutang entity provides the base persistence definition of
 * the Tblppkpenghutang entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkpenghutang implements java.io.Serializable {

	// Fields

	private Long idPenghutang;
	//private Tblppksimati tblppksimati;
        private Long idSimati;
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
	private String butiranHutang;
	private Long idNegeri;
	private String jenisPenghutang;
	private String jenisAgama;
	private String jenisWarga;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
        private String jenisKp;

	// Constructors

	/** default constructor */
	public AbstractTblppkpenghutang() {
	}

	/** minimal constructor */
	public AbstractTblppkpenghutang(Long idPenghutang) {
		this.idPenghutang = idPenghutang;
	//	this.tblppksimati = tblppksimati;
	}

	/** full constructor */
	public AbstractTblppkpenghutang(Long idPenghutang,
			Long idSimati, String namaPenghutang, String noKpBaru,
			String noKpLama, String noKpLain, String alamat1, String alamat2,
			String alamat3, String bandar, String poskod, String noAkaun,
			String namaBank, Double jumlahHutang, String butiranHutang,
			Long idNegeri, String jenisPenghutang, String jenisAgama,
			String jenisWarga, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, String jenisKp) {
		this.idPenghutang = idPenghutang;
		//this.tblppksimati = tblppksimati;
                this.idSimati = idSimati;
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
		this.butiranHutang = butiranHutang;
		this.idNegeri = idNegeri;
		this.jenisPenghutang = jenisPenghutang;
		this.jenisAgama = jenisAgama;
		this.jenisWarga = jenisWarga;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
                this.jenisKp = jenisKp;
	}

	// Property accessors

	public Long getIdPenghutang() {
		return this.idPenghutang;
	}

	public void setIdPenghutang(Long idPenghutang) {
		this.idPenghutang = idPenghutang;
	}

/*	public Tblppksimati getTblppksimati() {
		return this.tblppksimati;
	}

	public void setTblppksimati(Tblppksimati tblppksimati) {
		this.tblppksimati = tblppksimati;
	}
*/
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

	public String getButiranHutang() {
		return this.butiranHutang;
	}

	public void setButiranHutang(String butiranHutang) {
		this.butiranHutang = butiranHutang;
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

    public void setIdSimati(Long idSimati) {
        this.idSimati = idSimati;
    }

    public Long getIdSimati() {
        return idSimati;
    }

    public void setJenisKp(String jenisKp) {
        this.jenisKp = jenisKp;
    }

    public String getJenisKp() {
        return jenisKp;
    }
}
