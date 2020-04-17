package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujseksyenId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujseksyenId extends AbstractTblrujseksyenId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujseksyenId() {
	}

	/** minimal constructor */
	public TblrujseksyenId(Long idSeksyen) {
		super(idSeksyen);
	}

	/** full constructor */
	public TblrujseksyenId(Long idSeksyen, String kodSeksyen,
			String namaSeksyen, String versiSeksyen, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idSeksyen, kodSeksyen, namaSeksyen, versiSeksyen, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
