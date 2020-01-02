package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpfdpergerakanfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpfdpergerakanfail extends AbstractTblpfdpergerakanfail
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdpergerakanfail() {
	}

	/** minimal constructor */
	public Tblpfdpergerakanfail(Long idPergerakanFail,Long idFail) {
		super(idPergerakanFail,idFail);
	}

	/** full constructor */
	public Tblpfdpergerakanfail(Long idPergerakanFail, Long idFail,
			Date tarikhFailMasuk, Date tarikhFailKeluar,
			Long idPegawaipenerima, Long idPegawaipenghantar,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Date tempohSdpDari,Date tempohSdpHingga,String catatan,
			Long idStatus, Long idDb) {
		super(idPergerakanFail, idFail, tarikhFailMasuk, tarikhFailKeluar,
				idPegawaipenerima, idPegawaipenghantar, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, tempohSdpDari, tempohSdpHingga, catatan,
				idStatus, idDb);
	}

}
