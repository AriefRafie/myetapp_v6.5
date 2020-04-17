package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkkolateralmst entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkkolateralmst extends AbstractTblppkkolateralmst implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkkolateralmst() {
	}

	/** minimal constructor */
	public Tblppkkolateralmst(Long idKolateralmst,
			Tblppkperbicaraan tblppkperbicaraan) {
		super(idKolateralmst, tblppkperbicaraan);
	}

	/** full constructor */
	public Tblppkkolateralmst(Long idKolateralmst,
			Tblppkperbicaraan tblppkperbicaraan, Date tarikhBicara,
			String sebabPertelingkahan, String catatan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkkolateraldtls, Set tblppknotiskolaterals) {
		super(idKolateralmst, tblppkperbicaraan, tarikhBicara,
				sebabPertelingkahan, catatan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblppkkolateraldtls,
				tblppknotiskolaterals);
	}

}
