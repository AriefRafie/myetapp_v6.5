package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkperintah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkperintah extends AbstractTblppkperintah implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkperintah() {
	}

	/** minimal constructor */
	public Tblppkperintah(Long idPerintah, Tblppkperbicaraan tblppkperbicaraan) {
		super(idPerintah, tblppkperbicaraan);
	}

	/** full constructor */
	public Tblppkperintah(Long idPerintah,
			Tblrujppkjenisperintah tblrujppkjenisperintah,
			Tblppkperbicaraan tblppkperbicaraan, Tblrujppkunit tblrujppkunit,
			Date tarikhPerintah, Long idNegeri, String notisGeran,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkperintahhtaobmsts,
			Set tblppkperintahhaobmsts) {
		super(idPerintah, tblrujppkjenisperintah, tblppkperbicaraan,
				tblrujppkunit, tarikhPerintah, idNegeri, notisGeran, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblppkperintahhtaobmsts, tblppkperintahhaobmsts);
	}

}
