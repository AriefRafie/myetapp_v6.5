package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenpindabahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenpindabahagian extends
		AbstractTblpdtenakmenpindabahagian implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenpindabahagian() {
	}

	/** minimal constructor */
	public Tblpdtenakmenpindabahagian(Long idEnakmenpindabahagian) {
		super(idEnakmenpindabahagian);
	}

	/** full constructor */
	public Tblpdtenakmenpindabahagian(Long idEnakmenpindabahagian,
			Tblpdtenakmenpinda tblpdtenakmenpinda,
			Tblpdtenakmenpindapenggal tblpdtenakmenpindapenggal,
			String namaBahagian, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpdtenakmenpindababs,
			Set tblpdtpindaanenakmenbahagians) {
		super(idEnakmenpindabahagian, tblpdtenakmenpinda,
				tblpdtenakmenpindapenggal, namaBahagian, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblpdtenakmenpindababs,
				tblpdtpindaanenakmenbahagians);
	}

}
