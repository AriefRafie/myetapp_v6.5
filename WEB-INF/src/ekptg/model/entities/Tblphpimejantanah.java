package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpimejantanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpimejantanah extends AbstractTblphpimejantanah implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpimejantanah() {
	}

	/** minimal constructor */
	public Tblphpimejantanah(Long idImejantanah) {
		super(idImejantanah);
	}

	/** full constructor */
	public Tblphpimejantanah(Long idImejantanah,
			Tblphplawatantapak tblphplawatantapak, Long idDokumen,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idImejantanah, tblphplawatantapak, idDokumen, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
