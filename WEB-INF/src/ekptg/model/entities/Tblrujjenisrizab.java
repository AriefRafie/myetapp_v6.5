package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujjenisrizab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujjenisrizab extends AbstractTblrujjenisrizab implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenisrizab() {
	}

	/** minimal constructor */
	public Tblrujjenisrizab(Long idRizab) {
		super(idRizab);
	}

	/** full constructor */
	public Tblrujjenisrizab(Long idRizab, String kodRizab, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idRizab, kodRizab, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
