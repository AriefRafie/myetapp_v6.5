package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpdtrujlokasimesyuarat entity. @author MyEclipse Persistence Tools
 */
public class Tblpdtrujlokasimesyuarat extends AbstractTblpdtrujlokasimesyuarat
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtrujlokasimesyuarat() {
	}

	/** full constructor */
	public Tblpdtrujlokasimesyuarat(String lokasi, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idNegeri, Long idSeksyen) {
		super(lokasi, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				idNegeri, idSeksyen);
	}

}
