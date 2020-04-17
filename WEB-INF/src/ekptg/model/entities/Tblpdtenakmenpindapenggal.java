package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenpindapenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenpindapenggal extends
		AbstractTblpdtenakmenpindapenggal implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenpindapenggal() {
	}

	/** minimal constructor */
	public Tblpdtenakmenpindapenggal(Long idEnakmenpindapenggal) {
		super(idEnakmenpindapenggal);
	}

	/** full constructor */
	public Tblpdtenakmenpindapenggal(Long idEnakmenpindapenggal,
			Tblpdtenakmenpinda tblpdtenakmenpinda, String namaPenggal,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmenpenggals,
			Set tblpdtenakmenpindabahagians) {
		super(idEnakmenpindapenggal, tblpdtenakmenpinda, namaPenggal, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanenakmenpenggals, tblpdtenakmenpindabahagians);
	}

}
