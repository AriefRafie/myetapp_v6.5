package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenperenggan extends AbstractTblpdtenakmenperenggan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenperenggan() {
	}

	/** minimal constructor */
	public Tblpdtenakmenperenggan(Long idEnakmenperenggan) {
		super(idEnakmenperenggan);
	}

	/** full constructor */
	public Tblpdtenakmenperenggan(Long idEnakmenperenggan,
			Tblpdtenakmenseksyen tblpdtenakmenseksyen, String notaBirai,
			String kandungan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmenparas,
			Set tblpdtenakmensubperenggans) {
		super(idEnakmenperenggan, tblpdtenakmenseksyen, notaBirai, kandungan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanenakmenparas, tblpdtenakmensubperenggans);
	}

}
