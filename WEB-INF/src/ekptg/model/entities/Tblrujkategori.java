package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujkategori entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujkategori extends AbstractTblrujkategori implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujkategori() {
	}

	/** full constructor */
	public Tblrujkategori(String kodKategori, String keterangan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(kodKategori, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
