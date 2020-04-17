package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpjagaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpjagaan extends AbstractTblhtpjagaan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpjagaan() {
	}

	/** minimal constructor */
	public Tblhtpjagaan(Long idJagaan, Long idNegeri, Long idDaerah) {
		super(idJagaan, idNegeri, idDaerah);
	}

	/** full constructor */
	public Tblhtpjagaan(Long idJagaan, Long idNegeri, Long idDaerah,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idJagaan, idNegeri, idDaerah, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
