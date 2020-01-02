package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtphakmilikurusan entity provides the base persistence definition
 * of the Tblhtphakmilikurusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilikurusan implements
		java.io.Serializable {

	// Fields

	private Long idHakmilikurusan;
	private Long idPermohonan;
	private Long idLuas;
	private String peganganHakmilik;
	private String noHakmilik;
	private String noWarta;
	private Date tarikhWarta;
	private String noLot;
	private String luas;
	private String noSyit;
	private String noPelan;
	private String syarat;
	private String sekatan;
	private Double cukai;
	private String flagPelan;
	private String ulasan;
	private String statusSwasta;
	private String tindakanLanjut;
	private Long idSubkategori;
	private String lokasi;
	private Double cukaiTerkini;
	private String statusRizab;
	private String noBangunan;
	private String noTingkat;
	private String noptk;
	private String statusTanah;
	private Date tarikhMula;
	private Date tarikhLuput;
	private Double luasBersamaan;
	private Long idJenistanah;
	private String flagPtp;
	private Long idKategori;
	private Long idDaerah;
	private Long idNegeri;
	private String idMukim;
	private Long idLot;
	private Long idJenishakmilik;
	private Long bil;
	private Long idJenisrizab;
	private String noRizab;
	private Date tarikhRizab;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblhtphakmilikurusan() {
	}

	/** minimal constructor */
	public AbstractTblhtphakmilikurusan(Long idHakmilikurusan,
			Long idPermohonan, Long idLuas, String peganganHakmilik,
			Long idSubkategori, Long idKategori, Long idDaerah, Long idNegeri,
			String idMukim, Long idLot, Long idJenishakmilik) {
		this.idHakmilikurusan = idHakmilikurusan;
		this.idPermohonan = idPermohonan;
		this.idLuas = idLuas;
		this.peganganHakmilik = peganganHakmilik;
		this.idSubkategori = idSubkategori;
		this.idKategori = idKategori;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.idMukim = idMukim;
		this.idLot = idLot;
		this.idJenishakmilik = idJenishakmilik;
	}

	/** full constructor */
	public AbstractTblhtphakmilikurusan(Long idHakmilikurusan,
			Long idPermohonan, Long idLuas, String peganganHakmilik,
			String noHakmilik, String noWarta, Date tarikhWarta, String noLot,
			String luas, String noSyit, String noPelan, String syarat,
			String sekatan, Double cukai, String flagPelan, String ulasan,
			String statusSwasta, String tindakanLanjut, Long idSubkategori,
			String lokasi, Double cukaiTerkini, String statusRizab,
			String noBangunan, String noTingkat, String noptk,
			String statusTanah, Date tarikhMula, Date tarikhLuput,
			Double luasBersamaan, Long idJenistanah, String flagPtp,
			Long idKategori, Long idDaerah, Long idNegeri, String idMukim,
			Long idLot, Long idJenishakmilik, Long bil, Long idJenisrizab,
			String noRizab, Date tarikhRizab, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idHakmilikurusan = idHakmilikurusan;
		this.idPermohonan = idPermohonan;
		this.idLuas = idLuas;
		this.peganganHakmilik = peganganHakmilik;
		this.noHakmilik = noHakmilik;
		this.noWarta = noWarta;
		this.tarikhWarta = tarikhWarta;
		this.noLot = noLot;
		this.luas = luas;
		this.noSyit = noSyit;
		this.noPelan = noPelan;
		this.syarat = syarat;
		this.sekatan = sekatan;
		this.cukai = cukai;
		this.flagPelan = flagPelan;
		this.ulasan = ulasan;
		this.statusSwasta = statusSwasta;
		this.tindakanLanjut = tindakanLanjut;
		this.idSubkategori = idSubkategori;
		this.lokasi = lokasi;
		this.cukaiTerkini = cukaiTerkini;
		this.statusRizab = statusRizab;
		this.noBangunan = noBangunan;
		this.noTingkat = noTingkat;
		this.noptk = noptk;
		this.statusTanah = statusTanah;
		this.tarikhMula = tarikhMula;
		this.tarikhLuput = tarikhLuput;
		this.luasBersamaan = luasBersamaan;
		this.idJenistanah = idJenistanah;
		this.flagPtp = flagPtp;
		this.idKategori = idKategori;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.idMukim = idMukim;
		this.idLot = idLot;
		this.idJenishakmilik = idJenishakmilik;
		this.bil = bil;
		this.idJenisrizab = idJenisrizab;
		this.noRizab = noRizab;
		this.tarikhRizab = tarikhRizab;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdHakmilikurusan() {
		return this.idHakmilikurusan;
	}

	public void setIdHakmilikurusan(Long idHakmilikurusan) {
		this.idHakmilikurusan = idHakmilikurusan;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdLuas() {
		return this.idLuas;
	}

	public void setIdLuas(Long idLuas) {
		this.idLuas = idLuas;
	}

	public String getPeganganHakmilik() {
		return this.peganganHakmilik;
	}

	public void setPeganganHakmilik(String peganganHakmilik) {
		this.peganganHakmilik = peganganHakmilik;
	}

	public String getNoHakmilik() {
		return this.noHakmilik;
	}

	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}

	public String getNoWarta() {
		return this.noWarta;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public Date getTarikhWarta() {
		return this.tarikhWarta;
	}

	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public String getNoLot() {
		return this.noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public String getLuas() {
		return this.luas;
	}

	public void setLuas(String luas) {
		this.luas = luas;
	}

	public String getNoSyit() {
		return this.noSyit;
	}

	public void setNoSyit(String noSyit) {
		this.noSyit = noSyit;
	}

	public String getNoPelan() {
		return this.noPelan;
	}

	public void setNoPelan(String noPelan) {
		this.noPelan = noPelan;
	}

	public String getSyarat() {
		return this.syarat;
	}

	public void setSyarat(String syarat) {
		this.syarat = syarat;
	}

	public String getSekatan() {
		return this.sekatan;
	}

	public void setSekatan(String sekatan) {
		this.sekatan = sekatan;
	}

	public Double getCukai() {
		return this.cukai;
	}

	public void setCukai(Double cukai) {
		this.cukai = cukai;
	}

	public String getFlagPelan() {
		return this.flagPelan;
	}

	public void setFlagPelan(String flagPelan) {
		this.flagPelan = flagPelan;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public String getStatusSwasta() {
		return this.statusSwasta;
	}

	public void setStatusSwasta(String statusSwasta) {
		this.statusSwasta = statusSwasta;
	}

	public String getTindakanLanjut() {
		return this.tindakanLanjut;
	}

	public void setTindakanLanjut(String tindakanLanjut) {
		this.tindakanLanjut = tindakanLanjut;
	}

	public Long getIdSubkategori() {
		return this.idSubkategori;
	}

	public void setIdSubkategori(Long idSubkategori) {
		this.idSubkategori = idSubkategori;
	}

	public String getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public Double getCukaiTerkini() {
		return this.cukaiTerkini;
	}

	public void setCukaiTerkini(Double cukaiTerkini) {
		this.cukaiTerkini = cukaiTerkini;
	}

	public String getStatusRizab() {
		return this.statusRizab;
	}

	public void setStatusRizab(String statusRizab) {
		this.statusRizab = statusRizab;
	}

	public String getNoBangunan() {
		return this.noBangunan;
	}

	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}

	public String getNoTingkat() {
		return this.noTingkat;
	}

	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}

	public String getNoptk() {
		return this.noptk;
	}

	public void setNoptk(String noptk) {
		this.noptk = noptk;
	}

	public String getStatusTanah() {
		return this.statusTanah;
	}

	public void setStatusTanah(String statusTanah) {
		this.statusTanah = statusTanah;
	}

	public Date getTarikhMula() {
		return this.tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhLuput() {
		return this.tarikhLuput;
	}

	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}

	public Double getLuasBersamaan() {
		return this.luasBersamaan;
	}

	public void setLuasBersamaan(Double luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
	}

	public Long getIdJenistanah() {
		return this.idJenistanah;
	}

	public void setIdJenistanah(Long idJenistanah) {
		this.idJenistanah = idJenistanah;
	}

	public String getFlagPtp() {
		return this.flagPtp;
	}

	public void setFlagPtp(String flagPtp) {
		this.flagPtp = flagPtp;
	}

	public Long getIdKategori() {
		return this.idKategori;
	}

	public void setIdKategori(Long idKategori) {
		this.idKategori = idKategori;
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

	public String getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(String idMukim) {
		this.idMukim = idMukim;
	}

	public Long getIdLot() {
		return this.idLot;
	}

	public void setIdLot(Long idLot) {
		this.idLot = idLot;
	}

	public Long getIdJenishakmilik() {
		return this.idJenishakmilik;
	}

	public void setIdJenishakmilik(Long idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	public Long getBil() {
		return this.bil;
	}

	public void setBil(Long bil) {
		this.bil = bil;
	}

	public Long getIdJenisrizab() {
		return this.idJenisrizab;
	}

	public void setIdJenisrizab(Long idJenisrizab) {
		this.idJenisrizab = idJenisrizab;
	}

	public String getNoRizab() {
		return this.noRizab;
	}

	public void setNoRizab(String noRizab) {
		this.noRizab = noRizab;
	}

	public Date getTarikhRizab() {
		return this.tarikhRizab;
	}

	public void setTarikhRizab(Date tarikhRizab) {
		this.tarikhRizab = tarikhRizab;
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