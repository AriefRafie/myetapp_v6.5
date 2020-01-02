package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpperenggankkplpsn entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpperenggankkplpsn extends AbstractTblphpperenggankkplpsn
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpperenggankkplpsn() {
	}

	/** minimal constructor */
	public Tblphpperenggankkplpsn(Long idPerenggankkplpsn) {
		super(idPerenggankkplpsn);
	}

	/** full constructor */
	public Tblphpperenggankkplpsn(Long idPerenggankkplpsn,
			Tblphpkertaskerjapelepasan tblphpkertaskerjapelepasan,
			String perenggan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPerenggankkplpsn, tblphpkertaskerjapelepasan, perenggan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
