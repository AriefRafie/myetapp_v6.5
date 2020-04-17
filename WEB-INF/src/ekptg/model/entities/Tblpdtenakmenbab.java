package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenbab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenbab extends AbstractTblpdtenakmenbab implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenbab() {
	}

	/** minimal constructor */
	public Tblpdtenakmenbab(Long idEnakmenbab) {
		super(idEnakmenbab);
	}

	/** full constructor */
	public Tblpdtenakmenbab(Long idEnakmenbab,
			Tblpdtenakmenbahagian tblpdtenakmenbahagian, String tajukBab,String noBab,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtenakmenseksyens,
			Set tblpdtpindaanenakmenbabs) {
		super(idEnakmenbab, tblpdtenakmenbahagian, tajukBab,noBab, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtenakmenseksyens, tblpdtpindaanenakmenbabs);
	}

}
