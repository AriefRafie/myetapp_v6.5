package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujlot entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujlot extends AbstractTblrujlot implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujlot() {
	}

	/** minimal constructor */
	public Tblrujlot(Long idLot) {
		super(idLot);
	}

	/** full constructor */
	public Tblrujlot(Long idLot, String kodLot, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idLot, kodLot, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
