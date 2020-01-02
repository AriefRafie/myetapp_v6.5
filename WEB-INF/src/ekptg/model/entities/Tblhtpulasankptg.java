package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpulasankptg entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpulasankptg extends AbstractTblhtpulasankptg implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpulasankptg() {
	}

	/** minimal constructor */
	public Tblhtpulasankptg(Long idUlasankptg, Tblhtppermohonan tblhtppermohonan) {
		super(idUlasankptg, tblhtppermohonan);
	}

	/** full constructor */
	public Tblhtpulasankptg(Long idUlasankptg,
			Tblhtppermohonan tblhtppermohonan, Date tarikhHantar,
			String status, String ulasan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idUlasankptg, tblhtppermohonan, tarikhHantar, status, ulasan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
