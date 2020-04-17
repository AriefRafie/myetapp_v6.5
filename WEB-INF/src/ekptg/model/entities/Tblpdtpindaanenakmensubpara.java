package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanenakmensubpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanenakmensubpara extends
		AbstractTblpdtpindaanenakmensubpara implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanenakmensubpara() {
	}

	/** minimal constructor */
	public Tblpdtpindaanenakmensubpara(Long idPindaanenakmensubpara) {
		super(idPindaanenakmensubpara);
	}

	/** full constructor */
	public Tblpdtpindaanenakmensubpara(Long idPindaanenakmensubpara,
			Tblpdtenakmenpindasubperenggan tblpdtenakmenpindasubperenggan,
			Tblpdtenakmensubperenggan tblpdtenakmensubperenggan,
			Set tblpdtpindaanenakmens) {
		super(idPindaanenakmensubpara, tblpdtenakmenpindasubperenggan,
				tblpdtenakmensubperenggan, tblpdtpindaanenakmens);
	}

}
