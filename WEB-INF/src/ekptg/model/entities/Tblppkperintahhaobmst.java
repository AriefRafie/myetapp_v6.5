package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkperintahhaobmst entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkperintahhaobmst extends AbstractTblppkperintahhaobmst
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkperintahhaobmst() {
	}

	/** minimal constructor */
	public Tblppkperintahhaobmst(Long idPerintahhaobmst,
			Tblppkperintah tblppkperintah) {
		super(idPerintahhaobmst, tblppkperintah);
	}

	/** full constructor */
	public Tblppkperintahhaobmst(Long idPerintahhaobmst,
			Tblppkperintah tblppkperintah, String catatan, Double nilaiBersih,
			Double cukaiHarta, String namaPembayarCukai, Date tarikhJualan,
			Double amaun, String jenisLelong, Double hargaRizab, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkperintahhaobdtls) {
		super(idPerintahhaobmst, tblppkperintah, catatan, nilaiBersih,
				cukaiHarta, namaPembayarCukai, tarikhJualan, amaun,
				jenisLelong, hargaRizab, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblppkperintahhaobdtls);
	}

}
