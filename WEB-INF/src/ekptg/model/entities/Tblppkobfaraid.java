package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkobfaraid entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkobfaraid extends AbstractTblppkobfaraid implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkobfaraid() {
	}

	/** minimal constructor */
	public Tblppkobfaraid(Long idObfaraid, Tblppkhbgnfaraid tblppkhbgnfaraid,
			Tblppkob tblppkob) {
		super(idObfaraid, tblppkhbgnfaraid, tblppkob);
	}

	/** full constructor */
	public Tblppkobfaraid(Long idObfaraid, Tblppkhbgnfaraid tblppkhbgnfaraid,
			Tblppkob tblppkob, String statusHidup, Long baFaraid,
			Long bbFaraid, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idObfaraid, tblppkhbgnfaraid, tblppkob, statusHidup, baFaraid,
				bbFaraid, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
