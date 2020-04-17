package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujjenisdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujjenisdokumen extends AbstractTblrujjenisdokumen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenisdokumen() {
	}

	/** minimal constructor */
	public Tblrujjenisdokumen(Long idJenisdokumen) {
		super(idJenisdokumen);
	}

	/** full constructor */
	public Tblrujjenisdokumen(Long idJenisdokumen, String kodJenisDokumen, 
			String keterangan, String idLaporan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idSeksyen) {
		super(idJenisdokumen, kodJenisDokumen, keterangan, idLaporan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idSeksyen);
	}

}
