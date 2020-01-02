package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenpenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenpenggal extends AbstractTblpdtenakmenpenggal
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenpenggal() {
	}

	/** minimal constructor */
	public Tblpdtenakmenpenggal(Long idEnakmenpenggal) {
		super(idEnakmenpenggal);
	}

	/** full constructor */
	public Tblpdtenakmenpenggal(Long idEnakmenpenggal,
			Tblpdtenakmen tblpdtenakmen, String tajukPenggal, String noPenggal, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanenakmenpenggals, Set tblpdtenakmenbahagians) {
		super(idEnakmenpenggal, tblpdtenakmen, tajukPenggal,noPenggal, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanenakmenpenggals, tblpdtenakmenbahagians);
	}

}
