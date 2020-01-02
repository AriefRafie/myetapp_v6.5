package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujjenistanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujjenistanah extends AbstractTblrujjenistanah implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenistanah() {
	}

	/** minimal constructor */
	public Tblrujjenistanah(Long idJenistanah) {
		super(idJenistanah);
	}

	/** full constructor */
	public Tblrujjenistanah(Long idJenistanah, String kodJenistanah,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idJenistanah, kodJenistanah, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
