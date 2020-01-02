package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpfdrujfaharasat entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpfdrujfaharasat extends AbstractTblpfdrujfaharasat implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdrujfaharasat() {
	}

	/** full constructor */
	public Tblpfdrujfaharasat(String faharasat, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(faharasat, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
