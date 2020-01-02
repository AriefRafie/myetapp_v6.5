package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenbahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenbahagian extends AbstractTblpdtenakmenbahagian
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenbahagian() {
	}

	/** minimal constructor */
	public Tblpdtenakmenbahagian(Long idEnakmenbahagian) {
		super(idEnakmenbahagian);
	}

	/** full constructor */
	public Tblpdtenakmenbahagian(Long idEnakmenbahagian,
			Tblpdtenakmen tblpdtenakmen,
			Tblpdtenakmenpenggal tblpdtenakmenpenggal, String tajukBahagian,String noBahagian,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmenbahagians,
			Set tblpdtenakmenbabs) {
		super(idEnakmenbahagian, tblpdtenakmen, tblpdtenakmenpenggal,
				tajukBahagian,noBahagian, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblpdtpindaanenakmenbahagians,
				tblpdtenakmenbabs);
	}

}
