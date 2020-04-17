package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkob entity provides the base persistence definition of the
 * Tblppkob entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkob implements java.io.Serializable {

	// Fields
	private Long idOb;
	private Long idSimati;
	private String namaOb;
	private String noKpBaru;
	private String noKpLama;
	private String jenisKp;
	private String noKpLain;
	private String noSuratBeranak;
	private Date tarikhLahir;
	private String jantina;
	private Long umur;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String bandar;
	private String poskod;
	private String noHp;
	private String noTel;
	private String catatan;
	private String statusHidup;
	private Long idTarafkptg;
	private Long idNegeri;
	private Long idSaudara;
	private Long idJenispb;
	private String jenisAgama;
	private String jenisWarga;
	private Long idBank;
	private String noAkaun;
	private Date tarikhMati;
	private String waktuKematian;
	private String jenisWaktuKematian;
	private String statusOb;
	private Double nilaiHutang;
	private Long baFaraid;
	private Long bbFaraid;
	private Long lapis;
	private String butiranHutang;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
       
	// Constructors

	/** default constructor */
	public AbstractTblppkob() {
	}

	/** minimal constructor */
	public AbstractTblppkob(Long idOb, Long idSimati) {
		this.idOb = idOb;
		this.idSimati = idSimati;
	}

	/** full constructor */
	public AbstractTblppkob(Long idOb, Long idSimati, String namaOb,
			String noKpBaru, String noKpLama, String jenisKp, String noKpLain,
			String noSuratBeranak, Date tarikhLahir, String jantina, Long umur,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, String noHp, String noTel, String catatan,
			String statusHidup, Long idTarafkptg, Long idNegeri,
			Long idSaudara, Long idJenispb, String jenisAgama,
			String jenisWarga, Long idBank, String noAkaun, Date tarikhMati,
			String waktuKematian, String jenisWaktuKematian, String statusOb,
			Double nilaiHutang, Long baFaraid, Long bbFaraid, Long lapis,
			String butiranHutang, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idOb = idOb;
		this.idSimati = idSimati;
		this.namaOb = namaOb;
		this.noKpBaru = noKpBaru;
		this.noKpLama = noKpLama;
		this.jenisKp = jenisKp;
		this.noKpLain = noKpLain;
		this.noSuratBeranak = noSuratBeranak;
		this.tarikhLahir = tarikhLahir;
		this.jantina = jantina;
		this.umur = umur;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.bandar = bandar;
		this.poskod = poskod;
		this.noHp = noHp;
		this.noTel = noTel;
		this.catatan = catatan;
		this.statusHidup = statusHidup;
		this.idTarafkptg = idTarafkptg;
		this.idNegeri = idNegeri;
		this.idSaudara = idSaudara;
		this.idJenispb = idJenispb;
		this.jenisAgama = jenisAgama;
		this.jenisWarga = jenisWarga;
		this.idBank = idBank;
		this.noAkaun = noAkaun;
		this.tarikhMati = tarikhMati;
		this.waktuKematian = waktuKematian;
		this.jenisWaktuKematian = jenisWaktuKematian;
		this.statusOb = statusOb;
		this.nilaiHutang = nilaiHutang;
		this.baFaraid = baFaraid;
		this.bbFaraid = bbFaraid;
		this.lapis = lapis;
		this.butiranHutang = butiranHutang;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
             
	}

	// Property accessors

	public Long getIdOb() {
		return this.idOb;
	}

	public void setIdOb(Long idOb) {
		this.idOb = idOb;
	}

	public Long getIdSimati() {
		return this.idSimati;
	}

	public void setIdSimati(Long idSimati) {
		this.idSimati = idSimati;
	}

	public String getNamaOb() {
		return this.namaOb;
	}

	public void setNamaOb(String namaOb) {
		this.namaOb = namaOb;
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

	public String getNoSuratBeranak() {
		return this.noSuratBeranak;
	}

	public void setNoSuratBeranak(String noSuratBeranak) {
		this.noSuratBeranak = noSuratBeranak;
	}

	public Date getTarikhLahir() {
		return this.tarikhLahir;
	}

	public void setTarikhLahir(Date tarikhLahir) {
		this.tarikhLahir = tarikhLahir;
	}

	public String getJantina() {
		return this.jantina;
	}

	public void setJantina(String jantina) {
		this.jantina = jantina;
	}

	public Long getUmur() {
		return this.umur;
	}

	public void setUmur(Long umur) {
		this.umur = umur;
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

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getStatusHidup() {
		return this.statusHidup;
	}

	public void setStatusHidup(String statusHidup) {
		this.statusHidup = statusHidup;
	}

	public Long getIdTarafkptg() {
		return this.idTarafkptg;
	}

	public void setIdTarafkptg(Long idTarafkptg) {
		this.idTarafkptg = idTarafkptg;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdSaudara() {
		return this.idSaudara;
	}

	public void setIdSaudara(Long idSaudara) {
		this.idSaudara = idSaudara;
	}

	public Long getIdJenispb() {
		return this.idJenispb;
	}

	public void setIdJenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
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

	public Long getIdBank() {
		return this.idBank;
	}

	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	public String getNoAkaun() {
		return this.noAkaun;
	}

	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
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

	public String getStatusOb() {
		return this.statusOb;
	}

	public void setStatusOb(String statusOb) {
		this.statusOb = statusOb;
	}

	public Double getNilaiHutang() {
		return this.nilaiHutang;
	}

	public void setNilaiHutang(Double nilaiHutang) {
		this.nilaiHutang = nilaiHutang;
	}

	public Long getBaFaraid() {
		return this.baFaraid;
	}

	public void setBaFaraid(Long baFaraid) {
		this.baFaraid = baFaraid;
	}

	public Long getBbFaraid() {
		return this.bbFaraid;
	}

	public void setBbFaraid(Long bbFaraid) {
		this.bbFaraid = bbFaraid;
	}

	public Long getLapis() {
		return this.lapis;
	}

	public void setLapis(Long lapis) {
		this.lapis = lapis;
	}

	public String getButiranHutang() {
		return this.butiranHutang;
	}

	public void setButiranHutang(String butiranHutang) {
		this.butiranHutang = butiranHutang;
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
