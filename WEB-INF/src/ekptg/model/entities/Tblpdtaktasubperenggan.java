package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktasubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktasubperenggan extends AbstractTblpdtaktasubperenggan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktasubperenggan() {
	}

	/** minimal constructor */
	public Tblpdtaktasubperenggan(Long idAktasubperenggan,
			Tblpdtaktaperenggan tblpdtaktaperenggan) {
		super(idAktasubperenggan, tblpdtaktaperenggan);
	}

	/** full constructor */
	public Tblpdtaktasubperenggan(Long idAktasubperenggan,
			Tblpdtaktaperenggan tblpdtaktaperenggan, String notaBirai,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanaktasubparas) {
		super(idAktasubperenggan, tblpdtaktaperenggan, notaBirai, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanaktasubparas);
	}

}
