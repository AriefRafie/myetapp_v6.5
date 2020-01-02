package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtphakmilikgadaianId entity provides the base persistence
 * definition of the TblhtphakmilikgadaianId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilikgadaianId implements
		java.io.Serializable {

	// Fields

	private Long idHakmilik;
	private String peganganhakmilik;
	private Tblhtppermohonan tblhtppermohonan;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMukim;
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
	private String statusPelan;
	private String ulasan;
	private String statusSwasta;
	private String tindakanLanjut;
	private Long idJenishakmilik;
	private Long idLot;
	private Long idKategori;
	private Long idSubkategori;
	private Long idLuas;
	private String lokasi;
	private Double cukaiTerkini;
	private String statusRizab;
	private String noBangunan;
	private String noTingkat;
	private String noPetak;
	private String statusTanah;
	private Date tarikhMula;
	private Date tarikhLuput;
	private Double luasBersamaan;
	private Long idJenistanah;
	private String statusPtp;
	private Long idRizab;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtphakmilikgadaianId() {
	}

	/** minimal constructor */
	public AbstractTblhtphakmilikgadaianId(Long idHakmilik,
			Tblhtppermohonan tblhtppermohonan, Long idNegeri, Long idDaerah,
			Long idMukim, Long idJenishakmilik, Long idLot, Long idKategori,
			Long idSubkategori, Long idLuas, Long idJenistanah, Long idRizab) {
		this.idHakmilik = idHakmilik;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMukim = idMukim;
		this.idJenishakmilik = idJenishakmilik;
		this.idLot = idLot;
		this.idKategori = idKategori;
		this.idSubkategori = idSubkategori;
		this.idLuas = idLuas;
		this.idJenistanah = idJenistanah;
		this.idRizab = idRizab;
	}

	/** full constructor */
	public AbstractTblhtphakmilikgadaianId(Long idHakmilik,
			String peganganhakmilik, Tblhtppermohonan tblhtppermohonan,
			Long idNegeri, Long idDaerah, Long idMukim, String noHakmilik,
			String noWarta, Date tarikhWarta, String noLot, String luas,
			String noSyit, String noPelan, String syarat, String sekatan,
			Double cukai, String statusPelan, String ulasan,
			String statusSwasta, String tindakanLanjut, Long idJenishakmilik,
			Long idLot, Long idKategori, Long idSubkategori, Long idLuas,
			String lokasi, Double cukaiTerkini, String statusRizab,
			String noBangunan, String noTingkat, String noPetak,
			String statusTanah, Date tarikhMula, Date tarikhLuput,
			Double luasBersamaan, Long idJenistanah, String statusPtp,
			Long idRizab, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idHakmilik = idHakmilik;
		this.peganganhakmilik = peganganhakmilik;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMukim = idMukim;
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
		this.statusPelan = statusPelan;
		this.ulasan = ulasan;
		this.statusSwasta = statusSwasta;
		this.tindakanLanjut = tindakanLanjut;
		this.idJenishakmilik = idJenishakmilik;
		this.idLot = idLot;
		this.idKategori = idKategori;
		this.idSubkategori = idSubkategori;
		this.idLuas = idLuas;
		this.lokasi = lokasi;
		this.cukaiTerkini = cukaiTerkini;
		this.statusRizab = statusRizab;
		this.noBangunan = noBangunan;
		this.noTingkat = noTingkat;
		this.noPetak = noPetak;
		this.statusTanah = statusTanah;
		this.tarikhMula = tarikhMula;
		this.tarikhLuput = tarikhLuput;
		this.luasBersamaan = luasBersamaan;
		this.idJenistanah = idJenistanah;
		this.statusPtp = statusPtp;
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

	public String getPeganganhakmilik() {
		return this.peganganhakmilik;
	}

	public void setPeganganhakmilik(String peganganhakmilik) {
		this.peganganhakmilik = peganganhakmilik;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Long getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(Long idMukim) {
		this.idMukim = idMukim;
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

	public String getStatusPelan() {
		return this.statusPelan;
	}

	public void setStatusPelan(String statusPelan) {
		this.statusPelan = statusPelan;
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

	public Long getIdJenishakmilik() {
		return this.idJenishakmilik;
	}

	public void setIdJenishakmilik(Long idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	public Long getIdLot() {
		return this.idLot;
	}

	public void setIdLot(Long idLot) {
		this.idLot = idLot;
	}

	public Long getIdKategori() {
		return this.idKategori;
	}

	public void setIdKategori(Long idKategori) {
		this.idKategori = idKategori;
	}

	public Long getIdSubkategori() {
		return this.idSubkategori;
	}

	public void setIdSubkategori(Long idSubkategori) {
		this.idSubkategori = idSubkategori;
	}

	public Long getIdLuas() {
		return this.idLuas;
	}

	public void setIdLuas(Long idLuas) {
		this.idLuas = idLuas;
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

	public String getNoPetak() {
		return this.noPetak;
	}

	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
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

	public String getStatusPtp() {
		return this.statusPtp;
	}

	public void setStatusPtp(String statusPtp) {
		this.statusPtp = statusPtp;
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
		if (!(other instanceof AbstractTblhtphakmilikgadaianId))
			return false;
		AbstractTblhtphakmilikgadaianId castOther = (AbstractTblhtphakmilikgadaianId) other;

		return ((this.getIdHakmilik() == castOther.getIdHakmilik()) || (this
				.getIdHakmilik() != null
				&& castOther.getIdHakmilik() != null && this.getIdHakmilik()
				.equals(castOther.getIdHakmilik())))
				&& ((this.getPeganganhakmilik() == castOther
						.getPeganganhakmilik()) || (this.getPeganganhakmilik() != null
						&& castOther.getPeganganhakmilik() != null && this
						.getPeganganhakmilik().equals(
								castOther.getPeganganhakmilik())))
				&& ((this.getTblhtppermohonan() == castOther
						.getTblhtppermohonan()) || (this.getTblhtppermohonan() != null
						&& castOther.getTblhtppermohonan() != null && this
						.getTblhtppermohonan().equals(
								castOther.getTblhtppermohonan())))
				&& ((this.getIdNegeri() == castOther.getIdNegeri()) || (this
						.getIdNegeri() != null
						&& castOther.getIdNegeri() != null && this
						.getIdNegeri().equals(castOther.getIdNegeri())))
				&& ((this.getIdDaerah() == castOther.getIdDaerah()) || (this
						.getIdDaerah() != null
						&& castOther.getIdDaerah() != null && this
						.getIdDaerah().equals(castOther.getIdDaerah())))
				&& ((this.getIdMukim() == castOther.getIdMukim()) || (this
						.getIdMukim() != null
						&& castOther.getIdMukim() != null && this.getIdMukim()
						.equals(castOther.getIdMukim())))
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
				&& ((this.getStatusPelan() == castOther.getStatusPelan()) || (this
						.getStatusPelan() != null
						&& castOther.getStatusPelan() != null && this
						.getStatusPelan().equals(castOther.getStatusPelan())))
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
				&& ((this.getIdJenishakmilik() == castOther
						.getIdJenishakmilik()) || (this.getIdJenishakmilik() != null
						&& castOther.getIdJenishakmilik() != null && this
						.getIdJenishakmilik().equals(
								castOther.getIdJenishakmilik())))
				&& ((this.getIdLot() == castOther.getIdLot()) || (this
						.getIdLot() != null
						&& castOther.getIdLot() != null && this.getIdLot()
						.equals(castOther.getIdLot())))
				&& ((this.getIdKategori() == castOther.getIdKategori()) || (this
						.getIdKategori() != null
						&& castOther.getIdKategori() != null && this
						.getIdKategori().equals(castOther.getIdKategori())))
				&& ((this.getIdSubkategori() == castOther.getIdSubkategori()) || (this
						.getIdSubkategori() != null
						&& castOther.getIdSubkategori() != null && this
						.getIdSubkategori()
						.equals(castOther.getIdSubkategori())))
				&& ((this.getIdLuas() == castOther.getIdLuas()) || (this
						.getIdLuas() != null
						&& castOther.getIdLuas() != null && this.getIdLuas()
						.equals(castOther.getIdLuas())))
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
				&& ((this.getNoPetak() == castOther.getNoPetak()) || (this
						.getNoPetak() != null
						&& castOther.getNoPetak() != null && this.getNoPetak()
						.equals(castOther.getNoPetak())))
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
				&& ((this.getIdJenistanah() == castOther.getIdJenistanah()) || (this
						.getIdJenistanah() != null
						&& castOther.getIdJenistanah() != null && this
						.getIdJenistanah().equals(castOther.getIdJenistanah())))
				&& ((this.getStatusPtp() == castOther.getStatusPtp()) || (this
						.getStatusPtp() != null
						&& castOther.getStatusPtp() != null && this
						.getStatusPtp().equals(castOther.getStatusPtp())))
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
				+ (getIdHakmilik() == null ? 0 : this.getIdHakmilik()
						.hashCode());
		result = 37
				* result
				+ (getPeganganhakmilik() == null ? 0 : this
						.getPeganganhakmilik().hashCode());
		result = 37
				* result
				+ (getTblhtppermohonan() == null ? 0 : this
						.getTblhtppermohonan().hashCode());
		result = 37 * result
				+ (getIdNegeri() == null ? 0 : this.getIdNegeri().hashCode());
		result = 37 * result
				+ (getIdDaerah() == null ? 0 : this.getIdDaerah().hashCode());
		result = 37 * result
				+ (getIdMukim() == null ? 0 : this.getIdMukim().hashCode());
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
		result = 37
				* result
				+ (getStatusPelan() == null ? 0 : this.getStatusPelan()
						.hashCode());
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
				+ (getIdJenishakmilik() == null ? 0 : this.getIdJenishakmilik()
						.hashCode());
		result = 37 * result
				+ (getIdLot() == null ? 0 : this.getIdLot().hashCode());
		result = 37
				* result
				+ (getIdKategori() == null ? 0 : this.getIdKategori()
						.hashCode());
		result = 37
				* result
				+ (getIdSubkategori() == null ? 0 : this.getIdSubkategori()
						.hashCode());
		result = 37 * result
				+ (getIdLuas() == null ? 0 : this.getIdLuas().hashCode());
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
				+ (getNoPetak() == null ? 0 : this.getNoPetak().hashCode());
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
				+ (getIdJenistanah() == null ? 0 : this.getIdJenistanah()
						.hashCode());
		result = 37 * result
				+ (getStatusPtp() == null ? 0 : this.getStatusPtp().hashCode());
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