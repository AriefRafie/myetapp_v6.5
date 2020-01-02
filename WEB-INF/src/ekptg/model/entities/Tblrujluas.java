package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujluas entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujluas extends AbstractTblrujluas implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujluas() {
	}

	/** minimal constructor */
	public Tblrujluas(Long idLuas) {
		super(idLuas);
	}

	/** full constructor */
	public Tblrujluas(Long idLuas, String kodLuas, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idLuas, kodLuas, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
