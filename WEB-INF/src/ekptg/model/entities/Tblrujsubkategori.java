package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujsubkategori entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujsubkategori extends AbstractTblrujsubkategori implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujsubkategori() {
	}

	/** minimal constructor */
	public Tblrujsubkategori(Long idKategori) {
		super(idKategori);
	}

	/** full constructor */
	public Tblrujsubkategori(Long idKategori, String kodSubkategori,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idKategori, kodSubkategori, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
