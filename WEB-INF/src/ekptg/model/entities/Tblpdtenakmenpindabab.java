package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenpindabab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenpindabab extends AbstractTblpdtenakmenpindabab
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenpindabab() {
	}

	/** minimal constructor */
	public Tblpdtenakmenpindabab(Long idEnakmenpindabab) {
		super(idEnakmenpindabab);
	}

	/** full constructor */
	public Tblpdtenakmenpindabab(Long idEnakmenpindabab,
			Tblpdtenakmenpindabahagian tblpdtenakmenpindabahagian,
			String namaBab, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtenakmenpindaseksyens,
			Set tblpdtpindaanenakmenbabs) {
		super(idEnakmenpindabab, tblpdtenakmenpindabahagian, namaBab, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtenakmenpindaseksyens, tblpdtpindaanenakmenbabs);
	}

}
