package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpperjanjiantambahan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpperjanjiantambahan extends AbstractTblhtpperjanjiantambahan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpperjanjiantambahan() {
	}

	/** minimal constructor */
	public Tblhtpperjanjiantambahan(Long idPerjanjiantambahan,
			Tblhtppermohonan tblhtppermohonan, Long idPerjanjian) {
		super(idPerjanjiantambahan, tblhtppermohonan, idPerjanjian);
	}

	/** full constructor */
	public Tblhtpperjanjiantambahan(Long idPerjanjiantambahan,
			Tblhtppermohonan tblhtppermohonan, Long idPerjanjian,
			Date tarikhTandatangan, Date tarikhTerima, Date tarikhPerjanjian,
			String sebab, String tempoh, String ulasan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idPerjanjiantambahan, tblhtppermohonan, idPerjanjian,
				tarikhTandatangan, tarikhTerima, tarikhPerjanjian, sebab,
				tempoh, ulasan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
