package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkperintahhtaobdtl entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkperintahhtaobdtl extends AbstractTblppkperintahhtaobdtl
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkperintahhtaobdtl() {
	}

	/** minimal constructor */
	public Tblppkperintahhtaobdtl(Long idPerintahhtaobdtl,
			Tblppkperintahhtaobmst tblppkperintahhtaobmst, Tblppkob tblppkob) {
		super(idPerintahhtaobdtl, tblppkperintahhtaobmst, tblppkob);
	}

	/** full constructor */
	public Tblppkperintahhtaobdtl(Long idPerintahhtaobdtl,
			Tblppkperintahhtaobmst tblppkperintahhtaobmst, Tblppkob tblppkob,
			Long ba, Long bb, Long pekali, String minor, Long idPenjaga1,
			String kaveat1, String batalPa1, Long idPenjaga2, String kaveat2,
			String batalPa2, Long idPenjaga3, String kaveat3, String batalPa3,
			Long idPenjaga4, String kaveat4, String batalPa4,
			String statusTadbir, String catatan, Long idPa1, Long idPa2,
			Long idPa3, Long idPa4, String wakil, String keteranganOb,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPerintahhtaobdtl, tblppkperintahhtaobmst, tblppkob, ba, bb,
				pekali, minor, idPenjaga1, kaveat1, batalPa1, idPenjaga2,
				kaveat2, batalPa2, idPenjaga3, kaveat3, batalPa3, idPenjaga4,
				kaveat4, batalPa4, statusTadbir, catatan, idPa1, idPa2, idPa3,
				idPa4, wakil, keteranganOb, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
