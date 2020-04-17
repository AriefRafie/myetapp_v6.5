package ekptg.model.entities;

import java.util.Date;

/**
 * Tblurusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblurusan extends AbstractTblurusan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblurusan() {
	}

	/** full constructor */
	public Tblurusan(String kodProses, String kodFail, Date tarikhMula,
			Date tarikhSelesai, String kodPengguna, String kodStatusUrusan,
			String catatan) {
		super(kodProses, kodFail, tarikhMula, tarikhSelesai, kodPengguna,
				kodStatusUrusan, catatan);
	}

}
