package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptsiasatan entity provides the base persistence definition of the
 * Tblpptsiasatan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptsiasatan implements java.io.Serializable {

	// Fields

	private Long idSiasatan;
	private String noKes;
	private String noKesSebelum;
	private String noKesBerikut;
	private String noSiasatan;
	private Long statusSiasatan;
	private Date tarikhSiasatan;
	private String masaSiasatan;
	private String tempat;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private String alasanTangguh;
	private Double nilaianJpph;
	private Long idUnitluas;
	private String bantahanTuantnh;
	private String bantahanAgensi;
	private String bantahanLain;
	private Long tempohMilikTanah;
	private String caraMilik;
	private Double hargaBeli;
	private String jenisBangunan;
	private String jenisTanaman;
	private String flagPecahSempadan;
	private String flagTukarSyarat;
	private Date tarikhPecahSempadan;
	private Date tarikhTukarSyarat;
	private String statusSemasa;
	private String bebanan;
	private String keteranganTuanTanah;
	private String keteranganAgensi;
	private String keteranganJurunilai;
	private String tuntutanTuantnh;
	private String tuntutanPbBebanan;
	private String tuntutanPbTdkdaftar;
	private String tuntutanPbLain;
	private Long idBorange;
	private String perintah;
	private Date tarikhAkhirTampal;
	private String flagBantahan;
	private Long idPegawaiSiasatan;
	private Long idPenarikanbalik;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptsiasatan() {
	}

	/** full constructor */
	public AbstractTblpptsiasatan(String noKes, String noKesSebelum,
			String noKesBerikut, String noSiasatan, Long statusSiasatan,
			Date tarikhSiasatan, String masaSiasatan, String tempat,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, String alasanTangguh, Double nilaianJpph,
			Long idUnitluas, String bantahanTuantnh, String bantahanAgensi,
			String bantahanLain, Long tempohMilikTanah, String caraMilik,
			Double hargaBeli, String jenisBangunan, String jenisTanaman,
			String flagPecahSempadan, String flagTukarSyarat,
			Date tarikhPecahSempadan, Date tarikhTukarSyarat,
			String statusSemasa, String bebanan, String keteranganTuanTanah,
			String keteranganAgensi, String keteranganJurunilai,
			String tuntutanTuantnh, String tuntutanPbBebanan,
			String tuntutanPbTdkdaftar, String tuntutanPbLain, Long idBorange,
			String perintah, Date tarikhAkhirTampal, String flagBantahan,
			Long idPegawaiSiasatan, Long idPenarikanbalik, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.noKes = noKes;
		this.noKesSebelum = noKesSebelum;
		this.noKesBerikut = noKesBerikut;
		this.noSiasatan = noSiasatan;
		this.statusSiasatan = statusSiasatan;
		this.tarikhSiasatan = tarikhSiasatan;
		this.masaSiasatan = masaSiasatan;
		this.tempat = tempat;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.alasanTangguh = alasanTangguh;
		this.nilaianJpph = nilaianJpph;
		this.idUnitluas = idUnitluas;
		this.bantahanTuantnh = bantahanTuantnh;
		this.bantahanAgensi = bantahanAgensi;
		this.bantahanLain = bantahanLain;
		this.tempohMilikTanah = tempohMilikTanah;
		this.caraMilik = caraMilik;
		this.hargaBeli = hargaBeli;
		this.jenisBangunan = jenisBangunan;
		this.jenisTanaman = jenisTanaman;
		this.flagPecahSempadan = flagPecahSempadan;
		this.flagTukarSyarat = flagTukarSyarat;
		this.tarikhPecahSempadan = tarikhPecahSempadan;
		this.tarikhTukarSyarat = tarikhTukarSyarat;
		this.statusSemasa = statusSemasa;
		this.bebanan = bebanan;
		this.keteranganTuanTanah = keteranganTuanTanah;
		this.keteranganAgensi = keteranganAgensi;
		this.keteranganJurunilai = keteranganJurunilai;
		this.tuntutanTuantnh = tuntutanTuantnh;
		this.tuntutanPbBebanan = tuntutanPbBebanan;
		this.tuntutanPbTdkdaftar = tuntutanPbTdkdaftar;
		this.tuntutanPbLain = tuntutanPbLain;
		this.idBorange = idBorange;
		this.perintah = perintah;
		this.tarikhAkhirTampal = tarikhAkhirTampal;
		this.flagBantahan = flagBantahan;
		this.idPegawaiSiasatan = idPegawaiSiasatan;
		this.idPenarikanbalik = idPenarikanbalik;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdSiasatan() {
		return this.idSiasatan;
	}

	public void setIdSiasatan(Long idSiasatan) {
		this.idSiasatan = idSiasatan;
	}

	public String getNoKes() {
		return this.noKes;
	}

	public void setNoKes(String noKes) {
		this.noKes = noKes;
	}

	public String getNoKesSebelum() {
		return this.noKesSebelum;
	}

	public void setNoKesSebelum(String noKesSebelum) {
		this.noKesSebelum = noKesSebelum;
	}

	public String getNoKesBerikut() {
		return this.noKesBerikut;
	}

	public void setNoKesBerikut(String noKesBerikut) {
		this.noKesBerikut = noKesBerikut;
	}

	public String getNoSiasatan() {
		return this.noSiasatan;
	}

	public void setNoSiasatan(String noSiasatan) {
		this.noSiasatan = noSiasatan;
	}

	public Long getStatusSiasatan() {
		return this.statusSiasatan;
	}

	public void setStatusSiasatan(Long statusSiasatan) {
		this.statusSiasatan = statusSiasatan;
	}

	public Date getTarikhSiasatan() {
		return this.tarikhSiasatan;
	}

	public void setTarikhSiasatan(Date tarikhSiasatan) {
		this.tarikhSiasatan = tarikhSiasatan;
	}

	public String getMasaSiasatan() {
		return this.masaSiasatan;
	}

	public void setMasaSiasatan(String masaSiasatan) {
		this.masaSiasatan = masaSiasatan;
	}

	public String getTempat() {
		return this.tempat;
	}

	public void setTempat(String tempat) {
		this.tempat = tempat;
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

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getAlasanTangguh() {
		return this.alasanTangguh;
	}

	public void setAlasanTangguh(String alasanTangguh) {
		this.alasanTangguh = alasanTangguh;
	}

	public Double getNilaianJpph() {
		return this.nilaianJpph;
	}

	public void setNilaianJpph(Double nilaianJpph) {
		this.nilaianJpph = nilaianJpph;
	}

	public Long getIdUnitluas() {
		return this.idUnitluas;
	}

	public void setIdUnitluas(Long idUnitluas) {
		this.idUnitluas = idUnitluas;
	}

	public String getBantahanTuantnh() {
		return this.bantahanTuantnh;
	}

	public void setBantahanTuantnh(String bantahanTuantnh) {
		this.bantahanTuantnh = bantahanTuantnh;
	}

	public String getBantahanAgensi() {
		return this.bantahanAgensi;
	}

	public void setBantahanAgensi(String bantahanAgensi) {
		this.bantahanAgensi = bantahanAgensi;
	}

	public String getBantahanLain() {
		return this.bantahanLain;
	}

	public void setBantahanLain(String bantahanLain) {
		this.bantahanLain = bantahanLain;
	}

	public Long getTempohMilikTanah() {
		return this.tempohMilikTanah;
	}

	public void setTempohMilikTanah(Long tempohMilikTanah) {
		this.tempohMilikTanah = tempohMilikTanah;
	}

	public String getCaraMilik() {
		return this.caraMilik;
	}

	public void setCaraMilik(String caraMilik) {
		this.caraMilik = caraMilik;
	}

	public Double getHargaBeli() {
		return this.hargaBeli;
	}

	public void setHargaBeli(Double hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public String getJenisBangunan() {
		return this.jenisBangunan;
	}

	public void setJenisBangunan(String jenisBangunan) {
		this.jenisBangunan = jenisBangunan;
	}

	public String getJenisTanaman() {
		return this.jenisTanaman;
	}

	public void setJenisTanaman(String jenisTanaman) {
		this.jenisTanaman = jenisTanaman;
	}

	public String getFlagPecahSempadan() {
		return this.flagPecahSempadan;
	}

	public void setFlagPecahSempadan(String flagPecahSempadan) {
		this.flagPecahSempadan = flagPecahSempadan;
	}

	public String getFlagTukarSyarat() {
		return this.flagTukarSyarat;
	}

	public void setFlagTukarSyarat(String flagTukarSyarat) {
		this.flagTukarSyarat = flagTukarSyarat;
	}

	public Date getTarikhPecahSempadan() {
		return this.tarikhPecahSempadan;
	}

	public void setTarikhPecahSempadan(Date tarikhPecahSempadan) {
		this.tarikhPecahSempadan = tarikhPecahSempadan;
	}

	public Date getTarikhTukarSyarat() {
		return this.tarikhTukarSyarat;
	}

	public void setTarikhTukarSyarat(Date tarikhTukarSyarat) {
		this.tarikhTukarSyarat = tarikhTukarSyarat;
	}

	public String getStatusSemasa() {
		return this.statusSemasa;
	}

	public void setStatusSemasa(String statusSemasa) {
		this.statusSemasa = statusSemasa;
	}

	public String getBebanan() {
		return this.bebanan;
	}

	public void setBebanan(String bebanan) {
		this.bebanan = bebanan;
	}

	public String getKeteranganTuanTanah() {
		return this.keteranganTuanTanah;
	}

	public void setKeteranganTuanTanah(String keteranganTuanTanah) {
		this.keteranganTuanTanah = keteranganTuanTanah;
	}

	public String getKeteranganAgensi() {
		return this.keteranganAgensi;
	}

	public void setKeteranganAgensi(String keteranganAgensi) {
		this.keteranganAgensi = keteranganAgensi;
	}

	public String getKeteranganJurunilai() {
		return this.keteranganJurunilai;
	}

	public void setKeteranganJurunilai(String keteranganJurunilai) {
		this.keteranganJurunilai = keteranganJurunilai;
	}

	public String getTuntutanTuantnh() {
		return this.tuntutanTuantnh;
	}

	public void setTuntutanTuantnh(String tuntutanTuantnh) {
		this.tuntutanTuantnh = tuntutanTuantnh;
	}

	public String getTuntutanPbBebanan() {
		return this.tuntutanPbBebanan;
	}

	public void setTuntutanPbBebanan(String tuntutanPbBebanan) {
		this.tuntutanPbBebanan = tuntutanPbBebanan;
	}

	public String getTuntutanPbTdkdaftar() {
		return this.tuntutanPbTdkdaftar;
	}

	public void setTuntutanPbTdkdaftar(String tuntutanPbTdkdaftar) {
		this.tuntutanPbTdkdaftar = tuntutanPbTdkdaftar;
	}

	public String getTuntutanPbLain() {
		return this.tuntutanPbLain;
	}

	public void setTuntutanPbLain(String tuntutanPbLain) {
		this.tuntutanPbLain = tuntutanPbLain;
	}

	public Long getIdBorange() {
		return this.idBorange;
	}

	public void setIdBorange(Long idBorange) {
		this.idBorange = idBorange;
	}

	public String getPerintah() {
		return this.perintah;
	}

	public void setPerintah(String perintah) {
		this.perintah = perintah;
	}

	public Date getTarikhAkhirTampal() {
		return this.tarikhAkhirTampal;
	}

	public void setTarikhAkhirTampal(Date tarikhAkhirTampal) {
		this.tarikhAkhirTampal = tarikhAkhirTampal;
	}

	public String getFlagBantahan() {
		return this.flagBantahan;
	}

	public void setFlagBantahan(String flagBantahan) {
		this.flagBantahan = flagBantahan;
	}

	public Long getIdPegawaiSiasatan() {
		return this.idPegawaiSiasatan;
	}

	public void setIdPegawaiSiasatan(Long idPegawaiSiasatan) {
		this.idPegawaiSiasatan = idPegawaiSiasatan;
	}

	public Long getIdPenarikanbalik() {
		return this.idPenarikanbalik;
	}

	public void setIdPenarikanbalik(Long idPenarikanbalik) {
		this.idPenarikanbalik = idPenarikanbalik;
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

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}