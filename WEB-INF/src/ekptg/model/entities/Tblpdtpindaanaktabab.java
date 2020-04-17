package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanaktabab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanaktabab extends AbstractTblpdtpindaanaktabab
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanaktabab() {
	}

	/** minimal constructor */
	public Tblpdtpindaanaktabab(Long idPindaanaktabab) {
		super(idPindaanaktabab);
	}

	/** full constructor */
	public Tblpdtpindaanaktabab(Long idPindaanaktabab,
			Tblpdtaktabab tblpdtaktabab, Tblpdtaktapindabab tblpdtaktapindabab,
			Set tblpdtpindaanaktas) {
		super(idPindaanaktabab, tblpdtaktabab, tblpdtaktapindabab,
				tblpdtpindaanaktas);
	}

}
