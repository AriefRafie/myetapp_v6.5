package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktapindasubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktapindasubperenggan extends
		AbstractTblpdtaktapindasubperenggan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktapindasubperenggan() {
	}

	/** minimal constructor */
	public Tblpdtaktapindasubperenggan(Long idAktapindasubperenggan,
			Tblpdtaktapindaperenggan tblpdtaktapindaperenggan) {
		super(idAktapindasubperenggan, tblpdtaktapindaperenggan);
	}

	/** full constructor */
	public Tblpdtaktapindasubperenggan(Long idAktapindasubperenggan,
			Tblpdtaktapindaperenggan tblpdtaktapindaperenggan,
			String notaBirai, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanaktasubparas) {
		super(idAktapindasubperenggan, tblpdtaktapindaperenggan, notaBirai,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanaktasubparas);
	}

}
