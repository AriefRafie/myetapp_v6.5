package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujdaerah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujdaerah extends AbstractTblrujdaerah implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujdaerah() {
	}

	/** minimal constructor */
	public Tblrujdaerah(Long idDaerah) {
		super(idDaerah);
	}

	/** full constructor */
	public Tblrujdaerah(Long idDaerah, String kodDaerah, String kodDaerahPTG, String namaDaerah,
			Long idNegeri, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idDaerah, kodDaerah, kodDaerahPTG, namaDaerah, idNegeri, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
