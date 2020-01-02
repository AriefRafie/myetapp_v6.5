package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanaktaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanaktaseksyen extends AbstractTblpdtpindaanaktaseksyen
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanaktaseksyen() {
	}

	/** minimal constructor */
	public Tblpdtpindaanaktaseksyen(Long idPindaanaktaseksyen) {
		super(idPindaanaktaseksyen);
	}

	/** full constructor */
	public Tblpdtpindaanaktaseksyen(Long idPindaanaktaseksyen,
			Tblpdtaktapindaseksyen tblpdtaktapindaseksyen,
			Tblpdtaktaseksyen tblpdtaktaseksyen, Set tblpdtpindaanaktas) {
		super(idPindaanaktaseksyen, tblpdtaktapindaseksyen, tblpdtaktaseksyen,
				tblpdtpindaanaktas);
	}

}
