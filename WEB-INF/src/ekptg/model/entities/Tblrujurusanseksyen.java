package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujurusanseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujurusanseksyen extends AbstractTblrujurusanseksyen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujurusanseksyen() {
	}

	/** minimal constructor */
	public Tblrujurusanseksyen(Long idUrusanseksyen, Long idUrusan) {
		super(idUrusanseksyen, idUrusan);
	}

	/** full constructor */
	public Tblrujurusanseksyen(Long idUrusanseksyen, Long idUrusan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idUrusanseksyen, idUrusan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
