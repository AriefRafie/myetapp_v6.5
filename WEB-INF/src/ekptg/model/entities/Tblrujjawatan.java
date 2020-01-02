package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujjawatan entity. @author MyEclipse Persistence Tools
 */
public class Tblrujjawatan extends AbstractTblrujjawatan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjawatan() {
	}

	/** full constructor */
	public Tblrujjawatan(String kodJawatan, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(kodJawatan, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
