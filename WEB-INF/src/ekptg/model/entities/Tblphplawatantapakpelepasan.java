package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphplawatantapakpelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphplawatantapakpelepasan extends
		AbstractTblphplawatantapakpelepasan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphplawatantapakpelepasan() {
	}

	/** minimal constructor */
	public Tblphplawatantapakpelepasan(Long idLawatantapakpelepasan) {
		super(idLawatantapakpelepasan);
	}

	/** full constructor */
	public Tblphplawatantapakpelepasan(Long idLawatantapakpelepasan,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idLawatantapak) {
		super(idLawatantapakpelepasan, tblphppermohonanpelepasan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idLawatantapak);
	}

}
