package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujdb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujdb extends AbstractTblrujdb implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujdb() {
	}

	/** minimal constructor */
	public Tblrujdb(Long idDb) {
		super(idDb);
	}

	/** full constructor */
	public Tblrujdb(Long idDb, String kodDb, String keterangan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idDb, kodDb, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
