package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtphakmilikId entity provides the base persistence definition of
 * the TblhtphakmilikId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilikId implements java.io.Serializable {

	// Fields

	private Long idHtphakmilik;
	private Tblhtppermohonan tblhtppermohonan;
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
	public AbstractTblhtphakmilikId() {
	}

	/** minimal constructor */
	public AbstractTblhtphakmilikId(Long idHtphakmilik,
			Tblhtppermohonan tblhtppermohonan, Long idLuas,
			String peganganHakmilik, Long idSubkategori, Long idKategori,
			Long idDaerah, Long idNegeri, Long idMukim, Long idLot,
			Long idJenishakmilik, Long idRizab) {
		this.idHtphakmilik = idHtphakmilik;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idLuas = idLuas;
		this.peganganHakmilik = peganganHakmilik;
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
	public AbstractTblhtphakmilikId(Long idHtphakmilik,
			Tblhtppermohonan tblhtppermohonan, Long idLuas,
			String peganganHakmilik, String noHakmilik, String noWarta,
			Date tarikhWarta, String noLot, String luas, String noSyit,
			String noPelan, String syarat, String sekatan, Double cukai,
			String flagPelan, String ulasan, String statusSwasta,
			String tindakanLanjut, Long idSubkategori, String lokasi,
			Double cukaiTerkini, String statusRizab, String noBangunan,
			String noTingkat, String noPtk, String statusTanah,
			Date tarikhMula, Date tarikhLuput, Double luasBersamaan,
			String jenisTanah, String flagPtp, Long idKategori, Long idDaerah,
			Long idNegeri, Long idMukim, Long idLot, Long idJenishakmilik,
			Long idRizab, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idHtphakmilik = idHtphakmilik;
		this.tblhtppermohonan = tblhtppermohonan;
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

	public Long getIdHtphakmilik() {
		return this.idHtphakmilik;
	}

	public void setIdHtphakmilik(Long idHtphakmilik) {
		this.idHtphakmilik = idHtphakmilik;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblhtphakmilikId))
			return false;
		AbstractTblhtphakmilikId castOther = (AbstractTblhtphakmilikId) other;

		return ((this.getIdHtphakmilik() == castOther.getIdHtphakmilik()) || (this
				.getIdHtphakmilik() != null
				&& castOther.getIdHtphakmilik() != null && this
				.getIdHtphakmilik().equals(castOther.getIdHtphakmilik())))
				&& ((this.getTblhtppermohonan() == castOther
						.getTblhtppermohonan()) || (this.getTblhtppermohonan() != null
						&& castOther.getTblhtppermohonan() != null && this
						.getTblhtppermohonan().equals(
								castOther.getTblhtppermohonan())))
				&& ((this.getIdLuas() == castOther.getIdLuas()) || (this
						.getIdLuas() != null
						&& castOther.getIdLuas() != null && this.getIdLuas()
						.equals(castOther.getIdLuas())))
				&& ((this.getPeganganHakmilik() == castOther
						.getPeganganHakmilik()) || (this.getPeganganHakmilik() != null
						&& castOther.getPeganganHakmilik() != null && this
						.getPeganganHakmilik().equals(
								castOther.getPeganganHakmilik())))
				&& ((this.getNoHakmilik() == castOther.getNoHakmilik()) || (this
						.getNoHakmilik() != null
						&& castOther.getNoHakmilik() != null && this
						.getNoHakmilik().equals(castOther.getNoHakmilik())))
				&& ((this.getNoWarta() == castOther.getNoWarta()) || (this
						.getNoWarta() != null
						&& castOther.getNoWarta() != null && this.getNoWarta()
						.equals(castOther.getNoWarta())))
				&& ((this.getTarikhWarta() == castOther.getTarikhWarta()) || (this
						.getTarikhWarta() != null
						&& castOther.getTarikhWarta() != null && this
						.getTarikhWarta().equals(castOther.getTarikhWarta())))
				&& ((this.getNoLot() == castOther.getNoLot()) || (this
						.getNoLot() != null
						&& castOther.getNoLot() != null && this.getNoLot()
						.equals(castOther.getNoLot())))
				&& ((this.getLuas() == castOther.getLuas()) || (this.getLuas() != null
						&& castOther.getLuas() != null && this.getLuas()
						.equals(castOther.getLuas())))
				&& ((this.getNoSyit() == castOther.getNoSyit()) || (this
						.getNoSyit() != null
						&& castOther.getNoSyit() != null && this.getNoSyit()
						.equals(castOther.getNoSyit())))
				&& ((this.getNoPelan() == castOther.getNoPelan()) || (this
						.getNoPelan() != null
						&& castOther.getNoPelan() != null && this.getNoPelan()
						.equals(castOther.getNoPelan())))
				&& ((this.getSyarat() == castOther.getSyarat()) || (this
						.getSyarat() != null
						&& castOther.getSyarat() != null && this.getSyarat()
						.equals(castOther.getSyarat())))
				&& ((this.getSekatan() == castOther.getSekatan()) || (this
						.getSekatan() != null
						&& castOther.getSekatan() != null && this.getSekatan()
						.equals(castOther.getSekatan())))
				&& ((this.getCukai() == castOther.getCukai()) || (this
						.getCukai() != null
						&& castOther.getCukai() != null && this.getCukai()
						.equals(castOther.getCukai())))
				&& ((this.getFlagPelan() == castOther.getFlagPelan()) || (this
						.getFlagPelan() != null
						&& castOther.getFlagPelan() != null && this
						.getFlagPelan().equals(castOther.getFlagPelan())))
				&& ((this.getUlasan() == castOther.getUlasan()) || (this
						.getUlasan() != null
						&& castOther.getUlasan() != null && this.getUlasan()
						.equals(castOther.getUlasan())))
				&& ((this.getStatusSwasta() == castOther.getStatusSwasta()) || (this
						.getStatusSwasta() != null
						&& castOther.getStatusSwasta() != null && this
						.getStatusSwasta().equals(castOther.getStatusSwasta())))
				&& ((this.getTindakanLanjut() == castOther.getTindakanLanjut()) || (this
						.getTindakanLanjut() != null
						&& castOther.getTindakanLanjut() != null && this
						.getTindakanLanjut().equals(
								castOther.getTindakanLanjut())))
				&& ((this.getIdSubkategori() == castOther.getIdSubkategori()) || (this
						.getIdSubkategori() != null
						&& castOther.getIdSubkategori() != null && this
						.getIdSubkategori()
						.equals(castOther.getIdSubkategori())))
				&& ((this.getLokasi() == castOther.getLokasi()) || (this
						.getLokasi() != null
						&& castOther.getLokasi() != null && this.getLokasi()
						.equals(castOther.getLokasi())))
				&& ((this.getCukaiTerkini() == castOther.getCukaiTerkini()) || (this
						.getCukaiTerkini() != null
						&& castOther.getCukaiTerkini() != null && this
						.getCukaiTerkini().equals(castOther.getCukaiTerkini())))
				&& ((this.getStatusRizab() == castOther.getStatusRizab()) || (this
						.getStatusRizab() != null
						&& castOther.getStatusRizab() != null && this
						.getStatusRizab().equals(castOther.getStatusRizab())))
				&& ((this.getNoBangunan() == castOther.getNoBangunan()) || (this
						.getNoBangunan() != null
						&& castOther.getNoBangunan() != null && this
						.getNoBangunan().equals(castOther.getNoBangunan())))
				&& ((this.getNoTingkat() == castOther.getNoTingkat()) || (this
						.getNoTingkat() != null
						&& castOther.getNoTingkat() != null && this
						.getNoTingkat().equals(castOther.getNoTingkat())))
				&& ((this.getNoPtk() == castOther.getNoPtk()) || (this
						.getNoPtk() != null
						&& castOther.getNoPtk() != null && this.getNoPtk()
						.equals(castOther.getNoPtk())))
				&& ((this.getStatusTanah() == castOther.getStatusTanah()) || (this
						.getStatusTanah() != null
						&& castOther.getStatusTanah() != null && this
						.getStatusTanah().equals(castOther.getStatusTanah())))
				&& ((this.getTarikhMula() == castOther.getTarikhMula()) || (this
						.getTarikhMula() != null
						&& castOther.getTarikhMula() != null && this
						.getTarikhMula().equals(castOther.getTarikhMula())))
				&& ((this.getTarikhLuput() == castOther.getTarikhLuput()) || (this
						.getTarikhLuput() != null
						&& castOther.getTarikhLuput() != null && this
						.getTarikhLuput().equals(castOther.getTarikhLuput())))
				&& ((this.getLuasBersamaan() == castOther.getLuasBersamaan()) || (this
						.getLuasBersamaan() != null
						&& castOther.getLuasBersamaan() != null && this
						.getLuasBersamaan()
						.equals(castOther.getLuasBersamaan())))
				&& ((this.getJenisTanah() == castOther.getJenisTanah()) || (this
						.getJenisTanah() != null
						&& castOther.getJenisTanah() != null && this
						.getJenisTanah().equals(castOther.getJenisTanah())))
				&& ((this.getFlagPtp() == castOther.getFlagPtp()) || (this
						.getFlagPtp() != null
						&& castOther.getFlagPtp() != null && this.getFlagPtp()
						.equals(castOther.getFlagPtp())))
				&& ((this.getIdKategori() == castOther.getIdKategori()) || (this
						.getIdKategori() != null
						&& castOther.getIdKategori() != null && this
						.getIdKategori().equals(castOther.getIdKategori())))
				&& ((this.getIdDaerah() == castOther.getIdDaerah()) || (this
						.getIdDaerah() != null
						&& castOther.getIdDaerah() != null && this
						.getIdDaerah().equals(castOther.getIdDaerah())))
				&& ((this.getIdNegeri() == castOther.getIdNegeri()) || (this
						.getIdNegeri() != null
						&& castOther.getIdNegeri() != null && this
						.getIdNegeri().equals(castOther.getIdNegeri())))
				&& ((this.getIdMukim() == castOther.getIdMukim()) || (this
						.getIdMukim() != null
						&& castOther.getIdMukim() != null && this.getIdMukim()
						.equals(castOther.getIdMukim())))
				&& ((this.getIdLot() == castOther.getIdLot()) || (this
						.getIdLot() != null
						&& castOther.getIdLot() != null && this.getIdLot()
						.equals(castOther.getIdLot())))
				&& ((this.getIdJenishakmilik() == castOther
						.getIdJenishakmilik()) || (this.getIdJenishakmilik() != null
						&& castOther.getIdJenishakmilik() != null && this
						.getIdJenishakmilik().equals(
								castOther.getIdJenishakmilik())))
				&& ((this.getIdRizab() == castOther.getIdRizab()) || (this
						.getIdRizab() != null
						&& castOther.getIdRizab() != null && this.getIdRizab()
						.equals(castOther.getIdRizab())))
				&& ((this.getIdMasuk() == castOther.getIdMasuk()) || (this
						.getIdMasuk() != null
						&& castOther.getIdMasuk() != null && this.getIdMasuk()
						.equals(castOther.getIdMasuk())))
				&& ((this.getTarikhMasuk() == castOther.getTarikhMasuk()) || (this
						.getTarikhMasuk() != null
						&& castOther.getTarikhMasuk() != null && this
						.getTarikhMasuk().equals(castOther.getTarikhMasuk())))
				&& ((this.getIdKemaskini() == castOther.getIdKemaskini()) || (this
						.getIdKemaskini() != null
						&& castOther.getIdKemaskini() != null && this
						.getIdKemaskini().equals(castOther.getIdKemaskini())))
				&& ((this.getTarikhKemaskini() == castOther
						.getTarikhKemaskini()) || (this.getTarikhKemaskini() != null
						&& castOther.getTarikhKemaskini() != null && this
						.getTarikhKemaskini().equals(
								castOther.getTarikhKemaskini())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdHtphakmilik() == null ? 0 : this.getIdHtphakmilik()
						.hashCode());
		result = 37
				* result
				+ (getTblhtppermohonan() == null ? 0 : this
						.getTblhtppermohonan().hashCode());
		result = 37 * result
				+ (getIdLuas() == null ? 0 : this.getIdLuas().hashCode());
		result = 37
				* result
				+ (getPeganganHakmilik() == null ? 0 : this
						.getPeganganHakmilik().hashCode());
		result = 37
				* result
				+ (getNoHakmilik() == null ? 0 : this.getNoHakmilik()
						.hashCode());
		result = 37 * result
				+ (getNoWarta() == null ? 0 : this.getNoWarta().hashCode());
		result = 37
				* result
				+ (getTarikhWarta() == null ? 0 : this.getTarikhWarta()
						.hashCode());
		result = 37 * result
				+ (getNoLot() == null ? 0 : this.getNoLot().hashCode());
		result = 37 * result
				+ (getLuas() == null ? 0 : this.getLuas().hashCode());
		result = 37 * result
				+ (getNoSyit() == null ? 0 : this.getNoSyit().hashCode());
		result = 37 * result
				+ (getNoPelan() == null ? 0 : this.getNoPelan().hashCode());
		result = 37 * result
				+ (getSyarat() == null ? 0 : this.getSyarat().hashCode());
		result = 37 * result
				+ (getSekatan() == null ? 0 : this.getSekatan().hashCode());
		result = 37 * result
				+ (getCukai() == null ? 0 : this.getCukai().hashCode());
		result = 37 * result
				+ (getFlagPelan() == null ? 0 : this.getFlagPelan().hashCode());
		result = 37 * result
				+ (getUlasan() == null ? 0 : this.getUlasan().hashCode());
		result = 37
				* result
				+ (getStatusSwasta() == null ? 0 : this.getStatusSwasta()
						.hashCode());
		result = 37
				* result
				+ (getTindakanLanjut() == null ? 0 : this.getTindakanLanjut()
						.hashCode());
		result = 37
				* result
				+ (getIdSubkategori() == null ? 0 : this.getIdSubkategori()
						.hashCode());
		result = 37 * result
				+ (getLokasi() == null ? 0 : this.getLokasi().hashCode());
		result = 37
				* result
				+ (getCukaiTerkini() == null ? 0 : this.getCukaiTerkini()
						.hashCode());
		result = 37
				* result
				+ (getStatusRizab() == null ? 0 : this.getStatusRizab()
						.hashCode());
		result = 37
				* result
				+ (getNoBangunan() == null ? 0 : this.getNoBangunan()
						.hashCode());
		result = 37 * result
				+ (getNoTingkat() == null ? 0 : this.getNoTingkat().hashCode());
		result = 37 * result
				+ (getNoPtk() == null ? 0 : this.getNoPtk().hashCode());
		result = 37
				* result
				+ (getStatusTanah() == null ? 0 : this.getStatusTanah()
						.hashCode());
		result = 37
				* result
				+ (getTarikhMula() == null ? 0 : this.getTarikhMula()
						.hashCode());
		result = 37
				* result
				+ (getTarikhLuput() == null ? 0 : this.getTarikhLuput()
						.hashCode());
		result = 37
				* result
				+ (getLuasBersamaan() == null ? 0 : this.getLuasBersamaan()
						.hashCode());
		result = 37
				* result
				+ (getJenisTanah() == null ? 0 : this.getJenisTanah()
						.hashCode());
		result = 37 * result
				+ (getFlagPtp() == null ? 0 : this.getFlagPtp().hashCode());
		result = 37
				* result
				+ (getIdKategori() == null ? 0 : this.getIdKategori()
						.hashCode());
		result = 37 * result
				+ (getIdDaerah() == null ? 0 : this.getIdDaerah().hashCode());
		result = 37 * result
				+ (getIdNegeri() == null ? 0 : this.getIdNegeri().hashCode());
		result = 37 * result
				+ (getIdMukim() == null ? 0 : this.getIdMukim().hashCode());
		result = 37 * result
				+ (getIdLot() == null ? 0 : this.getIdLot().hashCode());
		result = 37
				* result
				+ (getIdJenishakmilik() == null ? 0 : this.getIdJenishakmilik()
						.hashCode());
		result = 37 * result
				+ (getIdRizab() == null ? 0 : this.getIdRizab().hashCode());
		result = 37 * result
				+ (getIdMasuk() == null ? 0 : this.getIdMasuk().hashCode());
		result = 37
				* result
				+ (getTarikhMasuk() == null ? 0 : this.getTarikhMasuk()
						.hashCode());
		result = 37
				* result
				+ (getIdKemaskini() == null ? 0 : this.getIdKemaskini()
						.hashCode());
		result = 37
				* result
				+ (getTarikhKemaskini() == null ? 0 : this.getTarikhKemaskini()
						.hashCode());
		return result;
	}

}