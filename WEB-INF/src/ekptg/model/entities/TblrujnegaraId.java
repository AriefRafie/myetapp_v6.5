package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujnegaraId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujnegaraId extends AbstractTblrujnegaraId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujnegaraId() {
	}

	/** minimal constructor */
	public TblrujnegaraId(Long idNegara) {
		super(idNegara);
	}

	/** full constructor */
	public TblrujnegaraId(Long idNegara, String kodNegara, String namaNegara,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idNegara, kodNegara, namaNegara, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
