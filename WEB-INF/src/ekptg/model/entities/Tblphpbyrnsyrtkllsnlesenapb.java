package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpbyrnsyrtkllsnlesenapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpbyrnsyrtkllsnlesenapb extends
		AbstractTblphpbyrnsyrtkllsnlesenapb implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpbyrnsyrtkllsnlesenapb() {
	}

	/** minimal constructor */
	public Tblphpbyrnsyrtkllsnlesenapb(Long idByrnsyrtkllsnlesenapb) {
		super(idByrnsyrtkllsnlesenapb);
	}

	/** full constructor */
	public Tblphpbyrnsyrtkllsnlesenapb(Long idByrnsyrtkllsnlesenapb,
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
		super(idByrnsyrtkllsnlesenapb, tblphppmohonnjdualpertama, isipadu,
				idUnitisipadu, tarikhSuratJas, noFailJas, tajukSuratJas,
				keputusanJas, tempohKelulusanJas, tarikhLulusJas,
				tempohAkhirLulusjas, idCawanganjas, tarikhPhn, noFailPhn,
				tajukSuratPhn, keputusanPhn, tempohKelulusanPhn,
				tarikhLulusphn, tempohAkhirLulusphn, idCawanganphn, ulasanEia,
				ulasanHidrografi, depositJkptg, depositPerikanan,
				kadarFeelesen, feelesenBagSetiap, idUnitluasbgsetiap,
				jumlahFeelesen, kadarRoyalti, lampiran, jarak, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
