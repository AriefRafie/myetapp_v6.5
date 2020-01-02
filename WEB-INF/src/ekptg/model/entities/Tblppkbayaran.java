package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkbayaran extends AbstractTblppkbayaran implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkbayaran() {
	}

	/** minimal constructor */
	public Tblppkbayaran(Long idBayaran) {
		super(idBayaran);
	}

	/** full constructor */
	public Tblppkbayaran(Long idBayaran,Long idPermohonan,
			Long idJenisbayar, String noResit, Date tarikhBayaran,
			Double jumlahBayaran, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangphas) {
		super(idBayaran,idPermohonan, idJenisbayar, noResit,
				tarikhBayaran, jumlahBayaran, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblppkborangphas);
	}

}
