package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkrujjenisperintah entity. @author MyEclipse Persistence Tools
 */
public class Tblppkrujjenisperintah extends AbstractTblppkrujjenisperintah implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkrujjenisperintah() {
	}

	/** full constructor */
	public Tblppkrujjenisperintah(String kod, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(kod, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
