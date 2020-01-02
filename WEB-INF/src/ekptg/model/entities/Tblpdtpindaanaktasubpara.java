package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanaktasubpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanaktasubpara extends AbstractTblpdtpindaanaktasubpara
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanaktasubpara() {
	}

	/** minimal constructor */
	public Tblpdtpindaanaktasubpara(Long idPindaanaktasubpara) {
		super(idPindaanaktasubpara);
	}

	/** full constructor */
	public Tblpdtpindaanaktasubpara(Long idPindaanaktasubpara,
			Tblpdtaktapindasubperenggan tblpdtaktapindasubperenggan,
			Tblpdtaktasubperenggan tblpdtaktasubperenggan,
			Set tblpdtpindaanaktas) {
		super(idPindaanaktasubpara, tblpdtaktapindasubperenggan,
				tblpdtaktasubperenggan, tblpdtpindaanaktas);
	}

}
