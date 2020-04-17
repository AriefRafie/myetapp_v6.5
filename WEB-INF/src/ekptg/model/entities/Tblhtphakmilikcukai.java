package ekptg.model.entities;

import java.util.Set;

/**
 * Tblhtphakmilikcukai entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtphakmilikcukai extends AbstractTblhtphakmilikcukai implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtphakmilikcukai() {
	}

	/** minimal constructor */
	public Tblhtphakmilikcukai(Long idHakmilikcukai, Long idHakmilikpegangan,
			Long idMasuk) {
		super(idHakmilikcukai, idHakmilikpegangan, idMasuk);
	}

	/** full constructor */
	public Tblhtphakmilikcukai(Long idHakmilikcukai, Long idHakmilikpegangan,
			Double luas, Double cukai, String luasPetak,
			String kodBayarancukai, Double cukaiTaliair, Double cukaiParit,
			Double denda, Double pengurangan, Double pengecualian,
			String statusProsescukai, Double bayaranLain, String status,
			Long idMasuk, Long tarikhMasuk, Set tblhtpcukaiterperincis,
			Set tblhtppajakankadars) {
		super(idHakmilikcukai, idHakmilikpegangan, luas, cukai, luasPetak,
				kodBayarancukai, cukaiTaliair, cukaiParit, denda, pengurangan,
				pengecualian, statusProsescukai, bayaranLain, status, idMasuk,
				tarikhMasuk, tblhtpcukaiterperincis, tblhtppajakankadars);
	}

}
