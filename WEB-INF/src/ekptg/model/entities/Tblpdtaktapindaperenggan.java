package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktapindaperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktapindaperenggan extends AbstractTblpdtaktapindaperenggan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktapindaperenggan() {
	}

	/** minimal constructor */
	public Tblpdtaktapindaperenggan(Long idAktapindaperenggan) {
		super(idAktapindaperenggan);
	}

	/** full constructor */
	public Tblpdtaktapindaperenggan(Long idAktapindaperenggan,
			Tblpdtaktapindaseksyen tblpdtaktapindaseksyen, String notaBirai,
			String kandungan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtaktapindasubperenggans,
			Set tblpdtpindaanaktaparas) {
		super(idAktapindaperenggan, tblpdtaktapindaseksyen, notaBirai,
				kandungan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtaktapindasubperenggans, tblpdtpindaanaktaparas);
	}

}
