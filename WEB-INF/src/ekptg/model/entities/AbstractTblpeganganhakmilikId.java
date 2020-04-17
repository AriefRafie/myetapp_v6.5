package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpeganganhakmilikId entity provides the base persistence definition
 * of the TblpeganganhakmilikId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpeganganhakmilikId implements
		java.io.Serializable {

	// Fields

	private Long idHakmilik;
	private String noHakmilik;
	private String noBangunan;
	private String noTingkat;
	private String noPetak;
	private Date tarikhTerima;
	private String noLot;
	private String noWarta;
	private Date tarikhDaftar;
	private String noFailHakmilik;
	private String tarafHakmilik;
	private String statusRizab;
	private String tempoh;
	private Date tarikhLuput;
	private String noPermintaanUkur;
	private Double luas;
	private String noPelan;
	private String noSyit;
	private String lokasi;
	private Double cukai;
	private String hakmilikAsal;
	private String hakmilikBerikut;
	private String statusPajakan;
	private Date tarikhMohonPajakan;
	private String statusSewa;
	private Date tarikhMohonSewa;
	private String statusSwasta;
	private Date tarikhMohonSwasta;
	private String statusPelepasan;
	private Date tarikhMohonPelepasan;
	private String caraDapat;
	private String idSubkategori;
	private Double cukaiTerkini;
	private String kawasanRizab;
	private Long noRizab;
	private Date tarikhRizab;
	private String syarat;
	private String sekatan;
	private String idPermohonan;
	private Date tarikhBukaFail;
	private String kegunaanTapak;
	private String kegunaanTanah;
	private String statusSah;
	private String kodLuasLama;
	private String luasLama;
	private String statusPelarasan;
	private String noFailJofa;
	private String noFailKjp;
	private String noFailAgensi;
	private String noFailPtg;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private String statusGeran;
	private String statusTukarGanti;
	private String idKategori;
	private Long idDaerah;
	private Long idNegeri;
	private String idMukim;
	private String idLot;
	private String idJenishakmilik;
	private String idRizab;
	private String idLuas;

	// Constructors

	/** default constructor */
	public AbstractTblpeganganhakmilikId() {
	}

	/** minimal constructor */
	public AbstractTblpeganganhakmilikId(Long idHakmilik, String idSubkategori,
			String idPermohonan, String idKategori, Long idDaerah,
			Long idNegeri, String idMukim, String idLot,
			String idJenishakmilik, String idRizab, String idLuas) {
		this.idHakmilik = idHakmilik;
		this.idSubkategori = idSubkategori;
		this.idPermohonan = idPermohonan;
		this.idKategori = idKategori;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.idMukim = idMukim;
		this.idLot = idLot;
		this.idJenishakmilik = idJenishakmilik;
		this.idRizab = idRizab;
		this.idLuas = idLuas;
	}

	/** full constructor */
	public AbstractTblpeganganhakmilikId(Long idHakmilik, String noHakmilik,
			String noBangunan, String noTingkat, String noPetak,
			Date tarikhTerima, String noLot, String noWarta, Date tarikhDaftar,
			String noFailHakmilik, String tarafHakmilik, String statusRizab,
			String tempoh, Date tarikhLuput, String noPermintaanUkur,
			Double luas, String noPelan, String noSyit, String lokasi,
			Double cukai, String hakmilikAsal, String hakmilikBerikut,
			String statusPajakan, Date tarikhMohonPajakan, String statusSewa,
			Date tarikhMohonSewa, String statusSwasta, Date tarikhMohonSwasta,
			String statusPelepasan, Date tarikhMohonPelepasan,
			String caraDapat, String idSubkategori, Double cukaiTerkini,
			String kawasanRizab, Long noRizab, Date tarikhRizab, String syarat,
			String sekatan, String idPermohonan, Date tarikhBukaFail,
			String kegunaanTapak, String kegunaanTanah, String statusSah,
			String kodLuasLama, String luasLama, String statusPelarasan,
			String noFailJofa, String noFailKjp, String noFailAgensi,
			String noFailPtg, Long idKemaskini, Date tarikhKemaskini,
			String statusGeran, String statusTukarGanti, String idKategori,
			Long idDaerah, Long idNegeri, String idMukim, String idLot,
			String idJenishakmilik, String idRizab, String idLuas) {
		this.idHakmilik = idHakmilik;
		this.noHakmilik = noHakmilik;
		this.noBangunan = noBangunan;
		this.noTingkat = noTingkat;
		this.noPetak = noPetak;
		this.tarikhTerima = tarikhTerima;
		this.noLot = noLot;
		this.noWarta = noWarta;
		this.tarikhDaftar = tarikhDaftar;
		this.noFailHakmilik = noFailHakmilik;
		this.tarafHakmilik = tarafHakmilik;
		this.statusRizab = statusRizab;
		this.tempoh = tempoh;
		this.tarikhLuput = tarikhLuput;
		this.noPermintaanUkur = noPermintaanUkur;
		this.luas = luas;
		this.noPelan = noPelan;
		this.noSyit = noSyit;
		this.lokasi = lokasi;
		this.cukai = cukai;
		this.hakmilikAsal = hakmilikAsal;
		this.hakmilikBerikut = hakmilikBerikut;
		this.statusPajakan = statusPajakan;
		this.tarikhMohonPajakan = tarikhMohonPajakan;
		this.statusSewa = statusSewa;
		this.tarikhMohonSewa = tarikhMohonSewa;
		this.statusSwasta = statusSwasta;
		this.tarikhMohonSwasta = tarikhMohonSwasta;
		this.statusPelepasan = statusPelepasan;
		this.tarikhMohonPelepasan = tarikhMohonPelepasan;
		this.caraDapat = caraDapat;
		this.idSubkategori = idSubkategori;
		this.cukaiTerkini = cukaiTerkini;
		this.kawasanRizab = kawasanRizab;
		this.noRizab = noRizab;
		this.tarikhRizab = tarikhRizab;
		this.syarat = syarat;
		this.sekatan = sekatan;
		this.idPermohonan = idPermohonan;
		this.tarikhBukaFail = tarikhBukaFail;
		this.kegunaanTapak = kegunaanTapak;
		this.kegunaanTanah = kegunaanTanah;
		this.statusSah = statusSah;
		this.kodLuasLama = kodLuasLama;
		this.luasLama = luasLama;
		this.statusPelarasan = statusPelarasan;
		this.noFailJofa = noFailJofa;
		this.noFailKjp = noFailKjp;
		this.noFailAgensi = noFailAgensi;
		this.noFailPtg = noFailPtg;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.statusGeran = statusGeran;
		this.statusTukarGanti = statusTukarGanti;
		this.idKategori = idKategori;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.idMukim = idMukim;
		this.idLot = idLot;
		this.idJenishakmilik = idJenishakmilik;
		this.idRizab = idRizab;
		this.idLuas = idLuas;
	}

	// Property accessors

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public String getNoHakmilik() {
		return this.noHakmilik;
	}

	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
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

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public String getNoLot() {
		return this.noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public String getNoWarta() {
		return this.noWarta;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public String getNoFailHakmilik() {
		return this.noFailHakmilik;
	}

	public void setNoFailHakmilik(String noFailHakmilik) {
		this.noFailHakmilik = noFailHakmilik;
	}

	public String getTarafHakmilik() {
		return this.tarafHakmilik;
	}

	public void setTarafHakmilik(String tarafHakmilik) {
		this.tarafHakmilik = tarafHakmilik;
	}

	public String getStatusRizab() {
		return this.statusRizab;
	}

	public void setStatusRizab(String statusRizab) {
		this.statusRizab = statusRizab;
	}

	public String getTempoh() {
		return this.tempoh;
	}

	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
	}

	public Date getTarikhLuput() {
		return this.tarikhLuput;
	}

	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}

	public String getNoPermintaanUkur() {
		return this.noPermintaanUkur;
	}

	public void setNoPermintaanUkur(String noPermintaanUkur) {
		this.noPermintaanUkur = noPermintaanUkur;
	}

	public Double getLuas() {
		return this.luas;
	}

	public void setLuas(Double luas) {
		this.luas = luas;
	}

	public String getNoPelan() {
		return this.noPelan;
	}

	public void setNoPelan(String noPelan) {
		this.noPelan = noPelan;
	}

	public String getNoSyit() {
		return this.noSyit;
	}

	public void setNoSyit(String noSyit) {
		this.noSyit = noSyit;
	}

	public String getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public Double getCukai() {
		return this.cukai;
	}

	public void setCukai(Double cukai) {
		this.cukai = cukai;
	}

	public String getHakmilikAsal() {
		return this.hakmilikAsal;
	}

	public void setHakmilikAsal(String hakmilikAsal) {
		this.hakmilikAsal = hakmilikAsal;
	}

	public String getHakmilikBerikut() {
		return this.hakmilikBerikut;
	}

	public void setHakmilikBerikut(String hakmilikBerikut) {
		this.hakmilikBerikut = hakmilikBerikut;
	}

	public String getStatusPajakan() {
		return this.statusPajakan;
	}

	public void setStatusPajakan(String statusPajakan) {
		this.statusPajakan = statusPajakan;
	}

	public Date getTarikhMohonPajakan() {
		return this.tarikhMohonPajakan;
	}

	public void setTarikhMohonPajakan(Date tarikhMohonPajakan) {
		this.tarikhMohonPajakan = tarikhMohonPajakan;
	}

	public String getStatusSewa() {
		return this.statusSewa;
	}

	public void setStatusSewa(String statusSewa) {
		this.statusSewa = statusSewa;
	}

	public Date getTarikhMohonSewa() {
		return this.tarikhMohonSewa;
	}

	public void setTarikhMohonSewa(Date tarikhMohonSewa) {
		this.tarikhMohonSewa = tarikhMohonSewa;
	}

	public String getStatusSwasta() {
		return this.statusSwasta;
	}

	public void setStatusSwasta(String statusSwasta) {
		this.statusSwasta = statusSwasta;
	}

	public Date getTarikhMohonSwasta() {
		return this.tarikhMohonSwasta;
	}

	public void setTarikhMohonSwasta(Date tarikhMohonSwasta) {
		this.tarikhMohonSwasta = tarikhMohonSwasta;
	}

	public String getStatusPelepasan() {
		return this.statusPelepasan;
	}

	public void setStatusPelepasan(String statusPelepasan) {
		this.statusPelepasan = statusPelepasan;
	}

	public Date getTarikhMohonPelepasan() {
		return this.tarikhMohonPelepasan;
	}

	public void setTarikhMohonPelepasan(Date tarikhMohonPelepasan) {
		this.tarikhMohonPelepasan = tarikhMohonPelepasan;
	}

	public String getCaraDapat() {
		return this.caraDapat;
	}

	public void setCaraDapat(String caraDapat) {
		this.caraDapat = caraDapat;
	}

	public String getIdSubkategori() {
		return this.idSubkategori;
	}

	public void setIdSubkategori(String idSubkategori) {
		this.idSubkategori = idSubkategori;
	}

	public Double getCukaiTerkini() {
		return this.cukaiTerkini;
	}

	public void setCukaiTerkini(Double cukaiTerkini) {
		this.cukaiTerkini = cukaiTerkini;
	}

	public String getKawasanRizab() {
		return this.kawasanRizab;
	}

	public void setKawasanRizab(String kawasanRizab) {
		this.kawasanRizab = kawasanRizab;
	}

	public Long getNoRizab() {
		return this.noRizab;
	}

	public void setNoRizab(Long noRizab) {
		this.noRizab = noRizab;
	}

	public Date getTarikhRizab() {
		return this.tarikhRizab;
	}

	public void setTarikhRizab(Date tarikhRizab) {
		this.tarikhRizab = tarikhRizab;
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

	public String getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(String idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Date getTarikhBukaFail() {
		return this.tarikhBukaFail;
	}

	public void setTarikhBukaFail(Date tarikhBukaFail) {
		this.tarikhBukaFail = tarikhBukaFail;
	}

	public String getKegunaanTapak() {
		return this.kegunaanTapak;
	}

	public void setKegunaanTapak(String kegunaanTapak) {
		this.kegunaanTapak = kegunaanTapak;
	}

	public String getKegunaanTanah() {
		return this.kegunaanTanah;
	}

	public void setKegunaanTanah(String kegunaanTanah) {
		this.kegunaanTanah = kegunaanTanah;
	}

	public String getStatusSah() {
		return this.statusSah;
	}

	public void setStatusSah(String statusSah) {
		this.statusSah = statusSah;
	}

	public String getKodLuasLama() {
		return this.kodLuasLama;
	}

	public void setKodLuasLama(String kodLuasLama) {
		this.kodLuasLama = kodLuasLama;
	}

	public String getLuasLama() {
		return this.luasLama;
	}

	public void setLuasLama(String luasLama) {
		this.luasLama = luasLama;
	}

	public String getStatusPelarasan() {
		return this.statusPelarasan;
	}

	public void setStatusPelarasan(String statusPelarasan) {
		this.statusPelarasan = statusPelarasan;
	}

	public String getNoFailJofa() {
		return this.noFailJofa;
	}

	public void setNoFailJofa(String noFailJofa) {
		this.noFailJofa = noFailJofa;
	}

	public String getNoFailKjp() {
		return this.noFailKjp;
	}

	public void setNoFailKjp(String noFailKjp) {
		this.noFailKjp = noFailKjp;
	}

	public String getNoFailAgensi() {
		return this.noFailAgensi;
	}

	public void setNoFailAgensi(String noFailAgensi) {
		this.noFailAgensi = noFailAgensi;
	}

	public String getNoFailPtg() {
		return this.noFailPtg;
	}

	public void setNoFailPtg(String noFailPtg) {
		this.noFailPtg = noFailPtg;
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

	public String getStatusGeran() {
		return this.statusGeran;
	}

	public void setStatusGeran(String statusGeran) {
		this.statusGeran = statusGeran;
	}

	public String getStatusTukarGanti() {
		return this.statusTukarGanti;
	}

	public void setStatusTukarGanti(String statusTukarGanti) {
		this.statusTukarGanti = statusTukarGanti;
	}

	public String getIdKategori() {
		return this.idKategori;
	}

	public void setIdKategori(String idKategori) {
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

	public String getIdLot() {
		return this.idLot;
	}

	public void setIdLot(String idLot) {
		this.idLot = idLot;
	}

	public String getIdJenishakmilik() {
		return this.idJenishakmilik;
	}

	public void setIdJenishakmilik(String idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	public String getIdRizab() {
		return this.idRizab;
	}

	public void setIdRizab(String idRizab) {
		this.idRizab = idRizab;
	}

	public String getIdLuas() {
		return this.idLuas;
	}

	public void setIdLuas(String idLuas) {
		this.idLuas = idLuas;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblpeganganhakmilikId))
			return false;
		AbstractTblpeganganhakmilikId castOther = (AbstractTblpeganganhakmilikId) other;

		return ((this.getIdHakmilik() == castOther.getIdHakmilik()) || (this
				.getIdHakmilik() != null
				&& castOther.getIdHakmilik() != null && this.getIdHakmilik()
				.equals(castOther.getIdHakmilik())))
				&& ((this.getNoHakmilik() == castOther.getNoHakmilik()) || (this
						.getNoHakmilik() != null
						&& castOther.getNoHakmilik() != null && this
						.getNoHakmilik().equals(castOther.getNoHakmilik())))
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
				&& ((this.getTarikhTerima() == castOther.getTarikhTerima()) || (this
						.getTarikhTerima() != null
						&& castOther.getTarikhTerima() != null && this
						.getTarikhTerima().equals(castOther.getTarikhTerima())))
				&& ((this.getNoLot() == castOther.getNoLot()) || (this
						.getNoLot() != null
						&& castOther.getNoLot() != null && this.getNoLot()
						.equals(castOther.getNoLot())))
				&& ((this.getNoWarta() == castOther.getNoWarta()) || (this
						.getNoWarta() != null
						&& castOther.getNoWarta() != null && this.getNoWarta()
						.equals(castOther.getNoWarta())))
				&& ((this.getTarikhDaftar() == castOther.getTarikhDaftar()) || (this
						.getTarikhDaftar() != null
						&& castOther.getTarikhDaftar() != null && this
						.getTarikhDaftar().equals(castOther.getTarikhDaftar())))
				&& ((this.getNoFailHakmilik() == castOther.getNoFailHakmilik()) || (this
						.getNoFailHakmilik() != null
						&& castOther.getNoFailHakmilik() != null && this
						.getNoFailHakmilik().equals(
								castOther.getNoFailHakmilik())))
				&& ((this.getTarafHakmilik() == castOther.getTarafHakmilik()) || (this
						.getTarafHakmilik() != null
						&& castOther.getTarafHakmilik() != null && this
						.getTarafHakmilik()
						.equals(castOther.getTarafHakmilik())))
				&& ((this.getStatusRizab() == castOther.getStatusRizab()) || (this
						.getStatusRizab() != null
						&& castOther.getStatusRizab() != null && this
						.getStatusRizab().equals(castOther.getStatusRizab())))
				&& ((this.getTempoh() == castOther.getTempoh()) || (this
						.getTempoh() != null
						&& castOther.getTempoh() != null && this.getTempoh()
						.equals(castOther.getTempoh())))
				&& ((this.getTarikhLuput() == castOther.getTarikhLuput()) || (this
						.getTarikhLuput() != null
						&& castOther.getTarikhLuput() != null && this
						.getTarikhLuput().equals(castOther.getTarikhLuput())))
				&& ((this.getNoPermintaanUkur() == castOther
						.getNoPermintaanUkur()) || (this.getNoPermintaanUkur() != null
						&& castOther.getNoPermintaanUkur() != null && this
						.getNoPermintaanUkur().equals(
								castOther.getNoPermintaanUkur())))
				&& ((this.getLuas() == castOther.getLuas()) || (this.getLuas() != null
						&& castOther.getLuas() != null && this.getLuas()
						.equals(castOther.getLuas())))
				&& ((this.getNoPelan() == castOther.getNoPelan()) || (this
						.getNoPelan() != null
						&& castOther.getNoPelan() != null && this.getNoPelan()
						.equals(castOther.getNoPelan())))
				&& ((this.getNoSyit() == castOther.getNoSyit()) || (this
						.getNoSyit() != null
						&& castOther.getNoSyit() != null && this.getNoSyit()
						.equals(castOther.getNoSyit())))
				&& ((this.getLokasi() == castOther.getLokasi()) || (this
						.getLokasi() != null
						&& castOther.getLokasi() != null && this.getLokasi()
						.equals(castOther.getLokasi())))
				&& ((this.getCukai() == castOther.getCukai()) || (this
						.getCukai() != null
						&& castOther.getCukai() != null && this.getCukai()
						.equals(castOther.getCukai())))
				&& ((this.getHakmilikAsal() == castOther.getHakmilikAsal()) || (this
						.getHakmilikAsal() != null
						&& castOther.getHakmilikAsal() != null && this
						.getHakmilikAsal().equals(castOther.getHakmilikAsal())))
				&& ((this.getHakmilikBerikut() == castOther
						.getHakmilikBerikut()) || (this.getHakmilikBerikut() != null
						&& castOther.getHakmilikBerikut() != null && this
						.getHakmilikBerikut().equals(
								castOther.getHakmilikBerikut())))
				&& ((this.getStatusPajakan() == castOther.getStatusPajakan()) || (this
						.getStatusPajakan() != null
						&& castOther.getStatusPajakan() != null && this
						.getStatusPajakan()
						.equals(castOther.getStatusPajakan())))
				&& ((this.getTarikhMohonPajakan() == castOther
						.getTarikhMohonPajakan()) || (this
						.getTarikhMohonPajakan() != null
						&& castOther.getTarikhMohonPajakan() != null && this
						.getTarikhMohonPajakan().equals(
								castOther.getTarikhMohonPajakan())))
				&& ((this.getStatusSewa() == castOther.getStatusSewa()) || (this
						.getStatusSewa() != null
						&& castOther.getStatusSewa() != null && this
						.getStatusSewa().equals(castOther.getStatusSewa())))
				&& ((this.getTarikhMohonSewa() == castOther
						.getTarikhMohonSewa()) || (this.getTarikhMohonSewa() != null
						&& castOther.getTarikhMohonSewa() != null && this
						.getTarikhMohonSewa().equals(
								castOther.getTarikhMohonSewa())))
				&& ((this.getStatusSwasta() == castOther.getStatusSwasta()) || (this
						.getStatusSwasta() != null
						&& castOther.getStatusSwasta() != null && this
						.getStatusSwasta().equals(castOther.getStatusSwasta())))
				&& ((this.getTarikhMohonSwasta() == castOther
						.getTarikhMohonSwasta()) || (this
						.getTarikhMohonSwasta() != null
						&& castOther.getTarikhMohonSwasta() != null && this
						.getTarikhMohonSwasta().equals(
								castOther.getTarikhMohonSwasta())))
				&& ((this.getStatusPelepasan() == castOther
						.getStatusPelepasan()) || (this.getStatusPelepasan() != null
						&& castOther.getStatusPelepasan() != null && this
						.getStatusPelepasan().equals(
								castOther.getStatusPelepasan())))
				&& ((this.getTarikhMohonPelepasan() == castOther
						.getTarikhMohonPelepasan()) || (this
						.getTarikhMohonPelepasan() != null
						&& castOther.getTarikhMohonPelepasan() != null && this
						.getTarikhMohonPelepasan().equals(
								castOther.getTarikhMohonPelepasan())))
				&& ((this.getCaraDapat() == castOther.getCaraDapat()) || (this
						.getCaraDapat() != null
						&& castOther.getCaraDapat() != null && this
						.getCaraDapat().equals(castOther.getCaraDapat())))
				&& ((this.getIdSubkategori() == castOther.getIdSubkategori()) || (this
						.getIdSubkategori() != null
						&& castOther.getIdSubkategori() != null && this
						.getIdSubkategori()
						.equals(castOther.getIdSubkategori())))
				&& ((this.getCukaiTerkini() == castOther.getCukaiTerkini()) || (this
						.getCukaiTerkini() != null
						&& castOther.getCukaiTerkini() != null && this
						.getCukaiTerkini().equals(castOther.getCukaiTerkini())))
				&& ((this.getKawasanRizab() == castOther.getKawasanRizab()) || (this
						.getKawasanRizab() != null
						&& castOther.getKawasanRizab() != null && this
						.getKawasanRizab().equals(castOther.getKawasanRizab())))
				&& ((this.getNoRizab() == castOther.getNoRizab()) || (this
						.getNoRizab() != null
						&& castOther.getNoRizab() != null && this.getNoRizab()
						.equals(castOther.getNoRizab())))
				&& ((this.getTarikhRizab() == castOther.getTarikhRizab()) || (this
						.getTarikhRizab() != null
						&& castOther.getTarikhRizab() != null && this
						.getTarikhRizab().equals(castOther.getTarikhRizab())))
				&& ((this.getSyarat() == castOther.getSyarat()) || (this
						.getSyarat() != null
						&& castOther.getSyarat() != null && this.getSyarat()
						.equals(castOther.getSyarat())))
				&& ((this.getSekatan() == castOther.getSekatan()) || (this
						.getSekatan() != null
						&& castOther.getSekatan() != null && this.getSekatan()
						.equals(castOther.getSekatan())))
				&& ((this.getIdPermohonan() == castOther.getIdPermohonan()) || (this
						.getIdPermohonan() != null
						&& castOther.getIdPermohonan() != null && this
						.getIdPermohonan().equals(castOther.getIdPermohonan())))
				&& ((this.getTarikhBukaFail() == castOther.getTarikhBukaFail()) || (this
						.getTarikhBukaFail() != null
						&& castOther.getTarikhBukaFail() != null && this
						.getTarikhBukaFail().equals(
								castOther.getTarikhBukaFail())))
				&& ((this.getKegunaanTapak() == castOther.getKegunaanTapak()) || (this
						.getKegunaanTapak() != null
						&& castOther.getKegunaanTapak() != null && this
						.getKegunaanTapak()
						.equals(castOther.getKegunaanTapak())))
				&& ((this.getKegunaanTanah() == castOther.getKegunaanTanah()) || (this
						.getKegunaanTanah() != null
						&& castOther.getKegunaanTanah() != null && this
						.getKegunaanTanah()
						.equals(castOther.getKegunaanTanah())))
				&& ((this.getStatusSah() == castOther.getStatusSah()) || (this
						.getStatusSah() != null
						&& castOther.getStatusSah() != null && this
						.getStatusSah().equals(castOther.getStatusSah())))
				&& ((this.getKodLuasLama() == castOther.getKodLuasLama()) || (this
						.getKodLuasLama() != null
						&& castOther.getKodLuasLama() != null && this
						.getKodLuasLama().equals(castOther.getKodLuasLama())))
				&& ((this.getLuasLama() == castOther.getLuasLama()) || (this
						.getLuasLama() != null
						&& castOther.getLuasLama() != null && this
						.getLuasLama().equals(castOther.getLuasLama())))
				&& ((this.getStatusPelarasan() == castOther
						.getStatusPelarasan()) || (this.getStatusPelarasan() != null
						&& castOther.getStatusPelarasan() != null && this
						.getStatusPelarasan().equals(
								castOther.getStatusPelarasan())))
				&& ((this.getNoFailJofa() == castOther.getNoFailJofa()) || (this
						.getNoFailJofa() != null
						&& castOther.getNoFailJofa() != null && this
						.getNoFailJofa().equals(castOther.getNoFailJofa())))
				&& ((this.getNoFailKjp() == castOther.getNoFailKjp()) || (this
						.getNoFailKjp() != null
						&& castOther.getNoFailKjp() != null && this
						.getNoFailKjp().equals(castOther.getNoFailKjp())))
				&& ((this.getNoFailAgensi() == castOther.getNoFailAgensi()) || (this
						.getNoFailAgensi() != null
						&& castOther.getNoFailAgensi() != null && this
						.getNoFailAgensi().equals(castOther.getNoFailAgensi())))
				&& ((this.getNoFailPtg() == castOther.getNoFailPtg()) || (this
						.getNoFailPtg() != null
						&& castOther.getNoFailPtg() != null && this
						.getNoFailPtg().equals(castOther.getNoFailPtg())))
				&& ((this.getIdKemaskini() == castOther.getIdKemaskini()) || (this
						.getIdKemaskini() != null
						&& castOther.getIdKemaskini() != null && this
						.getIdKemaskini().equals(castOther.getIdKemaskini())))
				&& ((this.getTarikhKemaskini() == castOther
						.getTarikhKemaskini()) || (this.getTarikhKemaskini() != null
						&& castOther.getTarikhKemaskini() != null && this
						.getTarikhKemaskini().equals(
								castOther.getTarikhKemaskini())))
				&& ((this.getStatusGeran() == castOther.getStatusGeran()) || (this
						.getStatusGeran() != null
						&& castOther.getStatusGeran() != null && this
						.getStatusGeran().equals(castOther.getStatusGeran())))
				&& ((this.getStatusTukarGanti() == castOther
						.getStatusTukarGanti()) || (this.getStatusTukarGanti() != null
						&& castOther.getStatusTukarGanti() != null && this
						.getStatusTukarGanti().equals(
								castOther.getStatusTukarGanti())))
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
				&& ((this.getIdLuas() == castOther.getIdLuas()) || (this
						.getIdLuas() != null
						&& castOther.getIdLuas() != null && this.getIdLuas()
						.equals(castOther.getIdLuas())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdHakmilik() == null ? 0 : this.getIdHakmilik()
						.hashCode());
		result = 37
				* result
				+ (getNoHakmilik() == null ? 0 : this.getNoHakmilik()
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
				+ (getTarikhTerima() == null ? 0 : this.getTarikhTerima()
						.hashCode());
		result = 37 * result
				+ (getNoLot() == null ? 0 : this.getNoLot().hashCode());
		result = 37 * result
				+ (getNoWarta() == null ? 0 : this.getNoWarta().hashCode());
		result = 37
				* result
				+ (getTarikhDaftar() == null ? 0 : this.getTarikhDaftar()
						.hashCode());
		result = 37
				* result
				+ (getNoFailHakmilik() == null ? 0 : this.getNoFailHakmilik()
						.hashCode());
		result = 37
				* result
				+ (getTarafHakmilik() == null ? 0 : this.getTarafHakmilik()
						.hashCode());
		result = 37
				* result
				+ (getStatusRizab() == null ? 0 : this.getStatusRizab()
						.hashCode());
		result = 37 * result
				+ (getTempoh() == null ? 0 : this.getTempoh().hashCode());
		result = 37
				* result
				+ (getTarikhLuput() == null ? 0 : this.getTarikhLuput()
						.hashCode());
		result = 37
				* result
				+ (getNoPermintaanUkur() == null ? 0 : this
						.getNoPermintaanUkur().hashCode());
		result = 37 * result
				+ (getLuas() == null ? 0 : this.getLuas().hashCode());
		result = 37 * result
				+ (getNoPelan() == null ? 0 : this.getNoPelan().hashCode());
		result = 37 * result
				+ (getNoSyit() == null ? 0 : this.getNoSyit().hashCode());
		result = 37 * result
				+ (getLokasi() == null ? 0 : this.getLokasi().hashCode());
		result = 37 * result
				+ (getCukai() == null ? 0 : this.getCukai().hashCode());
		result = 37
				* result
				+ (getHakmilikAsal() == null ? 0 : this.getHakmilikAsal()
						.hashCode());
		result = 37
				* result
				+ (getHakmilikBerikut() == null ? 0 : this.getHakmilikBerikut()
						.hashCode());
		result = 37
				* result
				+ (getStatusPajakan() == null ? 0 : this.getStatusPajakan()
						.hashCode());
		result = 37
				* result
				+ (getTarikhMohonPajakan() == null ? 0 : this
						.getTarikhMohonPajakan().hashCode());
		result = 37
				* result
				+ (getStatusSewa() == null ? 0 : this.getStatusSewa()
						.hashCode());
		result = 37
				* result
				+ (getTarikhMohonSewa() == null ? 0 : this.getTarikhMohonSewa()
						.hashCode());
		result = 37
				* result
				+ (getStatusSwasta() == null ? 0 : this.getStatusSwasta()
						.hashCode());
		result = 37
				* result
				+ (getTarikhMohonSwasta() == null ? 0 : this
						.getTarikhMohonSwasta().hashCode());
		result = 37
				* result
				+ (getStatusPelepasan() == null ? 0 : this.getStatusPelepasan()
						.hashCode());
		result = 37
				* result
				+ (getTarikhMohonPelepasan() == null ? 0 : this
						.getTarikhMohonPelepasan().hashCode());
		result = 37 * result
				+ (getCaraDapat() == null ? 0 : this.getCaraDapat().hashCode());
		result = 37
				* result
				+ (getIdSubkategori() == null ? 0 : this.getIdSubkategori()
						.hashCode());
		result = 37
				* result
				+ (getCukaiTerkini() == null ? 0 : this.getCukaiTerkini()
						.hashCode());
		result = 37
				* result
				+ (getKawasanRizab() == null ? 0 : this.getKawasanRizab()
						.hashCode());
		result = 37 * result
				+ (getNoRizab() == null ? 0 : this.getNoRizab().hashCode());
		result = 37
				* result
				+ (getTarikhRizab() == null ? 0 : this.getTarikhRizab()
						.hashCode());
		result = 37 * result
				+ (getSyarat() == null ? 0 : this.getSyarat().hashCode());
		result = 37 * result
				+ (getSekatan() == null ? 0 : this.getSekatan().hashCode());
		result = 37
				* result
				+ (getIdPermohonan() == null ? 0 : this.getIdPermohonan()
						.hashCode());
		result = 37
				* result
				+ (getTarikhBukaFail() == null ? 0 : this.getTarikhBukaFail()
						.hashCode());
		result = 37
				* result
				+ (getKegunaanTapak() == null ? 0 : this.getKegunaanTapak()
						.hashCode());
		result = 37
				* result
				+ (getKegunaanTanah() == null ? 0 : this.getKegunaanTanah()
						.hashCode());
		result = 37 * result
				+ (getStatusSah() == null ? 0 : this.getStatusSah().hashCode());
		result = 37
				* result
				+ (getKodLuasLama() == null ? 0 : this.getKodLuasLama()
						.hashCode());
		result = 37 * result
				+ (getLuasLama() == null ? 0 : this.getLuasLama().hashCode());
		result = 37
				* result
				+ (getStatusPelarasan() == null ? 0 : this.getStatusPelarasan()
						.hashCode());
		result = 37
				* result
				+ (getNoFailJofa() == null ? 0 : this.getNoFailJofa()
						.hashCode());
		result = 37 * result
				+ (getNoFailKjp() == null ? 0 : this.getNoFailKjp().hashCode());
		result = 37
				* result
				+ (getNoFailAgensi() == null ? 0 : this.getNoFailAgensi()
						.hashCode());
		result = 37 * result
				+ (getNoFailPtg() == null ? 0 : this.getNoFailPtg().hashCode());
		result = 37
				* result
				+ (getIdKemaskini() == null ? 0 : this.getIdKemaskini()
						.hashCode());
		result = 37
				* result
				+ (getTarikhKemaskini() == null ? 0 : this.getTarikhKemaskini()
						.hashCode());
		result = 37
				* result
				+ (getStatusGeran() == null ? 0 : this.getStatusGeran()
						.hashCode());
		result = 37
				* result
				+ (getStatusTukarGanti() == null ? 0 : this
						.getStatusTukarGanti().hashCode());
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
				+ (getIdLuas() == null ? 0 : this.getIdLuas().hashCode());
		return result;
	}

}