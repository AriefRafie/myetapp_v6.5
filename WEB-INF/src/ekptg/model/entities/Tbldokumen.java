package ekptg.model.entities;

import java.util.Date;

/**
 * Tbldokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tbldokumen extends AbstractTbldokumen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tbldokumen() {
	}

	/** full constructor */
	public Tbldokumen(String kodFail, String kodDokumen, String tajukDokumen,
			String catatan, Date tarikhMasuk, String kodPengguna,
			String dokumen, String tandatangan, String kodKategoriKeselamatan,
			String kodKategoriDokumen) {
		super(kodFail, kodDokumen, tajukDokumen, catatan, tarikhMasuk,
				kodPengguna, dokumen, tandatangan, kodKategoriKeselamatan,
				kodKategoriDokumen);
	}

}
