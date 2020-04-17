package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktabab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktabab extends AbstractTblpdtaktabab implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktabab() {
	}

	/** minimal constructor */
	public Tblpdtaktabab(Long idAktabab) {
		super(idAktabab);
	}

	/** full constructor */
	public Tblpdtaktabab(Long idAktabab, Tblpdtaktabahagian tblpdtaktabahagian,
			String tajukBab,String noBab, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanaktababs,
			Set tblpdtaktaseksyens) {
		super(idAktabab, tblpdtaktabahagian, tajukBab,noBab, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblpdtpindaanaktababs,
				tblpdtaktaseksyens);
	}

}
