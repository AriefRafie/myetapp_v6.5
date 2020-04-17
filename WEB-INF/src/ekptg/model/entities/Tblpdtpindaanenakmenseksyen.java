package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanenakmenseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanenakmenseksyen extends
		AbstractTblpdtpindaanenakmenseksyen implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanenakmenseksyen() {
	}

	/** minimal constructor */
	public Tblpdtpindaanenakmenseksyen(Long idPindaanenakmenseksyen) {
		super(idPindaanenakmenseksyen);
	}

	/** full constructor */
	public Tblpdtpindaanenakmenseksyen(Long idPindaanenakmenseksyen,
			Tblpdtenakmenpindaseksyen tblpdtenakmenpindaseksyen,
			Tblpdtenakmenseksyen tblpdtenakmenseksyen, Set tblpdtpindaanenakmens) {
		super(idPindaanenakmenseksyen, tblpdtenakmenpindaseksyen,
				tblpdtenakmenseksyen, tblpdtpindaanenakmens);
	}

}
