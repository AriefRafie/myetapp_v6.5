package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpborang30a entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpborang30a extends AbstractTblhtpborang30a implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpborang30a() {
	}

	/** minimal constructor */
	public Tblhtpborang30a(Long idBorang30a, Tblhtppermohonan tblhtppermohonan) {
		super(idBorang30a, tblhtppermohonan);
	}

	/** full constructor */
	public Tblhtpborang30a(Long idBorang30a, Tblhtppermohonan tblhtppermohonan,
			Date tarikhSurat, Date tarikhTerima, Date tarikhHantar,
			Date tarikhTandatanganPtp, Date tarikhDaftar, String noPeserahan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idBorang30a, tblhtppermohonan, tarikhSurat, tarikhTerima,
				tarikhHantar, tarikhTandatanganPtp, tarikhDaftar, noPeserahan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
