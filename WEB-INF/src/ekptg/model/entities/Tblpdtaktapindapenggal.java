package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktapindapenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktapindapenggal extends AbstractTblpdtaktapindapenggal
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktapindapenggal() {
	}

	/** minimal constructor */
	public Tblpdtaktapindapenggal(Long idAktapindapenggal) {
		super(idAktapindapenggal);
	}

	/** full constructor */
	public Tblpdtaktapindapenggal(Long idAktapindapenggal,
			Tblpdtaktapinda tblpdtaktapinda, String namaPenggal, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanaktapenggals, Set tblpdtaktapindabahagians) {
		super(idAktapindapenggal, tblpdtaktapinda, namaPenggal, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtpindaanaktapenggals, tblpdtaktapindabahagians);
	}

}
