package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktaperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktaperenggan extends AbstractTblpdtaktaperenggan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktaperenggan() {
	}

	/** minimal constructor */
	public Tblpdtaktaperenggan(Long idAktaperenggan) {
		super(idAktaperenggan);
	}

	/** full constructor */
	public Tblpdtaktaperenggan(Long idAktaperenggan,
			Tblpdtaktaseksyen tblpdtaktaseksyen, String notaBirai,
			String kandungan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtaktasubperenggans,
			Set tblpdtpindaanaktaparas) {
		super(idAktaperenggan, tblpdtaktaseksyen, notaBirai, kandungan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtaktasubperenggans, tblpdtpindaanaktaparas);
	}

}
