package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptulasanteknikal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptulasanteknikal extends AbstractTblpptulasanteknikal
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptulasanteknikal() {
	}

	/** full constructor */
	public Tblpptulasanteknikal(Long idJabatanteknikal, String noRujukanSurat,
			Date tarikhSurat, Date tarikhHantar, Date tarikhTerima,
			Long bilSurat, Long tempoh, Date tarikhMula, Date tarikhAkhir,
			String keputusan, String ulasan, String flagTerima,
			Long idPermohonan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idJabatanteknikal, noRujukanSurat, tarikhSurat, tarikhHantar,
				tarikhTerima, bilSurat, tempoh, tarikhMula, tarikhAkhir,
				keputusan, ulasan, flagTerima, idPermohonan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
