package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujjenishakmilik entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujjenishakmilik extends AbstractTblrujjenishakmilik implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenishakmilik() {
	}

	/** minimal constructor */
	public Tblrujjenishakmilik(Long idJenishakmilik) {
		super(idJenishakmilik);
	}

	/** full constructor */
	public Tblrujjenishakmilik(Long idJenishakmilik, String kodJenisHakmilik,
			String keterangan, String statusHakmilik, String simpanan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idJenishakmilik, kodJenisHakmilik, keterangan, statusHakmilik,
				simpanan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
