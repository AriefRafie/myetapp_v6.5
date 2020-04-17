package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujdaerahId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujbandarId extends AbstractTblrujbandarId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujbandarId() {
	}

	/** minimal constructor */
	public TblrujbandarId(Long idBandar) {
		super(idBandar);
	}

	/** full constructor */
	public TblrujbandarId(Long idBandar, String kodBandar, String keterangan,
			Long idNegeri, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idBandar, kodBandar, keterangan, idNegeri, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
