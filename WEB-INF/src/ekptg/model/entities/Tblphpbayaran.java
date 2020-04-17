package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpbayaran extends AbstractTblphpbayaran implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpbayaran() {
	}

	/** minimal constructor */
	public Tblphpbayaran(Long idBayaran) {
		super(idBayaran);
	}

	/** full constructor */
	public Tblphpbayaran(Long idBayaran,
			Tblphpbayaranperludibayar tblphpbayaranperludibayar,
			Double amaunDibayar, Long idCarabayar, String noResit,
			Date tarikhResit, String noCek, Date tarikhCek, Date tarikhLuput,
			String statusBayar, String namaBank, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppenyelarasanbayarans) {
		super(idBayaran, tblphpbayaranperludibayar, amaunDibayar, idCarabayar,
				noResit, tarikhResit, noCek, tarikhCek, tarikhLuput,
				statusBayar, namaBank, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblphppenyelarasanbayarans);
	}

}
