package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktapindaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktapindaseksyen extends AbstractTblpdtaktapindaseksyen
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktapindaseksyen() {
	}

	/** minimal constructor */
	public Tblpdtaktapindaseksyen(Long idAktapindaseksyen) {
		super(idAktapindaseksyen);
	}

	/** full constructor */
	public Tblpdtaktapindaseksyen(Long idAktapindaseksyen,
			Tblpdtaktapindabab tblpdtaktapindabab, Long noSeksyen,
			String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanaktaseksyens, Set tblpdtaktapindaperenggans) {
		super(idAktapindaseksyen, tblpdtaktapindabab, noSeksyen, namaSeksyen,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanaktaseksyens, tblpdtaktapindaperenggans);
	}

}
