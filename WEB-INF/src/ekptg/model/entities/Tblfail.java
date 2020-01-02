package ekptg.model.entities;

import java.util.Date;

/**
 * Tblfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblfail extends AbstractTblfail implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblfail() {
	}

	/** full constructor */
	public Tblfail(String kodFail, String tajukFail, Date tarikhBuka,
			String catatan, String kodPengguna, String kodStatusFail,
			String kodKategoriKeselamatan, String kodKategoriFail) {
		super(kodFail, tajukFail, tarikhBuka, catatan, kodPengguna,
				kodStatusFail, kodKategoriKeselamatan, kodKategoriFail);
	}

}
