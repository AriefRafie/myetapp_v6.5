package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtptukargantiId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtptukargantiId extends AbstractTblhtptukargantiId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtptukargantiId() {
	}

	/** minimal constructor */
	public TblhtptukargantiId(Long idTukarganti, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik) {
		super(idTukarganti, idPenswastaan, idHtphakmilik, idHakmilik);
	}

	/** full constructor */
	public TblhtptukargantiId(Long idTukarganti, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik, String ulasan,
			String statusPtp, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idTukarganti, idPenswastaan, idHtphakmilik, idHakmilik, ulasan,
				statusPtp, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
