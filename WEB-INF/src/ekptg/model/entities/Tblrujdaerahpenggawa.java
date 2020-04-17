package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujdaerahpenggawa entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class Tblrujdaerahpenggawa extends AbstractTblrujdaerahpenggawa implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujdaerahpenggawa() {
	}

	/** minimal constructor */
	public Tblrujdaerahpenggawa(Long idDaerahPenggawa) {
		super(idDaerahPenggawa);
	}

	/** full constructor */
	public Tblrujdaerahpenggawa(Long idDaerahPenggawa, String kodDaerahPenggawa, String namaDaerahPenggawa,
			Long idDaerah, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idDaerahPenggawa, kodDaerahPenggawa, namaDaerahPenggawa, idDaerah, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
