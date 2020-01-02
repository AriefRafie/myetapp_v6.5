package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujseksyenId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujunitId extends AbstractTblrujunitId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujunitId() {
	}

	/** minimal constructor */
	public TblrujunitId(Long idUnit) {
		super(idUnit);
	}

	/** full constructor */
	public TblrujunitId(Long idUnit, String kodUnit,
			String namaUnit, String versiUnit, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idUnit, kodUnit, namaUnit, versiUnit, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}

