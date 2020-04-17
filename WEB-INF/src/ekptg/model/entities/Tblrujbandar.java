package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujbandar entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujbandar extends AbstractTblrujbandar implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujbandar() {
	}

	/** minimal constructor */
	public Tblrujbandar(Long idBandar) {
		super(idBandar);
	}

	/** full constructor */
	public Tblrujbandar(Long idBandar, String kodBandar, String keterangan,
			Long idNegeri, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idBandar, kodBandar, keterangan, idNegeri, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
