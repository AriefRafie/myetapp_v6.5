package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpjabatanlaporantanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpjabatanlaporantanah extends
		AbstractTblphpjabatanlaporantanah implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpjabatanlaporantanah() {
	}

	/** minimal constructor */
	public Tblphpjabatanlaporantanah(Long idJabatanlaporantanah) {
		super(idJabatanlaporantanah);
	}

	/** full constructor */
	public Tblphpjabatanlaporantanah(Long idJabatanlaporantanah,
			Tblphplawatantapak tblphplawatantapak, Long idJabatan,
			Long idNegerijabatan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idJabatanlaporantanah, tblphplawatantapak, idJabatan,
				idNegerijabatan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
