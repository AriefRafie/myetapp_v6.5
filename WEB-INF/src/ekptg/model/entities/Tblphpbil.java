package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpbil entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpbil extends AbstractTblphpbil implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpbil() {
	}

	/** minimal constructor */
	public Tblphpbil(Long idBil) {
		super(idBil);
	}

	/** full constructor */
	public Tblphpbil(Long idBil,
			Tblphpbayaranperludibayar tblphpbayaranperludibayar,
			Tblphppemegang tblphppemegang, String noAkaun, Double amaunSemasa,
			Double amaunPerludibayar, Double bakiAkhir, Long bulanBil,
			Long tahunBil, String flagTangguh, String flagBayar,
			String flagBatal, Long idBatal, Date tarikhBatal, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Double amaunTertunggak) {
		super(idBil, tblphpbayaranperludibayar, tblphppemegang, noAkaun,
				amaunSemasa, amaunPerludibayar, bakiAkhir, bulanBil, tahunBil,
				flagTangguh, flagBayar, flagBatal, idBatal, tarikhBatal,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				amaunTertunggak);
	}

}
