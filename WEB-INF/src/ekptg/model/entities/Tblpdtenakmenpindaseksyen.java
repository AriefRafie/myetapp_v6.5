package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenpindaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenpindaseksyen extends
		AbstractTblpdtenakmenpindaseksyen implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenpindaseksyen() {
	}

	/** minimal constructor */
	public Tblpdtenakmenpindaseksyen(Long idEnakmenpindaseksyen) {
		super(idEnakmenpindaseksyen);
	}

	/** full constructor */
	public Tblpdtenakmenpindaseksyen(Long idEnakmenpindaseksyen,
			Tblpdtenakmenpindabab tblpdtenakmenpindabab, Long noSeksyen,
			String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtenakmenpindaperenggans, Set tblpdtpindaanenakmenseksyens) {
		super(idEnakmenpindaseksyen, tblpdtenakmenpindabab, noSeksyen,
				namaSeksyen, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblpdtenakmenpindaperenggans,
				tblpdtpindaanenakmenseksyens);
	}

}
