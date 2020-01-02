package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtppergerakan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppergerakan extends AbstractTblhtppergerakan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppergerakan() {
	}

	/** minimal constructor */
	public Tblhtppergerakan(Long idPergerakan,
			Tblhtppermohonan tblhtppermohonan, Long idHakmilikpegangan,
			Long idMasuk) {
		super(idPergerakan, tblhtppermohonan, idHakmilikpegangan, idMasuk);
	}

	/** full constructor */
	public Tblhtppergerakan(Long idPergerakan,
			Tblhtppermohonan tblhtppermohonan, Long idHakmilikpegangan,
			Date tarikh, String keterangan, Long bilSalinan, String status,
			Long idMasuk, Date tarikhMasuk) {
		super(idPergerakan, tblhtppermohonan, idHakmilikpegangan, tarikh,
				keterangan, bilSalinan, status, idMasuk, tarikhMasuk);
	}

}
