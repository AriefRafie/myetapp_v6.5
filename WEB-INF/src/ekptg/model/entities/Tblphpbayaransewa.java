package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpbayaransewa entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpbayaransewa extends AbstractTblphpbayaransewa implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpbayaransewa() {
	}

	/** minimal constructor */
	public Tblphpbayaransewa(Long idBayaransewa) {
		super(idBayaransewa);
	}

	/** full constructor */
	public Tblphpbayaransewa(Long idBayaransewa,
			Tblphpperjanjianpenyewaan tblphpperjanjianpenyewaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Double kadarSewa, Set tblphpbayaranperludibayars) {
		super(idBayaransewa, tblphpperjanjianpenyewaan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, kadarSewa,
				tblphpbayaranperludibayars);
	}

}
