package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujpihakberkepentingan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujpihakberkepentingan extends
		AbstractTblrujpihakberkepentingan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujpihakberkepentingan() {
	}

	/** minimal constructor */
	public Tblrujpihakberkepentingan(Long idRujpihakberkepentingan) {
		super(idRujpihakberkepentingan);
	}

	/** full constructor */
	public Tblrujpihakberkepentingan(Long idRujpihakberkepentingan,
			String kodNopb, String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idRujpihakberkepentingan, kodNopb, keterangan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
