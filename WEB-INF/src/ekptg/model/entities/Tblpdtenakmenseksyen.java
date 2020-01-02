package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenseksyen extends AbstractTblpdtenakmenseksyen
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenseksyen() {
	}

	/** minimal constructor */
	public Tblpdtenakmenseksyen(Long idEnakmenseksyen) {
		super(idEnakmenseksyen);
	}

	/** full constructor */
	public Tblpdtenakmenseksyen(Long idEnakmenseksyen,
			Tblpdtenakmenbab tblpdtenakmenbab,
			String seksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanenakmenseksyens, Set tblpdtenakmenperenggans) {
		super(idEnakmenseksyen, tblpdtenakmenbab, seksyen,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanenakmenseksyens, tblpdtenakmenperenggans);
	}

}
