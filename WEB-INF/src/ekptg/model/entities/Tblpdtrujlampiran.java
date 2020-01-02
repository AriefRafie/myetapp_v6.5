package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpdtrujlampiran entity. @author MyEclipse Persistence Tools
 */
public class Tblpdtrujlampiran extends AbstractTblpdtrujlampiran implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtrujlampiran() {
	}

	/** full constructor */
	public Tblpdtrujlampiran(Long idDokumen, String content,
			String namaFail, String jenisMime, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idDokumen, content, namaFail, jenisMime, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
