package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppknotiskolateral entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppknotiskolateral extends AbstractTblppknotiskolateral
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppknotiskolateral() {
	}

	/** minimal constructor */
	public Tblppknotiskolateral(Long idNotiskolateral,
			Tblppknotisobmst tblppknotisobmst,
			Tblppkkolateralmst tblppkkolateralmst) {
		super(idNotiskolateral, tblppknotisobmst, tblppkkolateralmst);
	}

	/** full constructor */
	public Tblppknotiskolateral(Long idNotiskolateral,
			Tblppknotisobmst tblppknotisobmst,
			Tblppkkolateralmst tblppkkolateralmst, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idNotiskolateral, tblppknotisobmst, tblppkkolateralmst, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
