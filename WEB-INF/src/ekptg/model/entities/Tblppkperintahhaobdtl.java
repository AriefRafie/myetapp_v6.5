package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkperintahhaobdtl entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkperintahhaobdtl extends AbstractTblppkperintahhaobdtl
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkperintahhaobdtl() {
	}

	/** minimal constructor */
	public Tblppkperintahhaobdtl(Long idPerintahhaobdtl,
			Tblppkperintahhaobmst tblppkperintahhaobmst, Tblppkob tblppkob) {
		super(idPerintahhaobdtl, tblppkperintahhaobmst, tblppkob);
	}

	/** full constructor */
	public Tblppkperintahhaobdtl(Long idPerintahhaobdtl,
			Tblppkperintahhaobmst tblppkperintahhaobmst, Tblppkob tblppkob,
			Long idHa, Date tarikhPerintah, String minor, Long ba, Long bb,
			Long pekali, Long idPenjaga1, String batalPa1, Long idPenjaga2,
			String batalPa2, Long idPenjaga3, String batalPa3, Long idPenjaga4,
			String batalPa4, String statusTadbir, String catatan, Long idPa1,
			Long idPa2, Long idPa3, Long idPa4, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idPerintahhaobdtl, tblppkperintahhaobmst, tblppkob, idHa,
				tarikhPerintah, minor, ba, bb, pekali, idPenjaga1, batalPa1,
				idPenjaga2, batalPa2, idPenjaga3, batalPa3, idPenjaga4,
				batalPa4, statusTadbir, catatan, idPa1, idPa2, idPa3, idPa4,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
