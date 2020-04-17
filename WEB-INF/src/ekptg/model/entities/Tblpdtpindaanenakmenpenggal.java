package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanenakmenpenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanenakmenpenggal extends
		AbstractTblpdtpindaanenakmenpenggal implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanenakmenpenggal() {
	}

	/** minimal constructor */
	public Tblpdtpindaanenakmenpenggal(Long idPindaanenakmenpenggal) {
		super(idPindaanenakmenpenggal);
	}

	/** full constructor */
	public Tblpdtpindaanenakmenpenggal(Long idPindaanenakmenpenggal,
			Tblpdtenakmenpindapenggal tblpdtenakmenpindapenggal,
			Tblpdtenakmenpenggal tblpdtenakmenpenggal, Set tblpdtpindaanenakmens) {
		super(idPindaanenakmenpenggal, tblpdtenakmenpindapenggal,
				tblpdtenakmenpenggal, tblpdtpindaanenakmens);
	}

}
