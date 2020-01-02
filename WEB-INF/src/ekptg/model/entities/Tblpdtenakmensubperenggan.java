package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmensubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmensubperenggan extends
		AbstractTblpdtenakmensubperenggan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmensubperenggan() {
	}

	/** minimal constructor */
	public Tblpdtenakmensubperenggan(Long idEnakmensubperenggan,
			Tblpdtenakmenperenggan tblpdtenakmenperenggan) {
		super(idEnakmensubperenggan, tblpdtenakmenperenggan);
	}

	/** full constructor */
	public Tblpdtenakmensubperenggan(Long idEnakmensubperenggan,
			Tblpdtenakmenperenggan tblpdtenakmenperenggan, String notaBirai,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmensubparas) {
		super(idEnakmensubperenggan, tblpdtenakmenperenggan, notaBirai,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanenakmensubparas);
	}

}
