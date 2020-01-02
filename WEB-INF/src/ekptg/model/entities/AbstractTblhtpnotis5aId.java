package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpnotis5aId entity provides the base persistence definition of
 * the Tblhtpnotis5aId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpnotis5aId implements java.io.Serializable {

	// Fields

	private Long idNotis5a;
	private Tblhtppermohonan tblhtppermohonan;
	private Date tarikhNotis5a;
	private Date tarikhTerima;
	private Double kadarCukai;
	private Double bayarNotis;
	private Double bayarDaftarHakmilik;
	private Double bayarUkur;
	private Double bayarPremium;
	private Double bayarPerenggan;
	private Double bayarSempadan;
	private Double bayaranLain;
	private Double jumlahBayaran;
	private String noRujukanMop;
	private Date tarikhLuput1;
	private Date tarikhLuput2;
	private Double rayuanPremium;
	private Date tarikhTerimaGeran;
	private Date tarikhHantarGeran;
	private Double bayarSediahakmilik;
	private Long bayarWarta;
	private Long rayuanLain;
	private String perihalRayuan;
	private Double cukaiTertunggak;
	private Double bayarTertunggak;
	private Double bayarMohonLa;
	private String drafTitle;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpnotis5aId() {
	}

	/** minimal constructor */
	public AbstractTblhtpnotis5aId(Long idNotis5a,
			Tblhtppermohonan tblhtppermohonan) {
		this.idNotis5a = idNotis5a;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtpnotis5aId(Long idNotis5a,
			Tblhtppermohonan tblhtppermohonan, Date tarikhNotis5a,
			Date tarikhTerima, Double kadarCukai, Double bayarNotis,
			Double bayarDaftarHakmilik, Double bayarUkur, Double bayarPremium,
			Double bayarPerenggan, Double bayarSempadan, Double bayaranLain,
			Double jumlahBayaran, String noRujukanMop, Date tarikhLuput1,
			Date tarikhLuput2, Double rayuanPremium, Date tarikhTerimaGeran,
			Date tarikhHantarGeran, Double bayarSediahakmilik, Long bayarWarta,
			Long rayuanLain, String perihalRayuan, Double cukaiTertunggak,
			Double bayarTertunggak, Double bayarMohonLa, String drafTitle,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idNotis5a = idNotis5a;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tarikhNotis5a = tarikhNotis5a;
		this.tarikhTerima = tarikhTerima;
		this.kadarCukai = kadarCukai;
		this.bayarNotis = bayarNotis;
		this.bayarDaftarHakmilik = bayarDaftarHakmilik;
		this.bayarUkur = bayarUkur;
		this.bayarPremium = bayarPremium;
		this.bayarPerenggan = bayarPerenggan;
		this.bayarSempadan = bayarSempadan;
		this.bayaranLain = bayaranLain;
		this.jumlahBayaran = jumlahBayaran;
		this.noRujukanMop = noRujukanMop;
		this.tarikhLuput1 = tarikhLuput1;
		this.tarikhLuput2 = tarikhLuput2;
		this.rayuanPremium = rayuanPremium;
		this.tarikhTerimaGeran = tarikhTerimaGeran;
		this.tarikhHantarGeran = tarikhHantarGeran;
		this.bayarSediahakmilik = bayarSediahakmilik;
		this.bayarWarta = bayarWarta;
		this.rayuanLain = rayuanLain;
		this.perihalRayuan = perihalRayuan;
		this.cukaiTertunggak = cukaiTertunggak;
		this.bayarTertunggak = bayarTertunggak;
		this.bayarMohonLa = bayarMohonLa;
		this.drafTitle = drafTitle;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdNotis5a() {
		return this.idNotis5a;
	}

	public void setIdNotis5a(Long idNotis5a) {
		this.idNotis5a = idNotis5a;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Date getTarikhNotis5a() {
		return this.tarikhNotis5a;
	}

	public void setTarikhNotis5a(Date tarikhNotis5a) {
		this.tarikhNotis5a = tarikhNotis5a;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Double getKadarCukai() {
		return this.kadarCukai;
	}

	public void setKadarCukai(Double kadarCukai) {
		this.kadarCukai = kadarCukai;
	}

	public Double getBayarNotis() {
		return this.bayarNotis;
	}

	public void setBayarNotis(Double bayarNotis) {
		this.bayarNotis = bayarNotis;
	}

	public Double getBayarDaftarHakmilik() {
		return this.bayarDaftarHakmilik;
	}

	public void setBayarDaftarHakmilik(Double bayarDaftarHakmilik) {
		this.bayarDaftarHakmilik = bayarDaftarHakmilik;
	}

	public Double getBayarUkur() {
		return this.bayarUkur;
	}

	public void setBayarUkur(Double bayarUkur) {
		this.bayarUkur = bayarUkur;
	}

	public Double getBayarPremium() {
		return this.bayarPremium;
	}

	public void setBayarPremium(Double bayarPremium) {
		this.bayarPremium = bayarPremium;
	}

	public Double getBayarPerenggan() {
		return this.bayarPerenggan;
	}

	public void setBayarPerenggan(Double bayarPerenggan) {
		this.bayarPerenggan = bayarPerenggan;
	}

	public Double getBayarSempadan() {
		return this.bayarSempadan;
	}

	public void setBayarSempadan(Double bayarSempadan) {
		this.bayarSempadan = bayarSempadan;
	}

	public Double getBayaranLain() {
		return this.bayaranLain;
	}

	public void setBayaranLain(Double bayaranLain) {
		this.bayaranLain = bayaranLain;
	}

	public Double getJumlahBayaran() {
		return this.jumlahBayaran;
	}

	public void setJumlahBayaran(Double jumlahBayaran) {
		this.jumlahBayaran = jumlahBayaran;
	}

	public String getNoRujukanMop() {
		return this.noRujukanMop;
	}

	public void setNoRujukanMop(String noRujukanMop) {
		this.noRujukanMop = noRujukanMop;
	}

	public Date getTarikhLuput1() {
		return this.tarikhLuput1;
	}

	public void setTarikhLuput1(Date tarikhLuput1) {
		this.tarikhLuput1 = tarikhLuput1;
	}

	public Date getTarikhLuput2() {
		return this.tarikhLuput2;
	}

	public void setTarikhLuput2(Date tarikhLuput2) {
		this.tarikhLuput2 = tarikhLuput2;
	}

	public Double getRayuanPremium() {
		return this.rayuanPremium;
	}

	public void setRayuanPremium(Double rayuanPremium) {
		this.rayuanPremium = rayuanPremium;
	}

	public Date getTarikhTerimaGeran() {
		return this.tarikhTerimaGeran;
	}

	public void setTarikhTerimaGeran(Date tarikhTerimaGeran) {
		this.tarikhTerimaGeran = tarikhTerimaGeran;
	}

	public Date getTarikhHantarGeran() {
		return this.tarikhHantarGeran;
	}

	public void setTarikhHantarGeran(Date tarikhHantarGeran) {
		this.tarikhHantarGeran = tarikhHantarGeran;
	}

	public Double getBayarSediahakmilik() {
		return this.bayarSediahakmilik;
	}

	public void setBayarSediahakmilik(Double bayarSediahakmilik) {
		this.bayarSediahakmilik = bayarSediahakmilik;
	}

	public Long getBayarWarta() {
		return this.bayarWarta;
	}

	public void setBayarWarta(Long bayarWarta) {
		this.bayarWarta = bayarWarta;
	}

	public Long getRayuanLain() {
		return this.rayuanLain;
	}

	public void setRayuanLain(Long rayuanLain) {
		this.rayuanLain = rayuanLain;
	}

	public String getPerihalRayuan() {
		return this.perihalRayuan;
	}

	public void setPerihalRayuan(String perihalRayuan) {
		this.perihalRayuan = perihalRayuan;
	}

	public Double getCukaiTertunggak() {
		return this.cukaiTertunggak;
	}

	public void setCukaiTertunggak(Double cukaiTertunggak) {
		this.cukaiTertunggak = cukaiTertunggak;
	}

	public Double getBayarTertunggak() {
		return this.bayarTertunggak;
	}

	public void setBayarTertunggak(Double bayarTertunggak) {
		this.bayarTertunggak = bayarTertunggak;
	}

	public Double getBayarMohonLa() {
		return this.bayarMohonLa;
	}

	public void setBayarMohonLa(Double bayarMohonLa) {
		this.bayarMohonLa = bayarMohonLa;
	}

	public String getDrafTitle() {
		return this.drafTitle;
	}

	public void setDrafTitle(String drafTitle) {
		this.drafTitle = drafTitle;
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
		if (!(other instanceof AbstractTblhtpnotis5aId))
			return false;
		AbstractTblhtpnotis5aId castOther = (AbstractTblhtpnotis5aId) other;

		return ((this.getIdNotis5a() == castOther.getIdNotis5a()) || (this
				.getIdNotis5a() != null
				&& castOther.getIdNotis5a() != null && this.getIdNotis5a()
				.equals(castOther.getIdNotis5a())))
				&& ((this.getTblhtppermohonan() == castOther
						.getTblhtppermohonan()) || (this.getTblhtppermohonan() != null
						&& castOther.getTblhtppermohonan() != null && this
						.getTblhtppermohonan().equals(
								castOther.getTblhtppermohonan())))
				&& ((this.getTarikhNotis5a() == castOther.getTarikhNotis5a()) || (this
						.getTarikhNotis5a() != null
						&& castOther.getTarikhNotis5a() != null && this
						.getTarikhNotis5a()
						.equals(castOther.getTarikhNotis5a())))
				&& ((this.getTarikhTerima() == castOther.getTarikhTerima()) || (this
						.getTarikhTerima() != null
						&& castOther.getTarikhTerima() != null && this
						.getTarikhTerima().equals(castOther.getTarikhTerima())))
				&& ((this.getKadarCukai() == castOther.getKadarCukai()) || (this
						.getKadarCukai() != null
						&& castOther.getKadarCukai() != null && this
						.getKadarCukai().equals(castOther.getKadarCukai())))
				&& ((this.getBayarNotis() == castOther.getBayarNotis()) || (this
						.getBayarNotis() != null
						&& castOther.getBayarNotis() != null && this
						.getBayarNotis().equals(castOther.getBayarNotis())))
				&& ((this.getBayarDaftarHakmilik() == castOther
						.getBayarDaftarHakmilik()) || (this
						.getBayarDaftarHakmilik() != null
						&& castOther.getBayarDaftarHakmilik() != null && this
						.getBayarDaftarHakmilik().equals(
								castOther.getBayarDaftarHakmilik())))
				&& ((this.getBayarUkur() == castOther.getBayarUkur()) || (this
						.getBayarUkur() != null
						&& castOther.getBayarUkur() != null && this
						.getBayarUkur().equals(castOther.getBayarUkur())))
				&& ((this.getBayarPremium() == castOther.getBayarPremium()) || (this
						.getBayarPremium() != null
						&& castOther.getBayarPremium() != null && this
						.getBayarPremium().equals(castOther.getBayarPremium())))
				&& ((this.getBayarPerenggan() == castOther.getBayarPerenggan()) || (this
						.getBayarPerenggan() != null
						&& castOther.getBayarPerenggan() != null && this
						.getBayarPerenggan().equals(
								castOther.getBayarPerenggan())))
				&& ((this.getBayarSempadan() == castOther.getBayarSempadan()) || (this
						.getBayarSempadan() != null
						&& castOther.getBayarSempadan() != null && this
						.getBayarSempadan()
						.equals(castOther.getBayarSempadan())))
				&& ((this.getBayaranLain() == castOther.getBayaranLain()) || (this
						.getBayaranLain() != null
						&& castOther.getBayaranLain() != null && this
						.getBayaranLain().equals(castOther.getBayaranLain())))
				&& ((this.getJumlahBayaran() == castOther.getJumlahBayaran()) || (this
						.getJumlahBayaran() != null
						&& castOther.getJumlahBayaran() != null && this
						.getJumlahBayaran()
						.equals(castOther.getJumlahBayaran())))
				&& ((this.getNoRujukanMop() == castOther.getNoRujukanMop()) || (this
						.getNoRujukanMop() != null
						&& castOther.getNoRujukanMop() != null && this
						.getNoRujukanMop().equals(castOther.getNoRujukanMop())))
				&& ((this.getTarikhLuput1() == castOther.getTarikhLuput1()) || (this
						.getTarikhLuput1() != null
						&& castOther.getTarikhLuput1() != null && this
						.getTarikhLuput1().equals(castOther.getTarikhLuput1())))
				&& ((this.getTarikhLuput2() == castOther.getTarikhLuput2()) || (this
						.getTarikhLuput2() != null
						&& castOther.getTarikhLuput2() != null && this
						.getTarikhLuput2().equals(castOther.getTarikhLuput2())))
				&& ((this.getRayuanPremium() == castOther.getRayuanPremium()) || (this
						.getRayuanPremium() != null
						&& castOther.getRayuanPremium() != null && this
						.getRayuanPremium()
						.equals(castOther.getRayuanPremium())))
				&& ((this.getTarikhTerimaGeran() == castOther
						.getTarikhTerimaGeran()) || (this
						.getTarikhTerimaGeran() != null
						&& castOther.getTarikhTerimaGeran() != null && this
						.getTarikhTerimaGeran().equals(
								castOther.getTarikhTerimaGeran())))
				&& ((this.getTarikhHantarGeran() == castOther
						.getTarikhHantarGeran()) || (this
						.getTarikhHantarGeran() != null
						&& castOther.getTarikhHantarGeran() != null && this
						.getTarikhHantarGeran().equals(
								castOther.getTarikhHantarGeran())))
				&& ((this.getBayarSediahakmilik() == castOther
						.getBayarSediahakmilik()) || (this
						.getBayarSediahakmilik() != null
						&& castOther.getBayarSediahakmilik() != null && this
						.getBayarSediahakmilik().equals(
								castOther.getBayarSediahakmilik())))
				&& ((this.getBayarWarta() == castOther.getBayarWarta()) || (this
						.getBayarWarta() != null
						&& castOther.getBayarWarta() != null && this
						.getBayarWarta().equals(castOther.getBayarWarta())))
				&& ((this.getRayuanLain() == castOther.getRayuanLain()) || (this
						.getRayuanLain() != null
						&& castOther.getRayuanLain() != null && this
						.getRayuanLain().equals(castOther.getRayuanLain())))
				&& ((this.getPerihalRayuan() == castOther.getPerihalRayuan()) || (this
						.getPerihalRayuan() != null
						&& castOther.getPerihalRayuan() != null && this
						.getPerihalRayuan()
						.equals(castOther.getPerihalRayuan())))
				&& ((this.getCukaiTertunggak() == castOther
						.getCukaiTertunggak()) || (this.getCukaiTertunggak() != null
						&& castOther.getCukaiTertunggak() != null && this
						.getCukaiTertunggak().equals(
								castOther.getCukaiTertunggak())))
				&& ((this.getBayarTertunggak() == castOther
						.getBayarTertunggak()) || (this.getBayarTertunggak() != null
						&& castOther.getBayarTertunggak() != null && this
						.getBayarTertunggak().equals(
								castOther.getBayarTertunggak())))
				&& ((this.getBayarMohonLa() == castOther.getBayarMohonLa()) || (this
						.getBayarMohonLa() != null
						&& castOther.getBayarMohonLa() != null && this
						.getBayarMohonLa().equals(castOther.getBayarMohonLa())))
				&& ((this.getDrafTitle() == castOther.getDrafTitle()) || (this
						.getDrafTitle() != null
						&& castOther.getDrafTitle() != null && this
						.getDrafTitle().equals(castOther.getDrafTitle())))
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

		result = 37 * result
				+ (getIdNotis5a() == null ? 0 : this.getIdNotis5a().hashCode());
		result = 37
				* result
				+ (getTblhtppermohonan() == null ? 0 : this
						.getTblhtppermohonan().hashCode());
		result = 37
				* result
				+ (getTarikhNotis5a() == null ? 0 : this.getTarikhNotis5a()
						.hashCode());
		result = 37
				* result
				+ (getTarikhTerima() == null ? 0 : this.getTarikhTerima()
						.hashCode());
		result = 37
				* result
				+ (getKadarCukai() == null ? 0 : this.getKadarCukai()
						.hashCode());
		result = 37
				* result
				+ (getBayarNotis() == null ? 0 : this.getBayarNotis()
						.hashCode());
		result = 37
				* result
				+ (getBayarDaftarHakmilik() == null ? 0 : this
						.getBayarDaftarHakmilik().hashCode());
		result = 37 * result
				+ (getBayarUkur() == null ? 0 : this.getBayarUkur().hashCode());
		result = 37
				* result
				+ (getBayarPremium() == null ? 0 : this.getBayarPremium()
						.hashCode());
		result = 37
				* result
				+ (getBayarPerenggan() == null ? 0 : this.getBayarPerenggan()
						.hashCode());
		result = 37
				* result
				+ (getBayarSempadan() == null ? 0 : this.getBayarSempadan()
						.hashCode());
		result = 37
				* result
				+ (getBayaranLain() == null ? 0 : this.getBayaranLain()
						.hashCode());
		result = 37
				* result
				+ (getJumlahBayaran() == null ? 0 : this.getJumlahBayaran()
						.hashCode());
		result = 37
				* result
				+ (getNoRujukanMop() == null ? 0 : this.getNoRujukanMop()
						.hashCode());
		result = 37
				* result
				+ (getTarikhLuput1() == null ? 0 : this.getTarikhLuput1()
						.hashCode());
		result = 37
				* result
				+ (getTarikhLuput2() == null ? 0 : this.getTarikhLuput2()
						.hashCode());
		result = 37
				* result
				+ (getRayuanPremium() == null ? 0 : this.getRayuanPremium()
						.hashCode());
		result = 37
				* result
				+ (getTarikhTerimaGeran() == null ? 0 : this
						.getTarikhTerimaGeran().hashCode());
		result = 37
				* result
				+ (getTarikhHantarGeran() == null ? 0 : this
						.getTarikhHantarGeran().hashCode());
		result = 37
				* result
				+ (getBayarSediahakmilik() == null ? 0 : this
						.getBayarSediahakmilik().hashCode());
		result = 37
				* result
				+ (getBayarWarta() == null ? 0 : this.getBayarWarta()
						.hashCode());
		result = 37
				* result
				+ (getRayuanLain() == null ? 0 : this.getRayuanLain()
						.hashCode());
		result = 37
				* result
				+ (getPerihalRayuan() == null ? 0 : this.getPerihalRayuan()
						.hashCode());
		result = 37
				* result
				+ (getCukaiTertunggak() == null ? 0 : this.getCukaiTertunggak()
						.hashCode());
		result = 37
				* result
				+ (getBayarTertunggak() == null ? 0 : this.getBayarTertunggak()
						.hashCode());
		result = 37
				* result
				+ (getBayarMohonLa() == null ? 0 : this.getBayarMohonLa()
						.hashCode());
		result = 37 * result
				+ (getDrafTitle() == null ? 0 : this.getDrafTitle().hashCode());
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