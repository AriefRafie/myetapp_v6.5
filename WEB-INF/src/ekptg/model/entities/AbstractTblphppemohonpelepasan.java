package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppemohonpelepasan entity provides the base persistence
 * definition of the Tblphppemohonpelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppemohonpelepasan implements
		java.io.Serializable {

	// Fields

	private Long idPemohonpelepasan;
	private Tblphppermohonanpelepasan tblphppermohonanpelepasan;
	private Long idKategoripemohon;
	private String nama;
	private Long idJenispengenalan;
	private String noKp;
	private String alamat1Tetap;
	private String alamat2Tetap;
	private String alamat3Tetap;
	private String poskodTetap;
	private Long idBandartetap;
	private Long idNegeritetap;
	private String alamat1Surat;
	private String alamat2Surat;
	private String alamat3Surat;
	private String poskodSurat;
	private Long idBandarsurat;
	private Long idNegerisurat;
	private String noTelPejabat;
	private String extTel;
	private String noFax;
	private String noTelBimbit;
	private String emel;
	private String tempatDaftar;
	private String statusSyarikat;
	private Double modalDibenarkan;
	private Double modalJelas;
	private String undangUndangDiperbadankan;
	private String jenisPendaftaran;
	private Long idAgensi;
	private Long idMenteri;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private String flagSyarikat;

	// Constructors

	/** default constructor */
	public AbstractTblphppemohonpelepasan() {
	}

	/** minimal constructor */
	public AbstractTblphppemohonpelepasan(Long idPemohonpelepasan) {
		this.idPemohonpelepasan = idPemohonpelepasan;
	}

	/** full constructor */
	public AbstractTblphppemohonpelepasan(Long idPemohonpelepasan,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan,
			Long idKategoripemohon, String nama, Long idJenispengenalan,
			String noKp, String alamat1Tetap, String alamat2Tetap,
			String alamat3Tetap, String poskodTetap, Long idBandartetap,
			Long idNegeritetap, String alamat1Surat, String alamat2Surat,
			String alamat3Surat, String poskodSurat, Long idBandarsurat,
			Long idNegerisurat, String noTelPejabat, String extTel,
			String noFax, String noTelBimbit, String emel, String tempatDaftar,
			String statusSyarikat, Double modalDibenarkan, Double modalJelas,
			String undangUndangDiperbadankan, String jenisPendaftaran,
			Long idAgensi, Long idMenteri, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, String flagSyarikat) {
		this.idPemohonpelepasan = idPemohonpelepasan;
		this.tblphppermohonanpelepasan = tblphppermohonanpelepasan;
		this.idKategoripemohon = idKategoripemohon;
		this.nama = nama;
		this.idJenispengenalan = idJenispengenalan;
		this.noKp = noKp;
		this.alamat1Tetap = alamat1Tetap;
		this.alamat2Tetap = alamat2Tetap;
		this.alamat3Tetap = alamat3Tetap;
		this.poskodTetap = poskodTetap;
		this.idBandartetap = idBandartetap;
		this.idNegeritetap = idNegeritetap;
		this.alamat1Surat = alamat1Surat;
		this.alamat2Surat = alamat2Surat;
		this.alamat3Surat = alamat3Surat;
		this.poskodSurat = poskodSurat;
		this.idBandarsurat = idBandarsurat;
		this.idNegerisurat = idNegerisurat;
		this.noTelPejabat = noTelPejabat;
		this.extTel = extTel;
		this.noFax = noFax;
		this.noTelBimbit = noTelBimbit;
		this.emel = emel;
		this.tempatDaftar = tempatDaftar;
		this.statusSyarikat = statusSyarikat;
		this.modalDibenarkan = modalDibenarkan;
		this.modalJelas = modalJelas;
		this.undangUndangDiperbadankan = undangUndangDiperbadankan;
		this.jenisPendaftaran = jenisPendaftaran;
		this.idAgensi = idAgensi;
		this.idMenteri = idMenteri;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.flagSyarikat = flagSyarikat;
	}

	// Property accessors

	public Long getIdPemohonpelepasan() {
		return this.idPemohonpelepasan;
	}

	public void setIdPemohonpelepasan(Long idPemohonpelepasan) {
		this.idPemohonpelepasan = idPemohonpelepasan;
	}

	public Tblphppermohonanpelepasan getTblphppermohonanpelepasan() {
		return this.tblphppermohonanpelepasan;
	}

	public void setTblphppermohonanpelepasan(
			Tblphppermohonanpelepasan tblphppermohonanpelepasan) {
		this.tblphppermohonanpelepasan = tblphppermohonanpelepasan;
	}

	public Long getIdKategoripemohon() {
		return this.idKategoripemohon;
	}

	public void setIdKategoripemohon(Long idKategoripemohon) {
		this.idKategoripemohon = idKategoripemohon;
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

	public String getAlamat1Tetap() {
		return this.alamat1Tetap;
	}

	public void setAlamat1Tetap(String alamat1Tetap) {
		this.alamat1Tetap = alamat1Tetap;
	}

	public String getAlamat2Tetap() {
		return this.alamat2Tetap;
	}

	public void setAlamat2Tetap(String alamat2Tetap) {
		this.alamat2Tetap = alamat2Tetap;
	}

	public String getAlamat3Tetap() {
		return this.alamat3Tetap;
	}

	public void setAlamat3Tetap(String alamat3Tetap) {
		this.alamat3Tetap = alamat3Tetap;
	}

	public String getPoskodTetap() {
		return this.poskodTetap;
	}

	public void setPoskodTetap(String poskodTetap) {
		this.poskodTetap = poskodTetap;
	}

	public Long getIdBandartetap() {
		return this.idBandartetap;
	}

	public void setIdBandartetap(Long idBandartetap) {
		this.idBandartetap = idBandartetap;
	}

	public Long getIdNegeritetap() {
		return this.idNegeritetap;
	}

	public void setIdNegeritetap(Long idNegeritetap) {
		this.idNegeritetap = idNegeritetap;
	}

	public String getAlamat1Surat() {
		return this.alamat1Surat;
	}

	public void setAlamat1Surat(String alamat1Surat) {
		this.alamat1Surat = alamat1Surat;
	}

	public String getAlamat2Surat() {
		return this.alamat2Surat;
	}

	public void setAlamat2Surat(String alamat2Surat) {
		this.alamat2Surat = alamat2Surat;
	}

	public String getAlamat3Surat() {
		return this.alamat3Surat;
	}

	public void setAlamat3Surat(String alamat3Surat) {
		this.alamat3Surat = alamat3Surat;
	}

	public String getPoskodSurat() {
		return this.poskodSurat;
	}

	public void setPoskodSurat(String poskodSurat) {
		this.poskodSurat = poskodSurat;
	}

	public Long getIdBandarsurat() {
		return this.idBandarsurat;
	}

	public void setIdBandarsurat(Long idBandarsurat) {
		this.idBandarsurat = idBandarsurat;
	}

	public Long getIdNegerisurat() {
		return this.idNegerisurat;
	}

	public void setIdNegerisurat(Long idNegerisurat) {
		this.idNegerisurat = idNegerisurat;
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

	public Long getIdAgensi() {
		return this.idAgensi;
	}

	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}

	public Long getIdMenteri() {
		return this.idMenteri;
	}

	public void setIdMenteri(Long idMenteri) {
		this.idMenteri = idMenteri;
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

	public String getFlagSyarikat() {
		return this.flagSyarikat;
	}

	public void setFlagSyarikat(String flagSyarikat) {
		this.flagSyarikat = flagSyarikat;
	}

}