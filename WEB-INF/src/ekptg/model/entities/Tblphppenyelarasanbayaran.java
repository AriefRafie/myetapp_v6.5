package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppenyelarasanbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppenyelarasanbayaran extends
		AbstractTblphppenyelarasanbayaran implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppenyelarasanbayaran() {
	}

	/** minimal constructor */
	public Tblphppenyelarasanbayaran(Long idPenyelarasanbayaran) {
		super(idPenyelarasanbayaran);
	}

	/** full constructor */
	public Tblphppenyelarasanbayaran(Long idPenyelarasanbayaran,
			Tblphpbayaranperludibayar tblphpbayaranperludibayar,
			Tblphpbayaran tblphpbayaran, Double bakiBawakehadapan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPenyelarasanbayaran, tblphpbayaranperludibayar, tblphpbayaran,
				bakiBawakehadapan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
