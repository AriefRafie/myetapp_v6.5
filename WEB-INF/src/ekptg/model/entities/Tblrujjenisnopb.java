package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujjenisnopb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujjenisnopb extends AbstractTblrujjenisnopb implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenisnopb() {
	}

	/** minimal constructor */
	public Tblrujjenisnopb(Long idJenisnopb) {
		super(idJenisnopb);
	}

	/** full constructor */
	public Tblrujjenisnopb(Long idJenisnopb, String kodJenisNopb,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idJenisnopb, kodJenisNopb, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
