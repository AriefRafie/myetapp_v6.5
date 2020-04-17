package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtjadualpinda entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtjadualpinda extends AbstractTblpdtjadualpinda implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtjadualpinda() {
	}

	/** minimal constructor */
	public Tblpdtjadualpinda(Long idJadualpinda, Tblpdtaktapinda tblpdtaktapinda) {
		super(idJadualpinda, tblpdtaktapinda);
	}

	/** full constructor */
	public Tblpdtjadualpinda(Long idJadualpinda,
			Tblpdtenakmenpinda tblpdtenakmenpinda,
			Tblpdtaktapinda tblpdtaktapinda, String namaJadual, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtjadualpindaseksyens) {
		super(idJadualpinda, tblpdtenakmenpinda, tblpdtaktapinda, namaJadual,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtjadualpindaseksyens);
	}

}
