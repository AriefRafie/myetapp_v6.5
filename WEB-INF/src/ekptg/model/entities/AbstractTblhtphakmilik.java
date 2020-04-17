package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtphakmilik entity provides the base persistence definition of the
 * Tblhtphakmilik entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilik implements java.io.Serializable {

	// Fields

	private Long idHakmilik;
	private Long idPermohonan;
	private Long idLuas;
	private String peganganHakmilik;
	private Long idHakmiliksptb;
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
	private String noPtk;
	private String statusTanah;
	private Date tarikhMula;
	private Date tarikhLuput;
	private Double luasBersamaan;
	private String jenisTanah;
	private String flagPtp;
	private Long idKategori;
	private Long idDaerah;
	private Long idNegeri;
	private Long idMukim;
	private Long idLot;
	private Long idJenishakmilik;
	private Long idRizab;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtphakmilik() {
	}

	/** minimal constructor */
	public AbstractTblhtphakmilik(Long idHakmilik, Long idPermohonan,
			Long idLuas, Long idHakmiliksptb, Long idSubkategori,
			Long idKategori, Long idDaerah, Long idNegeri, Long idMukim,
			Long idLot, Long idJenishakmilik, Long idRizab) {
		this.idHakmilik = idHakmilik;
		this.idPermohonan = idPermohonan;
		this.idLuas = idLuas;
		this.idHakmiliksptb = idHakmiliksptb;
		this.idSubkategori = idSubkategori;
		this.idKategori = idKategori;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.idMukim = idMukim;
		this.idLot = idLot;
		this.idJenishakmilik = idJenishakmilik;
		this.idRizab = idRizab;
	}

	/** full constructor */
	public AbstractTblhtphakmilik(Long idHakmilik, Long idPermohonan,
			Long idLuas, String peganganHakmilik, Long idHakmiliksptb,
			String noHakmilik, String noWarta, Date tarikhWarta, String noLot,
			String luas, String noSyit, String noPelan, String syarat,
			String sekatan, Double cukai, String flagPelan, String ulasan,
			String statusSwasta, String tindakanLanjut, Long idSubkategori,
			String lokasi, Double cukaiTerkini, String statusRizab,
			String noBangunan, String noTingkat, String noPtk,
			String statusTanah, Date tarikhMula, Date tarikhLuput,
			Double luasBersamaan, String jenisTanah, String flagPtp,
			Long idKategori, Long idDaerah, Long idNegeri, Long idMukim,
			Long idLot, Long idJenishakmilik, Long idRizab, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idHakmilik = idHakmilik;
		this.idPermohonan = idPermohonan;
		this.idLuas = idLuas;
		this.peganganHakmilik = peganganHakmilik;
		this.idHakmiliksptb = idHakmiliksptb;
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
		this.noPtk = noPtk;
		this.statusTanah = statusTanah;
		this.tarikhMula = tarikhMula;
		this.tarikhLuput = tarikhLuput;
		this.luasBersamaan = luasBersamaan;
		this.jenisTanah = jenisTanah;
		this.flagPtp = flagPtp;
		this.idKategori = idKategori;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.idMukim = idMukim;
		this.idLot = idLot;
		this.idJenishakmilik = idJenishakmilik;
		this.idRizab = idRizab;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
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

	public Long getIdHakmiliksptb() {
		return this.idHakmiliksptb;
	}

	public void setIdHakmiliksptb(Long idHakmiliksptb) {
		this.idHakmiliksptb = idHakmiliksptb;
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

	public String getNoPtk() {
		return this.noPtk;
	}

	public void setNoPtk(String noPtk) {
		this.noPtk = noPtk;
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

	public String getJenisTanah() {
		return this.jenisTanah;
	}

	public void setJenisTanah(String jenisTanah) {
		this.jenisTanah = jenisTanah;
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

	public Long getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(Long idMukim) {
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

	public Long getIdRizab() {
		return this.idRizab;
	}

	public void setIdRizab(Long idRizab) {
		this.idRizab = idRizab;
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