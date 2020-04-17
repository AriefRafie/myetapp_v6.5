package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujpfdseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujpfdseksyen extends AbstractTblrujpfdseksyen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujpfdseksyen() {
	}

	/** minimal constructor */
	public Tblrujpfdseksyen(Long idSeksyen) {
		super(idSeksyen);
	}

	/** full constructor */
	public Tblrujpfdseksyen(Long idSeksyen, String kodSeksyen,
			String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpfdfails) {
		super(idSeksyen, kodSeksyen, namaSeksyen, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblpfdfails);
	}

}
