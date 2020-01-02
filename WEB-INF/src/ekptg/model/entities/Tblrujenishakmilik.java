package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujenishakmilik entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujenishakmilik extends AbstractTblrujenishakmilik implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujenishakmilik() {
	}

	/** minimal constructor */
	public Tblrujenishakmilik(Long idJenishakmilik) {
		super(idJenishakmilik);
	}

	/** full constructor */
	public Tblrujenishakmilik(Long idJenishakmilik, String kodJenisHm,
			String keterangan, String statusHm, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idJenishakmilik, kodJenisHm, keterangan, statusHm,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
