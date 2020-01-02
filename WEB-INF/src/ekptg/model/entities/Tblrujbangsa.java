package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujbangsa entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujbangsa extends AbstractTblrujbangsa implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujbangsa() {
	}

	/** minimal constructor */
	public Tblrujbangsa(Long idBangsa) {
		super(idBangsa);
	}

	/** full constructor */
	public Tblrujbangsa(Long idBangsa, String kodBangsa, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idBangsa, kodBangsa, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
