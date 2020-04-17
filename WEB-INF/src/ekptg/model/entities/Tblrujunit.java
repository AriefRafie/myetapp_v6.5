package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujunit entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujunit extends AbstractTblrujunit implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujunit() {
	}

	/** minimal constructor */
	public Tblrujunit(Long idUnit) {
		super(idUnit);
	}

	/** full constructor */
	public Tblrujunit(Long idUnit, String kodUnit, String namaUnit,
			String versiUnit, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idUnit, kodUnit, namaUnit, versiUnit, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
