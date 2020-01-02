package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblhtpcukaiutama entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpcukaiutama extends AbstractTblhtpcukaiutama implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpcukaiutama() {
	}

	/** minimal constructor */
	public Tblhtpcukaiutama(Long idCukaiutama,
			Tblhtpperingkatbayaran tblhtpperingkatbayaran, Long idNegeri,
			Long idDaerah) {
		super(idCukaiutama, tblhtpperingkatbayaran, idNegeri, idDaerah);
	}

	/** full constructor */
	public Tblhtpcukaiutama(Long idCukaiutama,
			Tblhtpperingkatbayaran tblhtpperingkatbayaran, Double jumlagCukai,
			Long jumlahHakmilik, Double amaunBayaranCukai, String tahun,
			Long idNegeri, Long idDaerah, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblhtpbaucers,
			Set tblhtpcukaiterperincis) {
		super(idCukaiutama, tblhtpperingkatbayaran, jumlagCukai,
				jumlahHakmilik, amaunBayaranCukai, tahun, idNegeri, idDaerah,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblhtpbaucers, tblhtpcukaiterperincis);
	}

}
