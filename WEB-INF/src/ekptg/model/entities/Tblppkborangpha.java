package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkborangpha entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangpha extends AbstractTblppkborangpha implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangpha() {
	}

	/** minimal constructor */
	public Tblppkborangpha(Long idBorangpha,
			Tblppkborangpsimati tblppkborangpsimati, Tblppkbayaran tblppkbayaran) {
		super(idBorangpha, tblppkborangpsimati, tblppkbayaran);
	}

	/** full constructor */
	public Tblppkborangpha(Long idBorangpha, Tblrujppkjenisha tblrujppkjenisha,
			Tblppkborangpsimati tblppkborangpsimati,
			Tblppkbayaran tblppkbayaran, Long idNegeri, Long idDaerah,
			Long bil, String jenama, String noDaftar, String noSijil,
			Double bilUnit, Date tarikhHarta, String alamatHa1,
			String alamatHa2, String alamatHa3, String poskod,
			Double nilaiHaTarikhmohon, Double nilaiHaTarikhmati,
			Double nilaiSeunit, Long baSimati, Long bbSimati, String catatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idBorangpha, tblrujppkjenisha, tblppkborangpsimati,
				tblppkbayaran, idNegeri, idDaerah, bil, jenama, noDaftar,
				noSijil, bilUnit, tarikhHarta, alamatHa1, alamatHa2, alamatHa3,
				poskod, nilaiHaTarikhmohon, nilaiHaTarikhmati, nilaiSeunit,
				baSimati, bbSimati, catatan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
