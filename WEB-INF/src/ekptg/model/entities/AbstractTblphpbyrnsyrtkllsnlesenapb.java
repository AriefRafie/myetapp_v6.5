package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpbyrnsyrtkllsnlesenapb entity provides the base persistence
 * definition of the Tblphpbyrnsyrtkllsnlesenapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpbyrnsyrtkllsnlesenapb implements
		java.io.Serializable {

	// Fields

	private Long idByrnsyrtkllsnlesenapb;
	private Tblphppmohonnjdualpertama tblphppmohonnjdualpertama;
	private String isipadu;
	private Long idUnitisipadu;
	private Date tarikhSuratJas;
	private String noFailJas;
	private String tajukSuratJas;
	private String keputusanJas;
	private Long tempohKelulusanJas;
	private Date tarikhLulusJas;
	private Long tempohAkhirLulusjas;
	private Long idCawanganjas;
	private Date tarikhPhn;
	private String noFailPhn;
	private String tajukSuratPhn;
	private String keputusanPhn;
	private Long tempohKelulusanPhn;
	private Date tarikhLulusphn;
	private Long tempohAkhirLulusphn;
	private Long idCawanganphn;
	private String ulasanEia;
	private String ulasanHidrografi;
	private Double depositJkptg;
	private Double depositPerikanan;
	private Double kadarFeelesen;
	private Double feelesenBagSetiap;
	private Long idUnitluasbgsetiap;
	private Double jumlahFeelesen;
	private Double kadarRoyalti;
	private String lampiran;
	private Double jarak;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpbyrnsyrtkllsnlesenapb() {
	}

	/** minimal constructor */
	public AbstractTblphpbyrnsyrtkllsnlesenapb(Long idByrnsyrtkllsnlesenapb) {
		this.idByrnsyrtkllsnlesenapb = idByrnsyrtkllsnlesenapb;
	}

	/** full constructor */
	public AbstractTblphpbyrnsyrtkllsnlesenapb(Long idByrnsyrtkllsnlesenapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama,
			String isipadu, Long idUnitisipadu, Date tarikhSuratJas,
			String noFailJas, String tajukSuratJas, String keputusanJas,
			Long tempohKelulusanJas, Date tarikhLulusJas,
			Long tempohAkhirLulusjas, Long idCawanganjas, Date tarikhPhn,
			String noFailPhn, String tajukSuratPhn, String keputusanPhn,
			Long tempohKelulusanPhn, Date tarikhLulusphn,
			Long tempohAkhirLulusphn, Long idCawanganphn, String ulasanEia,
			String ulasanHidrografi, Double depositJkptg,
			Double depositPerikanan, Double kadarFeelesen,
			Double feelesenBagSetiap, Long idUnitluasbgsetiap,
			Double jumlahFeelesen, Double kadarRoyalti, String lampiran,
			Double jarak, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idByrnsyrtkllsnlesenapb = idByrnsyrtkllsnlesenapb;
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
		this.isipadu = isipadu;
		this.idUnitisipadu = idUnitisipadu;
		this.tarikhSuratJas = tarikhSuratJas;
		this.noFailJas = noFailJas;
		this.tajukSuratJas = tajukSuratJas;
		this.keputusanJas = keputusanJas;
		this.tempohKelulusanJas = tempohKelulusanJas;
		this.tarikhLulusJas = tarikhLulusJas;
		this.tempohAkhirLulusjas = tempohAkhirLulusjas;
		this.idCawanganjas = idCawanganjas;
		this.tarikhPhn = tarikhPhn;
		this.noFailPhn = noFailPhn;
		this.tajukSuratPhn = tajukSuratPhn;
		this.keputusanPhn = keputusanPhn;
		this.tempohKelulusanPhn = tempohKelulusanPhn;
		this.tarikhLulusphn = tarikhLulusphn;
		this.tempohAkhirLulusphn = tempohAkhirLulusphn;
		this.idCawanganphn = idCawanganphn;
		this.ulasanEia = ulasanEia;
		this.ulasanHidrografi = ulasanHidrografi;
		this.depositJkptg = depositJkptg;
		this.depositPerikanan = depositPerikanan;
		this.kadarFeelesen = kadarFeelesen;
		this.feelesenBagSetiap = feelesenBagSetiap;
		this.idUnitluasbgsetiap = idUnitluasbgsetiap;
		this.jumlahFeelesen = jumlahFeelesen;
		this.kadarRoyalti = kadarRoyalti;
		this.lampiran = lampiran;
		this.jarak = jarak;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdByrnsyrtkllsnlesenapb() {
		return this.idByrnsyrtkllsnlesenapb;
	}

	public void setIdByrnsyrtkllsnlesenapb(Long idByrnsyrtkllsnlesenapb) {
		this.idByrnsyrtkllsnlesenapb = idByrnsyrtkllsnlesenapb;
	}

	public Tblphppmohonnjdualpertama getTblphppmohonnjdualpertama() {
		return this.tblphppmohonnjdualpertama;
	}

	public void setTblphppmohonnjdualpertama(
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama) {
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
	}

	public String getIsipadu() {
		return this.isipadu;
	}

	public void setIsipadu(String isipadu) {
		this.isipadu = isipadu;
	}

	public Long getIdUnitisipadu() {
		return this.idUnitisipadu;
	}

	public void setIdUnitisipadu(Long idUnitisipadu) {
		this.idUnitisipadu = idUnitisipadu;
	}

	public Date getTarikhSuratJas() {
		return this.tarikhSuratJas;
	}

	public void setTarikhSuratJas(Date tarikhSuratJas) {
		this.tarikhSuratJas = tarikhSuratJas;
	}

	public String getNoFailJas() {
		return this.noFailJas;
	}

	public void setNoFailJas(String noFailJas) {
		this.noFailJas = noFailJas;
	}

	public String getTajukSuratJas() {
		return this.tajukSuratJas;
	}

	public void setTajukSuratJas(String tajukSuratJas) {
		this.tajukSuratJas = tajukSuratJas;
	}

	public String getKeputusanJas() {
		return this.keputusanJas;
	}

	public void setKeputusanJas(String keputusanJas) {
		this.keputusanJas = keputusanJas;
	}

	public Long getTempohKelulusanJas() {
		return this.tempohKelulusanJas;
	}

	public void setTempohKelulusanJas(Long tempohKelulusanJas) {
		this.tempohKelulusanJas = tempohKelulusanJas;
	}

	public Date getTarikhLulusJas() {
		return this.tarikhLulusJas;
	}

	public void setTarikhLulusJas(Date tarikhLulusJas) {
		this.tarikhLulusJas = tarikhLulusJas;
	}

	public Long getTempohAkhirLulusjas() {
		return this.tempohAkhirLulusjas;
	}

	public void setTempohAkhirLulusjas(Long tempohAkhirLulusjas) {
		this.tempohAkhirLulusjas = tempohAkhirLulusjas;
	}

	public Long getIdCawanganjas() {
		return this.idCawanganjas;
	}

	public void setIdCawanganjas(Long idCawanganjas) {
		this.idCawanganjas = idCawanganjas;
	}

	public Date getTarikhPhn() {
		return this.tarikhPhn;
	}

	public void setTarikhPhn(Date tarikhPhn) {
		this.tarikhPhn = tarikhPhn;
	}

	public String getNoFailPhn() {
		return this.noFailPhn;
	}

	public void setNoFailPhn(String noFailPhn) {
		this.noFailPhn = noFailPhn;
	}

	public String getTajukSuratPhn() {
		return this.tajukSuratPhn;
	}

	public void setTajukSuratPhn(String tajukSuratPhn) {
		this.tajukSuratPhn = tajukSuratPhn;
	}

	public String getKeputusanPhn() {
		return this.keputusanPhn;
	}

	public void setKeputusanPhn(String keputusanPhn) {
		this.keputusanPhn = keputusanPhn;
	}

	public Long getTempohKelulusanPhn() {
		return this.tempohKelulusanPhn;
	}

	public void setTempohKelulusanPhn(Long tempohKelulusanPhn) {
		this.tempohKelulusanPhn = tempohKelulusanPhn;
	}

	public Date getTarikhLulusphn() {
		return this.tarikhLulusphn;
	}

	public void setTarikhLulusphn(Date tarikhLulusphn) {
		this.tarikhLulusphn = tarikhLulusphn;
	}

	public Long getTempohAkhirLulusphn() {
		return this.tempohAkhirLulusphn;
	}

	public void setTempohAkhirLulusphn(Long tempohAkhirLulusphn) {
		this.tempohAkhirLulusphn = tempohAkhirLulusphn;
	}

	public Long getIdCawanganphn() {
		return this.idCawanganphn;
	}

	public void setIdCawanganphn(Long idCawanganphn) {
		this.idCawanganphn = idCawanganphn;
	}

	public String getUlasanEia() {
		return this.ulasanEia;
	}

	public void setUlasanEia(String ulasanEia) {
		this.ulasanEia = ulasanEia;
	}

	public String getUlasanHidrografi() {
		return this.ulasanHidrografi;
	}

	public void setUlasanHidrografi(String ulasanHidrografi) {
		this.ulasanHidrografi = ulasanHidrografi;
	}

	public Double getDepositJkptg() {
		return this.depositJkptg;
	}

	public void setDepositJkptg(Double depositJkptg) {
		this.depositJkptg = depositJkptg;
	}

	public Double getDepositPerikanan() {
		return this.depositPerikanan;
	}

	public void setDepositPerikanan(Double depositPerikanan) {
		this.depositPerikanan = depositPerikanan;
	}

	public Double getKadarFeelesen() {
		return this.kadarFeelesen;
	}

	public void setKadarFeelesen(Double kadarFeelesen) {
		this.kadarFeelesen = kadarFeelesen;
	}

	public Double getFeelesenBagSetiap() {
		return this.feelesenBagSetiap;
	}

	public void setFeelesenBagSetiap(Double feelesenBagSetiap) {
		this.feelesenBagSetiap = feelesenBagSetiap;
	}

	public Long getIdUnitluasbgsetiap() {
		return this.idUnitluasbgsetiap;
	}

	public void setIdUnitluasbgsetiap(Long idUnitluasbgsetiap) {
		this.idUnitluasbgsetiap = idUnitluasbgsetiap;
	}

	public Double getJumlahFeelesen() {
		return this.jumlahFeelesen;
	}

	public void setJumlahFeelesen(Double jumlahFeelesen) {
		this.jumlahFeelesen = jumlahFeelesen;
	}

	public Double getKadarRoyalti() {
		return this.kadarRoyalti;
	}

	public void setKadarRoyalti(Double kadarRoyalti) {
		this.kadarRoyalti = kadarRoyalti;
	}

	public String getLampiran() {
		return this.lampiran;
	}

	public void setLampiran(String lampiran) {
		this.lampiran = lampiran;
	}

	public Double getJarak() {
		return this.jarak;
	}

	public void setJarak(Double jarak) {
		this.jarak = jarak;
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