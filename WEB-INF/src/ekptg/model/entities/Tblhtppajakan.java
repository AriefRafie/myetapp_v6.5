package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblhtppajakan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppajakan extends AbstractTblhtppajakan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppajakan() {
	}

	/** minimal constructor */
	public Tblhtppajakan(Long idPajakan, Tblhtppermohonan tblhtppermohonan) {
		super(idPajakan, tblhtppermohonan);
	}

	/** full constructor */
	public Tblhtppajakan(Long idPajakan, Tblhtppermohonan tblhtppermohonan,
			String tujuan, String tempohPajakan, Date tarikhMulaPajakan,
			Date tarikhTamatpajakan, String caraBayar, String kategoriCukai,
			Double kadarCukai, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblhtppajakankadars) {
		super(idPajakan, tblhtppermohonan, tujuan, tempohPajakan,
				tarikhMulaPajakan, tarikhTamatpajakan, caraBayar,
				kategoriCukai, kadarCukai, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblhtppajakankadars);
	}

}
