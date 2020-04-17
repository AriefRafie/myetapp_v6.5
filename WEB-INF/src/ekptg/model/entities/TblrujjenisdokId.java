package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujjenisdokId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujjenisdokId extends AbstractTblrujjenisdokId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujjenisdokId() {
	}

	/** minimal constructor */
	public TblrujjenisdokId(Long idJenisdok) {
		super(idJenisdok);
	}

	/** full constructor */
	public TblrujjenisdokId(Long idJenisdok, String kodJenisDokumen,
			String keterangan, Long idForm, Long idReport, Long idSeksyen,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idJenisdok, kodJenisDokumen, keterangan, idForm, idReport,
				idSeksyen, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
