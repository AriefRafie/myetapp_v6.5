package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtjadualpindasubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtjadualpindasubperenggan extends
		AbstractTblpdtjadualpindasubperenggan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtjadualpindasubperenggan() {
	}

	/** minimal constructor */
	public Tblpdtjadualpindasubperenggan(Long idJadualpindasubperenggan,
			Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan) {
		super(idJadualpindasubperenggan, tblpdtjadualpindaperenggan);
	}

	/** full constructor */
	public Tblpdtjadualpindasubperenggan(Long idJadualpindasubperenggan,
			Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan,
			String notaBirai, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanjadualsubparas) {
		super(idJadualpindasubperenggan, tblpdtjadualpindaperenggan, notaBirai,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanjadualsubparas);
	}

}
