package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppenceroboh entity provides the base persistence definition of
 * the Tblphppenceroboh entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppenceroboh implements java.io.Serializable {

	// Fields

	private Long idPenceroboh;
	private Tblphppermohonanpenyewaan tblphppermohonanpenyewaan;
	private String nama;
	private Long idJenispengenalan;
	private String noKp;
	private String noKpLama;
	private Long idWarnakp;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idBandar;
	private Long idNegeri;
	private String noTelRumah;
	private String noTelPejabat;
	private String extTel;
	private String noFax;
	private String noTelBimbit;
	private String emel;
	private Date tarikhLahir;
	private Long umur;
	private Long idKeturunan;
	private Long idWarganegara;
	private String pekerjaan;
	private Double pendapatan;
	private Long bilAhliKeluarga;
	private Long bilRumah;
	private Double luas;
	private Long idUnitluas;
	private String tempatDaftar;
	private String statusSyarikat;
	private Double modalDibenarkan;
	private Double modalJelas;
	private String undangUndangDiperbadankan;
	private String jenisPendaftaran;
	private Long idJantina;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphppenceroboh() {
	}

	/** minimal constructor */
	public AbstractTblphppenceroboh(Long idPenceroboh) {
		this.idPenceroboh = idPenceroboh;
	}

	/** full constructor */
	public AbstractTblphppenceroboh(Long idPenceroboh,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan, String nama,
			Long idJenispengenalan, String noKp, String noKpLama,
			Long idWarnakp, String alamat1, String alamat2, String alamat3,
			String poskod, Long idBandar, Long idNegeri, String noTelRumah,
			String noTelPejabat, String extTel, String noFax,
			String noTelBimbit, String emel, Date tarikhLahir, Long umur,
			Long idKeturunan, Long idWarganegara, String pekerjaan,
			Double pendapatan, Long bilAhliKeluarga, Long bilRumah,
			Double luas, Long idUnitluas, String tempatDaftar,
			String statusSyarikat, Double modalDibenarkan, Double modalJelas,
			String undangUndangDiperbadankan, String jenisPendaftaran,
			Long idJantina, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPenceroboh = idPenceroboh;
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
		this.nama = nama;
		this.idJenispengenalan = idJenispengenalan;
		this.noKp = noKp;
		this.noKpLama = noKpLama;
		this.idWarnakp = idWarnakp;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idBandar = idBandar;
		this.idNegeri = idNegeri;
		this.noTelRumah = noTelRumah;
		this.noTelPejabat = noTelPejabat;
		this.extTel = extTel;
		this.noFax = noFax;
		this.noTelBimbit = noTelBimbit;
		this.emel = emel;
		this.tarikhLahir = tarikhLahir;
		this.umur = umur;
		this.idKeturunan = idKeturunan;
		this.idWarganegara = idWarganegara;
		this.pekerjaan = pekerjaan;
		this.pendapatan = pendapatan;
		this.bilAhliKeluarga = bilAhliKeluarga;
		this.bilRumah = bilRumah;
		this.luas = luas;
		this.idUnitluas = idUnitluas;
		this.tempatDaftar = tempatDaftar;
		this.statusSyarikat = statusSyarikat;
		this.modalDibenarkan = modalDibenarkan;
		this.modalJelas = modalJelas;
		this.undangUndangDiperbadankan = undangUndangDiperbadankan;
		this.jenisPendaftaran = jenisPendaftaran;
		this.idJantina = idJantina;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPenceroboh() {
		return this.idPenceroboh;
	}

	public void setIdPenceroboh(Long idPenceroboh) {
		this.idPenceroboh = idPenceroboh;
	}

	public Tblphppermohonanpenyewaan getTblphppermohonanpenyewaan() {
		return this.tblphppermohonanpenyewaan;
	}

	public void setTblphppermohonanpenyewaan(
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan) {
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Long getIdJenispengenalan() {
		return this.idJenispengenalan;
	}

	public void setIdJenispengenalan(Long idJenispengenalan) {
		this.idJenispengenalan = idJenispengenalan;
	}

	public String getNoKp() {
		return this.noKp;
	}

	public void setNoKp(String noKp) {
		this.noKp = noKp;
	}

	public String getNoKpLama() {
		return this.noKpLama;
	}

	public void setNoKpLama(String noKpLama) {
		this.noKpLama = noKpLama;
	}

	public Long getIdWarnakp() {
		return this.idWarnakp;
	}

	public void setIdWarnakp(Long idWarnakp) {
		this.idWarnakp = idWarnakp;
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

	public Long getIdBandar() {
		return this.idBandar;
	}

	public void setIdBandar(Long idBandar) {
		this.idBandar = idBandar;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getNoTelRumah() {
		return this.noTelRumah;
	}

	public void setNoTelRumah(String noTelRumah) {
		this.noTelRumah = noTelRumah;
	}

	public String getNoTelPejabat() {
		return this.noTelPejabat;
	}

	public void setNoTelPejabat(String noTelPejabat) {
		this.noTelPejabat = noTelPejabat;
	}

	public String getExtTel() {
		return this.extTel;
	}

	public void setExtTel(String extTel) {
		this.extTel = extTel;
	}

	public String getNoFax() {
		return this.noFax;
	}

	public void setNoFax(String noFax) {
		this.noFax = noFax;
	}

	public String getNoTelBimbit() {
		return this.noTelBimbit;
	}

	public void setNoTelBimbit(String noTelBimbit) {
		this.noTelBimbit = noTelBimbit;
	}

	public String getEmel() {
		return this.emel;
	}

	public void setEmel(String emel) {
		this.emel = emel;
	}

	public Date getTarikhLahir() {
		return this.tarikhLahir;
	}

	public void setTarikhLahir(Date tarikhLahir) {
		this.tarikhLahir = tarikhLahir;
	}

	public Long getUmur() {
		return this.umur;
	}

	public void setUmur(Long umur) {
		this.umur = umur;
	}

	public Long getIdKeturunan() {
		return this.idKeturunan;
	}

	public void setIdKeturunan(Long idKeturunan) {
		this.idKeturunan = idKeturunan;
	}

	public Long getIdWarganegara() {
		return this.idWarganegara;
	}

	public void setIdWarganegara(Long idWarganegara) {
		this.idWarganegara = idWarganegara;
	}

	public String getPekerjaan() {
		return this.pekerjaan;
	}

	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}

	public Double getPendapatan() {
		return this.pendapatan;
	}

	public void setPendapatan(Double pendapatan) {
		this.pendapatan = pendapatan;
	}

	public Long getBilAhliKeluarga() {
		return this.bilAhliKeluarga;
	}

	public void setBilAhliKeluarga(Long bilAhliKeluarga) {
		this.bilAhliKeluarga = bilAhliKeluarga;
	}

	public Long getBilRumah() {
		return this.bilRumah;
	}

	public void setBilRumah(Long bilRumah) {
		this.bilRumah = bilRumah;
	}

	public Double getLuas() {
		return this.luas;
	}

	public void setLuas(Double luas) {
		this.luas = luas;
	}

	public Long getIdUnitluas() {
		return this.idUnitluas;
	}

	public void setIdUnitluas(Long idUnitluas) {
		this.idUnitluas = idUnitluas;
	}

	public String getTempatDaftar() {
		return this.tempatDaftar;
	}

	public void setTempatDaftar(String tempatDaftar) {
		this.tempatDaftar = tempatDaftar;
	}

	public String getStatusSyarikat() {
		return this.statusSyarikat;
	}

	public void setStatusSyarikat(String statusSyarikat) {
		this.statusSyarikat = statusSyarikat;
	}

	public Double getModalDibenarkan() {
		return this.modalDibenarkan;
	}

	public void setModalDibenarkan(Double modalDibenarkan) {
		this.modalDibenarkan = modalDibenarkan;
	}

	public Double getModalJelas() {
		return this.modalJelas;
	}

	public void setModalJelas(Double modalJelas) {
		this.modalJelas = modalJelas;
	}

	public String getUndangUndangDiperbadankan() {
		return this.undangUndangDiperbadankan;
	}

	public void setUndangUndangDiperbadankan(String undangUndangDiperbadankan) {
		this.undangUndangDiperbadankan = undangUndangDiperbadankan;
	}

	public String getJenisPendaftaran() {
		return this.jenisPendaftaran;
	}

	public void setJenisPendaftaran(String jenisPendaftaran) {
		this.jenisPendaftaran = jenisPendaftaran;
	}

	public Long getIdJantina() {
		return this.idJantina;
	}

	public void setIdJantina(Long idJantina) {
		this.idJantina = idJantina;
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