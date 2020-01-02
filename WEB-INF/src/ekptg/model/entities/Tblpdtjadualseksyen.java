package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtjadualseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtjadualseksyen extends AbstractTblpdtjadualseksyen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtjadualseksyen() {
	}

	/** minimal constructor */
	public Tblpdtjadualseksyen(Long idJadualseksyen) {
		super(idJadualseksyen);
	}

	/** full constructor */
	public Tblpdtjadualseksyen(Long idJadualseksyen, Tblpdtjadual tblpdtjadual,
			Long noSeksyen, String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpdtjadualperenggans) {
		super(idJadualseksyen, tblpdtjadual, noSeksyen, namaSeksyen, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtjadualperenggans);
	}

}
