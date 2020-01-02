package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpdokumenperjanjian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpdokumenperjanjian extends AbstractTblhtpdokumenperjanjian
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpdokumenperjanjian() {
	}

	/** minimal constructor */
	public Tblhtpdokumenperjanjian(Long idDokumenperjanjian,
			Tblhtppermohonan tblhtppermohonan, Long idMasuk) {
		super(idDokumenperjanjian, tblhtppermohonan, idMasuk);
	}

	/** full constructor */
	public Tblhtpdokumenperjanjian(Long idDokumenperjanjian,
			Tblhtppermohonan tblhtppermohonan, Date tarikhHantar,
			Date tarikhTerima, String flag, String pihak, String ulasan,
			Long idMasuk, Date tarikhMasuk, String idKemaskini,
			Date tarkhKemaskini) {
		super(idDokumenperjanjian, tblhtppermohonan, tarikhHantar,
				tarikhTerima, flag, pihak, ulasan, idMasuk, tarikhMasuk,
				idKemaskini, tarkhKemaskini);
	}

}
