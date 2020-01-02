package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanenakmenbab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanenakmenbab extends AbstractTblpdtpindaanenakmenbab
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanenakmenbab() {
	}

	/** minimal constructor */
	public Tblpdtpindaanenakmenbab(Long idPindaanenakmenbab) {
		super(idPindaanenakmenbab);
	}

	/** full constructor */
	public Tblpdtpindaanenakmenbab(Long idPindaanenakmenbab,
			Tblpdtenakmenpindabab tblpdtenakmenpindabab,
			Tblpdtenakmenbab tblpdtenakmenbab, Set tblpdtpindaanenakmens) {
		super(idPindaanenakmenbab, tblpdtenakmenpindabab, tblpdtenakmenbab,
				tblpdtpindaanenakmens);
	}

}
