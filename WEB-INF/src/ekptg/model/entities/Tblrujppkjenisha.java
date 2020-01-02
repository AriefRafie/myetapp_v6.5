package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujppkjenisha entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujppkjenisha extends AbstractTblrujppkjenisha implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujppkjenisha() {
	}

	/** minimal constructor */
	public Tblrujppkjenisha(Long idJenisha) {
		super(idJenisha);
	}

	/** full constructor */
	public Tblrujppkjenisha(Long idJenisha, String kod, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkborangahas, Set tblppkborangphas,
			Set tblppkhas) {
		super(idJenisha, kod, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblppkborangahas, tblppkborangphas, tblppkhas);
	}

}
