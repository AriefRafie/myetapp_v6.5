package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenpindasubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenpindasubperenggan extends
		AbstractTblpdtenakmenpindasubperenggan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenpindasubperenggan() {
	}

	/** minimal constructor */
	public Tblpdtenakmenpindasubperenggan(Long idEnakmenpindasubperenggan,
			Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan) {
		super(idEnakmenpindasubperenggan, tblpdtenakmenpindaperenggan);
	}

	/** full constructor */
	public Tblpdtenakmenpindasubperenggan(Long idEnakmenpindasubperenggan,
			Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan,
			String notaBirai, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmensubparas) {
		super(idEnakmenpindasubperenggan, tblpdtenakmenpindaperenggan,
				notaBirai, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanenakmensubparas);
	}

}
