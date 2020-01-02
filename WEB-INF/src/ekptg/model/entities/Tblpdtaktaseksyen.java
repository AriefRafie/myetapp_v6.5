package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktaseksyen extends AbstractTblpdtaktaseksyen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktaseksyen() {
	}

	/** minimal constructor */
	public Tblpdtaktaseksyen(Long idAktaseksyen) {
		super(idAktaseksyen);
	}

	/** full constructor */
	public Tblpdtaktaseksyen(Long idAktaseksyen, Tblpdtaktabab tblpdtaktabab,
			Long noSeksyen, String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpdtaktaperenggans,
			Set tblpdtpindaanaktaseksyens) {
		super(idAktaseksyen, tblpdtaktabab, noSeksyen, namaSeksyen, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtaktaperenggans, tblpdtpindaanaktaseksyens);
	}

}
