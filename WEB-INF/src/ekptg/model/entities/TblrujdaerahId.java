package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujdaerahId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujdaerahId extends AbstractTblrujdaerahId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujdaerahId() {
	}

	/** minimal constructor */
	public TblrujdaerahId(Long idDaerah) {
		super(idDaerah);
	}

	/** full constructor */
	public TblrujdaerahId(Long idDaerah, String kodDaerah, String kodDaerahPTG, String namaDaerah,
			Long idNegeri, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idDaerah, kodDaerah, kodDaerahPTG, namaDaerah, idNegeri, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
