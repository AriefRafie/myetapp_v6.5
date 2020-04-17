package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanenakmenbahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanenakmenbahagian extends
		AbstractTblpdtpindaanenakmenbahagian implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanenakmenbahagian() {
	}

	/** minimal constructor */
	public Tblpdtpindaanenakmenbahagian(Long idPindaanenakmenbahagian) {
		super(idPindaanenakmenbahagian);
	}

	/** full constructor */
	public Tblpdtpindaanenakmenbahagian(Long idPindaanenakmenbahagian,
			Tblpdtenakmenbahagian tblpdtenakmenbahagian,
			Tblpdtenakmenpindabahagian tblpdtenakmenpindabahagian,
			Set tblpdtpindaanenakmens) {
		super(idPindaanenakmenbahagian, tblpdtenakmenbahagian,
				tblpdtenakmenpindabahagian, tblpdtpindaanenakmens);
	}

}
