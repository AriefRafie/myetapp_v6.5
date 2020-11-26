package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtphakmilikbangunan entity provides the base persistence
 * definition of the Tblhtphakmilikbangunan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilikbangunan implements
		java.io.Serializable {

	// Fields

	private Long idHakmilikbangunan;
	private Long idPermohonan;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idLuas;
	private String luas;
	private Long idMukim;
	private Long idDaerah;
	private Long idNegeri;
	private String sewaBulanan;
	private String ulasan;
	private Date tarikhMula;
	private Date tarikhTamat;
	private Long idPegawai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;
	private int bulan;
	private int tahun;
	private int hari;
	private Double sewaBulananDouble;

	// Constructors

	/** default constructor */
	public AbstractTblhtphakmilikbangunan() {
	}

	/** minimal constructor */
	public AbstractTblhtphakmilikbangunan(Long idHakmilikbangunan,
			Long idPermohonan, Long idLuas, Long idMukim, Long idDaerah,
			Long idNegeri) {
		this.idHakmilikbangunan = idHakmilikbangunan;
		this.idPermohonan = idPermohonan;
		this.idLuas = idLuas;
		this.idMukim = idMukim;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblhtphakmilikbangunan(Long idHakmilikbangunan,
			Long idPermohonan, String alamat1, String alamat2, String alamat3,
			String poskod, Long idLuas, String luas, Long idMukim,
			Long idDaerah, Long idNegeri, String sewaBulanan, String ulasan,
			Date tarikhMula, Date tarikhTamat, Long idPegawai, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idHakmilikbangunan = idHakmilikbangunan;
		this.idPermohonan = idPermohonan;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idLuas = idLuas;
		this.luas = luas;
		this.idMukim = idMukim;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.sewaBulanan = sewaBulanan;
		this.ulasan = ulasan;
		this.tarikhMula = tarikhMula;
		this.tarikhTamat = tarikhTamat;
		this.idPegawai = idPegawai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	/** full constructor */
	public AbstractTblhtphakmilikbangunan(Long idHakmilikbangunan,
			Long idPermohonan, String alamat1, String alamat2, String alamat3,
			String poskod, Long idLuas, String luas, Long idMukim,
			Long idDaerah, Long idNegeri, Double sewaBulanan, String ulasan,
			Date tarikhMula, Date tarikhTamat, Long idPegawai, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idHakmilikbangunan = idHakmilikbangunan;
		this.idPermohonan = idPermohonan;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idLuas = idLuas;
		this.luas = luas;
		this.idMukim = idMukim;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.sewaBulananDouble = sewaBulanan;
		this.ulasan = ulasan;
		this.tarikhMula = tarikhMula;
		this.tarikhTamat = tarikhTamat;
		this.idPegawai = idPegawai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}	
	// Property accessors

	public Long getIdHakmilikbangunan() {
		return this.idHakmilikbangunan;
	}

	public void setIdHakmilikbangunan(Long idHakmilikbangunan) {
		this.idHakmilikbangunan = idHakmilikbangunan;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
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

	public Long getIdLuas() {
		return this.idLuas;
	}

	public void setIdLuas(Long idLuas) {
		this.idLuas = idLuas;
	}

	public String getLuas() {
		return this.luas;
	}

	public void setLuas(String luas) {
		this.luas = luas;
	}

	public Long getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(Long idMukim) {
		this.idMukim = idMukim;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getSewaBulanan() {
		return this.sewaBulanan;
	}

	public void setSewaBulanan(String sewaBulanan) {
		this.sewaBulanan = sewaBulanan;
	}
	
	public Double getSewaBulananDouble() {
		return this.sewaBulananDouble;
	}	

	public void setSewaBulananDouble(Double sewaBulananDouble) {
		this.sewaBulananDouble = sewaBulananDouble;
	}	

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public Date getTarikhMula() {
		return this.tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhTamat() {
		return this.tarikhTamat;
	}

	public void setTarikhTamat(Date tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
	}

	public Long getIdPegawai() {
		return this.idPegawai;
	}

	public void setIdPegawai(Long idPegawai) {
		this.idPegawai = idPegawai;
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

	public int getBulan() {
		return bulan;
	}

	public void setBulan(int bulan) {
		this.bulan = bulan;
	}

	public int getTahun() {
		return tahun;
	}

	public void setTahun(int tahun) {
		this.tahun = tahun;
	}
	public int getHari() {
		return hari;
	}

	public void setHari(int hari) {
		this.hari = hari;
	}
	
}