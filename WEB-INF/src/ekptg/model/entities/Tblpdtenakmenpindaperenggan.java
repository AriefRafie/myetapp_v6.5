package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenpindaperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenpindaperenggan extends
		AbstractTblpdtenakmenpindaperenggan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenpindaperenggan() {
	}

	/** minimal constructor */
	public Tblpdtenakmenpindaperenggan(Long idEnakmenpindaperenggan) {
		super(idEnakmenpindaperenggan);
	}

	/** full constructor */
	public Tblpdtenakmenpindaperenggan(Long idEnakmenpindaperenggan,
			Tblpdtenakmenpindaseksyen tblpdtenakmenpindaseksyen,
			String notaBirai, String kandungan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanenakmenparas, Set tblpdtenakmenpindasubperenggans) {
		super(idEnakmenpindaperenggan, tblpdtenakmenpindaseksyen, notaBirai,
				kandungan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanenakmenparas, tblpdtenakmenpindasubperenggans);
	}

}
