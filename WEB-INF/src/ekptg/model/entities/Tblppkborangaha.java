package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkborangaha entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangaha extends AbstractTblppkborangaha implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangaha() {
	}

	/** minimal constructor */
	public Tblppkborangaha(Long idBorangaha, Tblrujppkjenisha tblrujppkjenisha,
			Tblppkborangasimati tblppkborangasimati) {
		super(idBorangaha, tblrujppkjenisha, tblppkborangasimati);
	}

	/** full constructor */
	public Tblppkborangaha(Long idBorangaha, Tblrujppkjenisha tblrujppkjenisha,
			Tblppkborangasimati tblppkborangasimati, Long bil, Long idNegeri,
			Long idDaerah, String jenama, String noDaftar, String noSijil,
			Double bilUnit, Date tarikhHarta, String alamatHa1,
			String alamatHa2, String alamatHa3, String poskod,
			Double nilaiHaTarikhmohon, Double nilaiHaTarikhmati,
			String urusniaga, Long baSimati, Long bbSimati, String catatan,
			Double nilaiSeunit, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idBorangaha, tblrujppkjenisha, tblppkborangasimati, bil,
				idNegeri, idDaerah, jenama, noDaftar, noSijil, bilUnit,
				tarikhHarta, alamatHa1, alamatHa2, alamatHa3, poskod,
				nilaiHaTarikhmohon, nilaiHaTarikhmati, urusniaga, baSimati,
				bbSimati, catatan, nilaiSeunit, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
