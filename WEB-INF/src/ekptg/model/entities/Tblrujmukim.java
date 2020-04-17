package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujmukim entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujmukim extends AbstractTblrujmukim implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujmukim() {
	}

	/** minimal constructor */
	public Tblrujmukim(Long idMukim, Long idDaerah, Long idNegeri) {
		super(idMukim, idDaerah, idNegeri);
	}

	/** full constructor */
	public Tblrujmukim(Long idMukim, String kodMukim, String namaMukim,
			Long idDaerah, Long idNegeri, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idMukim, kodMukim, namaMukim, idDaerah, idNegeri, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
