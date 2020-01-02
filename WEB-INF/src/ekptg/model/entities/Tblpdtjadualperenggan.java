package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtjadualperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtjadualperenggan extends AbstractTblpdtjadualperenggan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtjadualperenggan() {
	}

	/** minimal constructor */
	public Tblpdtjadualperenggan(Long idJadualperenggan) {
		super(idJadualperenggan);
	}

	/** full constructor */
	public Tblpdtjadualperenggan(Long idJadualperenggan,
			Tblpdtjadualseksyen tblpdtjadualseksyen, String notaBirai,
			String kandungan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtjadualsubperenggans,
			Set tblpdtpindaanjadualparas) {
		super(idJadualperenggan, tblpdtjadualseksyen, notaBirai, kandungan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtjadualsubperenggans, tblpdtpindaanjadualparas);
	}

}
