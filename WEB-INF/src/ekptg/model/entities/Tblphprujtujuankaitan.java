package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphprujtujuankaitan entity. @author MyEclipse Persistence Tools
 */
public class Tblphprujtujuankaitan extends AbstractTblphprujtujuankaitan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphprujtujuankaitan() {
	}

	/** full constructor */
	public Tblphprujtujuankaitan(String kodTujuankaitan, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(kodTujuankaitan, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
