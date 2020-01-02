package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpergerakanfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpergerakanfail extends AbstractTblpergerakanfail implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpergerakanfail() {
	}

	/** full constructor */
	public Tblpergerakanfail(String kodFail, Date tarikhPergerakan,
			String kodPenghantar, String kodPenerima, Date tarikhHantar,
			Date tarikhTerima, String kodStatusPergerakan, String catatan) {
		super(kodFail, tarikhPergerakan, kodPenghantar, kodPenerima,
				tarikhHantar, tarikhTerima, kodStatusPergerakan, catatan);
	}

}
