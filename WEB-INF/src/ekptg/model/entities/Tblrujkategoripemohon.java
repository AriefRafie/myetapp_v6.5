package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujkategoripemohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujkategoripemohon extends AbstractTblrujkategoripemohon
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujkategoripemohon() {
	}

	/** full constructor */
	public Tblrujkategoripemohon(String kodKategoripemohon, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(kodKategoripemohon, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
