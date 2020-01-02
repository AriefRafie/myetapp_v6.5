package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpbayaranperludibayar entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpbayaranperludibayar extends
		AbstractTblphpbayaranperludibayar implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpbayaranperludibayar() {
	}

	/** minimal constructor */
	public Tblphpbayaranperludibayar(Long idBayaranperludibayar) {
		super(idBayaranperludibayar);
	}

	/** full constructor */
	public Tblphpbayaranperludibayar(Long idBayaranperludibayar,
			Tblphpbayaransewa tblphpbayaransewa,
			Tblphpbayaranroyaltipasir tblphpbayaranroyaltipasir,
			Double amaunPerludibayar, Double amaunSemasa,
			Double amaunTunggakan, Long bulanTunggakDari,
			Long tahunTunggakDari, Long bulanTunggakHingga,
			Long tahunTunggakHingga, Double bakiAwal, Double bakiAkhir,
			Long idHasil, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphppenyelarasanbayarans,
			Set tblphpbayarans, Set tblphpbils) {
		super(idBayaranperludibayar, tblphpbayaransewa,
				tblphpbayaranroyaltipasir, amaunPerludibayar, amaunSemasa,
				amaunTunggakan, bulanTunggakDari, tahunTunggakDari,
				bulanTunggakHingga, tahunTunggakHingga, bakiAwal, bakiAkhir,
				idHasil, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblphppenyelarasanbayarans, tblphpbayarans, tblphpbils);
	}

}
