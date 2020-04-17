package ekptg.model.entities;

import java.util.Date;

/**
 * Tblaktiviti entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblaktiviti extends AbstractTblaktiviti implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblaktiviti() {
	}

	/** full constructor */
	public Tblaktiviti(String kodLangkah, String kodPengguna, Date tarikhMula,
			Date tarikhSelesai, String kodStatusAktiviti, String catatan,
			String kodProses) {
		super(kodLangkah, kodPengguna, tarikhMula, tarikhSelesai,
				kodStatusAktiviti, catatan, kodProses);
	}

}
