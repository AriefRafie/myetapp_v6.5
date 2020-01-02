package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblhtppajakankadar entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppajakankadar extends AbstractTblhtppajakankadar implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppajakankadar() {
	}

	/** minimal constructor */
	public Tblhtppajakankadar(Long idPajakankadar, Tblhtppajakan tblhtppajakan,
			Tblhtphakmilikcukai tblhtphakmilikcukai) {
		super(idPajakankadar, tblhtppajakan, tblhtphakmilikcukai);
	}

	/** full constructor */
	public Tblhtppajakankadar(Long idPajakankadar, Tblhtppajakan tblhtppajakan,
			Tblhtphakmilikcukai tblhtphakmilikcukai, Date tarikhMulaPajak,
			Date tarikhTamatpajak, Double kadarBayarPajak, Double kadarCukai,
			Date tarikhSemakan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblhtpajakanbilsi) {
		super(idPajakankadar, tblhtppajakan, tblhtphakmilikcukai,
				tarikhMulaPajak, tarikhTamatpajak, kadarBayarPajak, kadarCukai,
				tarikhSemakan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblhtpajakanbilsi);
	}

}
