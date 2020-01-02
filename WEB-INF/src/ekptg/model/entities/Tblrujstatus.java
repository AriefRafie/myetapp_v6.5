package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujstatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujstatus extends AbstractTblrujstatus implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujstatus() {
	}

	/** full constructor */
	public Tblrujstatus(String kodStatus, String keterangan, Long idMasuk,
			Long idKemaskini, Date tarikhMasuk, Date tarikhKemaskini,
			Long idSeksyen) {
		super(kodStatus, keterangan, idMasuk, idKemaskini, tarikhMasuk,
				tarikhKemaskini, idSeksyen);
	}

}
