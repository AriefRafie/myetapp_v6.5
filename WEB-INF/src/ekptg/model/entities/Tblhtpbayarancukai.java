package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblhtpbayarancukai entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpbayarancukai extends AbstractTblhtpbayarancukai implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpbayarancukai() {
	}

	/** minimal constructor */
	public Tblhtpbayarancukai(Long idBayarancukai,
			Tblhtpperingkatbayaran tblhtpperingkatbayaran,
			Tblhtpbaucer tblhtpbaucer) {
		super(idBayarancukai, tblhtpperingkatbayaran, tblhtpbaucer);
	}

	/** full constructor */
	public Tblhtpbayarancukai(Long idBayarancukai,
			Tblhtpperingkatbayaran tblhtpperingkatbayaran,
			Tblhtpbaucer tblhtpbaucer, String status, Date tarikhBayaran,
			String namaBank, Double amaun, Date tarikhTerimaBayaran,
			Date tarikhHantarPtg, String noRujbayaran, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblhtpbajetgunas) {
		super(idBayarancukai, tblhtpperingkatbayaran, tblhtpbaucer, status,
				tarikhBayaran, namaBank, amaun, tarikhTerimaBayaran,
				tarikhHantarPtg, noRujbayaran, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblhtpbajetgunas);
	}

}
