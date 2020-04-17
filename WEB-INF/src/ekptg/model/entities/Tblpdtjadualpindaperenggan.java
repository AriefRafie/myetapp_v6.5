package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtjadualpindaperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtjadualpindaperenggan extends
		AbstractTblpdtjadualpindaperenggan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtjadualpindaperenggan() {
	}

	/** minimal constructor */
	public Tblpdtjadualpindaperenggan(Long idJadualpindaperenggan) {
		super(idJadualpindaperenggan);
	}

	/** full constructor */
	public Tblpdtjadualpindaperenggan(Long idJadualpindaperenggan,
			Tblpdtjadualpindaseksyen tblpdtjadualpindaseksyen,
			String notaBirai, String kandungan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanjadualparas, Set tblpdtjadualpindasubperenggans) {
		super(idJadualpindaperenggan, tblpdtjadualpindaseksyen, notaBirai,
				kandungan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanjadualparas, tblpdtjadualpindasubperenggans);
	}

}
