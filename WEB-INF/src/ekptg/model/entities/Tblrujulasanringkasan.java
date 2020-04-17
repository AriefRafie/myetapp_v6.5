package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujulasanringkasan entity. @author MyEclipse Persistence Tools
 */
public class Tblrujulasanringkasan extends AbstractTblrujulasanringkasan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujulasanringkasan() {
	}

	/** full constructor */
	public Tblrujulasanringkasan(String kodUlasanringkasan, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(kodUlasanringkasan, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
