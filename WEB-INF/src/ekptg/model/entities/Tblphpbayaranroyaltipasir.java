package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpbayaranroyaltipasir entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpbayaranroyaltipasir extends
		AbstractTblphpbayaranroyaltipasir implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpbayaranroyaltipasir() {
	}

	/** minimal constructor */
	public Tblphpbayaranroyaltipasir(Long idBayaranroyaltipasir) {
		super(idBayaranroyaltipasir);
	}

	/** full constructor */
	public Tblphpbayaranroyaltipasir(Long idBayaranroyaltipasir,
			Tblphplaporanpasir tblphplaporanpasir, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Double jumlahRoyalti, Set tblphpbayaranperludibayars) {
		super(idBayaranroyaltipasir, tblphplaporanpasir, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, jumlahRoyalti,
				tblphpbayaranperludibayars);
	}

}
