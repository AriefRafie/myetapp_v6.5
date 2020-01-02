package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujpfdjenisdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujpfdjenisdokumen extends AbstractTblrujpfdjenisdokumen
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujpfdjenisdokumen() {
	}

	/** minimal constructor */
	public Tblrujpfdjenisdokumen(Long idJenisdokumen) {
		super(idJenisdokumen);
	}

	/** full constructor */
	public Tblrujpfdjenisdokumen(Long idJenisdokumen, String kodJenisDokumen,
			String kodSeksyen, String namaDokumen, String kodProgram,
			String kodLaporan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idJenisdokumen, kodJenisDokumen, kodSeksyen, namaDokumen,
				kodProgram, kodLaporan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
