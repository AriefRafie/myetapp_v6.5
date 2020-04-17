package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkkolateraldtl entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkkolateraldtl extends AbstractTblppkkolateraldtl implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkkolateraldtl() {
	}

	/** minimal constructor */
	public Tblppkkolateraldtl(Long idKolateraldtl,
			Tblppkkolateralmst tblppkkolateralmst, Tblppkob tblppkob) {
		super(idKolateraldtl, tblppkkolateralmst, tblppkob);
	}

	/** full constructor */
	public Tblppkkolateraldtl(Long idKolateraldtl,
			Tblppkkolateralmst tblppkkolateralmst, Tblppkob tblppkob,
			String jenisOb, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idKolateraldtl, tblppkkolateralmst, tblppkob, jenisOb, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
