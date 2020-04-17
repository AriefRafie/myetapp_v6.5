package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujdaerahpenggawaId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class TblrujdaerahpenggawaId extends AbstractTblrujdaerahpenggawaId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujdaerahpenggawaId() {
	}

	/** minimal constructor */
	public TblrujdaerahpenggawaId(Long idDaerahPenggawa) {
		super(idDaerahPenggawa);
	}

	/** full constructor */
	public TblrujdaerahpenggawaId(Long idDaerahPenggawa, String kodDaerahPenggawa, String namaDaerahPenggawa,
			Long idDaerah, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idDaerahPenggawa, kodDaerahPenggawa, namaDaerahPenggawa, idDaerah, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
