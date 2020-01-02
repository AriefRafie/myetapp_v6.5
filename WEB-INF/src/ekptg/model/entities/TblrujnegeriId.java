package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujnegeriId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujnegeriId extends AbstractTblrujnegeriId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujnegeriId() {
	}

	/** minimal constructor */
	public TblrujnegeriId(Long idNegeri, Long idNegara) {
		super(idNegeri, idNegara);
	}

	/** full constructor */
	public TblrujnegeriId(Long idNegeri, Long idNegara, String kodNegeri,
			String namaNegeri, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idNegeri, idNegara, kodNegeri, namaNegeri, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
