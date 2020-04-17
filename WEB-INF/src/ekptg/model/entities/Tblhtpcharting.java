package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpcharting entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpcharting extends AbstractTblhtpcharting implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpcharting() {
	}

	/** minimal constructor */
	public Tblhtpcharting(Long idCharting, Tblhtppermohonan tblhtppermohonan,
			String noRujukanSeksyen) {
		super(idCharting, tblhtppermohonan, noRujukanSeksyen);
	}

	/** full constructor */
	public Tblhtpcharting(Long idCharting, Tblhtppermohonan tblhtppermohonan,
			String noRujukanSeksyen, String flagPelan, String flagCharting,
			String ulasan, Double jumlahBayaranProses, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idCharting, tblhtppermohonan, noRujukanSeksyen, flagPelan,
				flagCharting, ulasan, jumlahBayaranProses, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
