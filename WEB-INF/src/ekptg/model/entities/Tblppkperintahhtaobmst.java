package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkperintahhtaobmst entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkperintahhtaobmst extends AbstractTblppkperintahhtaobmst
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkperintahhtaobmst() {
	}

	/** minimal constructor */
	public Tblppkperintahhtaobmst(Long idPerintahhtaobmst,
			Tblppkperintah tblppkperintah, Tblppkhta tblppkhta) {
		super(idPerintahhtaobmst, tblppkperintah, tblppkhta);
	}

	/** full constructor */
	public Tblppkperintahhtaobmst(Long idPerintahhtaobmst,
			Tblppkperintah tblppkperintah, Tblppkhta tblppkhta, String catatan,
			Double nilaiBersih, Double cukaiHarta, String namaPembayarCukai,
			Date tarikhJualan, Double amaun, String jenisLelong,
			Double hargaRizab, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkperintahhtaobdtls) {
		super(idPerintahhtaobmst, tblppkperintah, tblppkhta, catatan,
				nilaiBersih, cukaiHarta, namaPembayarCukai, tarikhJualan,
				amaun, jenisLelong, hargaRizab, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblppkperintahhtaobdtls);
	}

}
