package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktapindabab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktapindabab extends AbstractTblpdtaktapindabab implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktapindabab() {
	}

	/** minimal constructor */
	public Tblpdtaktapindabab(Long idAktapindabab) {
		super(idAktapindabab);
	}

	/** full constructor */
	public Tblpdtaktapindabab(Long idAktapindabab,
			Tblpdtaktapindabahagian tblpdtaktapindabahagian, String namaBab,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtaktapindaseksyens,
			Set tblpdtpindaanaktababs) {
		super(idAktapindabab, tblpdtaktapindabahagian, namaBab, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtaktapindaseksyens, tblpdtpindaanaktababs);
	}

}
