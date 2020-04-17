package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujnegeri entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujnegeri extends AbstractTblrujnegeri implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujnegeri() {
	}

	/** minimal constructor */
	public Tblrujnegeri(Long idNegeri, Long idNegara) {
		super(idNegeri, idNegara);
	}

	/** full constructor */
	public Tblrujnegeri(Long idNegeri, String kodNegeri, String namaNegeri,
			Long idNegara, String kodMampu, String abbrev, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idNegeri, kodNegeri, namaNegeri, idNegara, kodMampu, abbrev,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
