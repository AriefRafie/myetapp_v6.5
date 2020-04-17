package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanaktapara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanaktapara extends AbstractTblpdtpindaanaktapara
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanaktapara() {
	}

	/** minimal constructor */
	public Tblpdtpindaanaktapara(Long idPindaanaktapara) {
		super(idPindaanaktapara);
	}

	/** full constructor */
	public Tblpdtpindaanaktapara(Long idPindaanaktapara,
			Tblpdtaktaperenggan tblpdtaktaperenggan,
			Tblpdtaktapindaperenggan tblpdtaktapindaperenggan,
			Set tblpdtpindaanaktas) {
		super(idPindaanaktapara, tblpdtaktaperenggan, tblpdtaktapindaperenggan,
				tblpdtpindaanaktas);
	}

}
