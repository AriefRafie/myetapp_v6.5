package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanjadualsubpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanjadualsubpara extends
		AbstractTblpdtpindaanjadualsubpara implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanjadualsubpara() {
	}

	/** minimal constructor */
	public Tblpdtpindaanjadualsubpara(Long idPindaanjadualsubpara) {
		super(idPindaanjadualsubpara);
	}

	/** full constructor */
	public Tblpdtpindaanjadualsubpara(Long idPindaanjadualsubpara,
			Tblpdtjadualsubperenggan tblpdtjadualsubperenggan,
			Tblpdtjadualpindasubperenggan tblpdtjadualpindasubperenggan,
			Set tblpdtpindaanaktas, Set tblpdtpindaanenakmens) {
		super(idPindaanjadualsubpara, tblpdtjadualsubperenggan,
				tblpdtjadualpindasubperenggan, tblpdtpindaanaktas,
				tblpdtpindaanenakmens);
	}

}
