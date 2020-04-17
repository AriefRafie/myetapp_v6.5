package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpulasanteknikalpelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpulasanteknikalpelepasan extends
		AbstractTblphpulasanteknikalpelepasan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpulasanteknikalpelepasan() {
	}

	/** minimal constructor */
	public Tblphpulasanteknikalpelepasan(Long idUlasanteknikalpelepasan) {
		super(idUlasanteknikalpelepasan);
	}

	/** full constructor */
	public Tblphpulasanteknikalpelepasan(Long idUlasanteknikalpelepasan,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idUlasanteknikal) {
		super(idUlasanteknikalpelepasan, tblphppermohonanpelepasan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idUlasanteknikal);
	}

}
