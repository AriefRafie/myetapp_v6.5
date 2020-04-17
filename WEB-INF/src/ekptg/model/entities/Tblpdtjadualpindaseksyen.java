package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtjadualpindaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtjadualpindaseksyen extends AbstractTblpdtjadualpindaseksyen
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtjadualpindaseksyen() {
	}

	/** minimal constructor */
	public Tblpdtjadualpindaseksyen(Long idJadualpindaseksyen) {
		super(idJadualpindaseksyen);
	}

	/** full constructor */
	public Tblpdtjadualpindaseksyen(Long idJadualpindaseksyen,
			Tblpdtjadualpinda tblpdtjadualpinda, Long noSeksyen,
			String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtjadualpindaperenggans) {
		super(idJadualpindaseksyen, tblpdtjadualpinda, noSeksyen, namaSeksyen,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtjadualpindaperenggans);
	}

}
