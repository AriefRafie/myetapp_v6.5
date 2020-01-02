package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujpfdtarafkeselamatan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujpfdtarafkeselamatan extends
		AbstractTblrujpfdtarafkeselamatan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujpfdtarafkeselamatan() {
	}

	/** minimal constructor */
	public Tblrujpfdtarafkeselamatan(Long idTarafKeselamatan) {
		super(idTarafKeselamatan);
	}

	/** full constructor */
	public Tblrujpfdtarafkeselamatan(Long idTarafKeselamatan,
			String kodTarafKeselamatan, String tarafKeselamatan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpfdfails) {
		super(idTarafKeselamatan, kodTarafKeselamatan, tarafKeselamatan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, tblpfdfails);
	}

}
