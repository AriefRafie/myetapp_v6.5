package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtjadualsubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtjadualsubperenggan extends AbstractTblpdtjadualsubperenggan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtjadualsubperenggan() {
	}

	/** minimal constructor */
	public Tblpdtjadualsubperenggan(Long idJadualsubperenggan,
			Tblpdtjadualperenggan tblpdtjadualperenggan) {
		super(idJadualsubperenggan, tblpdtjadualperenggan);
	}

	/** full constructor */
	public Tblpdtjadualsubperenggan(Long idJadualsubperenggan,
			Tblpdtjadualperenggan tblpdtjadualperenggan, String notaBirai,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanjadualsubparas) {
		super(idJadualsubperenggan, tblpdtjadualperenggan, notaBirai, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanjadualsubparas);
	}

}
