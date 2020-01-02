package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktapindabahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktapindabahagian extends AbstractTblpdtaktapindabahagian
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktapindabahagian() {
	}

	/** minimal constructor */
	public Tblpdtaktapindabahagian(Long idAktapindabahagian) {
		super(idAktapindabahagian);
	}

	/** full constructor */
	public Tblpdtaktapindabahagian(Long idAktapindabahagian,
			Tblpdtaktapindapenggal tblpdtaktapindapenggal,
			Tblpdtaktapinda tblpdtaktapinda, String namaBahagian, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtaktapindababs, Set tblpdtpindaanaktabahagians) {
		super(idAktapindabahagian, tblpdtaktapindapenggal, tblpdtaktapinda,
				namaBahagian, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblpdtaktapindababs,
				tblpdtpindaanaktabahagians);
	}

}
