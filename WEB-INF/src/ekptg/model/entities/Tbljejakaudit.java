package ekptg.model.entities;

import java.util.Date;

/**
 * Tbljejakaudit entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tbljejakaudit extends AbstractTbljejakaudit implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tbljejakaudit() {
	}

	/** full constructor */
	public Tbljejakaudit(Date tarikhAktiviti, String kodPengguna,
			String kodSkrin, String catatan) {
		super(tarikhAktiviti, kodPengguna, kodSkrin, catatan);
	}

}
