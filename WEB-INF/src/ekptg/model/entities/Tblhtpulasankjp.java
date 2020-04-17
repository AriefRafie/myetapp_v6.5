package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpulasankjp entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpulasankjp extends AbstractTblhtpulasankjp implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpulasankjp() {
	}

	/** minimal constructor */
	public Tblhtpulasankjp(Long idUlasankjp, Tblhtppermohonan tblhtppermohonan) {
		super(idUlasankjp, tblhtppermohonan);
	}

	/** full constructor */
	public Tblhtpulasankjp(Long idUlasankjp, Tblhtppermohonan tblhtppermohonan,
			Date tarikhHantar, Date tarikhTerima, Date tarikhSuratKeputusan,
			String ulasan, String statusKeputusan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idUlasankjp, tblhtppermohonan, tarikhHantar, tarikhTerima,
				tarikhSuratKeputusan, ulasan, statusKeputusan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
