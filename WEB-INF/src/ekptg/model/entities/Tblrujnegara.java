package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujnegara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujnegara extends AbstractTblrujnegara implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujnegara() {
	}

	/** minimal constructor */
	public Tblrujnegara(Long idNegara) {
		super(idNegara);
	}

	/** full constructor */
	public Tblrujnegara(Long idNegara, String kodNegara, String namaNegara,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idNegara, kodNegara, namaNegara, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
