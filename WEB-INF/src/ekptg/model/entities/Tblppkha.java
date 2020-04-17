package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkha entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkha extends AbstractTblppkha implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkha() {
	}

	/** minimal constructor */
	public Tblppkha(Long idHa) {
		super(idHa);
	}

	/** full constructor */
	public Tblppkha(Long idHa, Long idJenisha,
			Long idSimati, Long bil, Long idNegeri, Long idDaerah,
			String jenama, String noDaftar, String noSijil, Double bilUnit,
			Date tarikhHarta, String alamatHa1, String alamatHa2,
			String alamatHa3, String poskod, Double nilaiHaTarikhmohon,
			Double nilaiHaTarikhmati, Long baSimati, Long bbSimati,
			String catatan, Double nilaiSeunit, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idHa,idSimati, idJenisha, bil, idNegeri, idDaerah,
				jenama, noDaftar, noSijil, bilUnit, tarikhHarta, alamatHa1,
				alamatHa2, alamatHa3, poskod, nilaiHaTarikhmohon,
				nilaiHaTarikhmati, baSimati, bbSimati, catatan, nilaiSeunit,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
