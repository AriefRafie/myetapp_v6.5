package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanenakmenpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanenakmenpara extends AbstractTblpdtpindaanenakmenpara
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanenakmenpara() {
	}

	/** minimal constructor */
	public Tblpdtpindaanenakmenpara(Long idPindaanenakmenpara) {
		super(idPindaanenakmenpara);
	}

	/** full constructor */
	public Tblpdtpindaanenakmenpara(Long idPindaanenakmenpara,
			Tblpdtenakmenperenggan tblpdtenakmenperenggan,
			Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan,
			Set tblpdtpindaanenakmens) {
		super(idPindaanenakmenpara, tblpdtenakmenperenggan,
				tblpdtenakmenpindaperenggan, tblpdtpindaanenakmens);
	}

}
