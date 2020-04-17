package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujenisrizab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujenisrizab extends AbstractTblrujenisrizab implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujenisrizab() {
	}

	/** minimal constructor */
	public Tblrujenisrizab(Long idRizab) {
		super(idRizab);
	}

	/** full constructor */
	public Tblrujenisrizab(Long idRizab, String kodRizab, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idRizab, kodRizab, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
