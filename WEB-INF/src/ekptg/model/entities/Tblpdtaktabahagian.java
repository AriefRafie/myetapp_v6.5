package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktabahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktabahagian extends AbstractTblpdtaktabahagian implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktabahagian() {
	}

	/** minimal constructor */
	public Tblpdtaktabahagian(Long idAktabahagian) {
		super(idAktabahagian);
	}

	/** full constructor */
	public Tblpdtaktabahagian(Long idAktabahagian,
			Tblpdtaktapenggal tblpdtaktapenggal, Tblpdtakta tblpdtakta,
			String tajukBahagian,String noBahagian, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpdtaktababs,
			Set tblpdtpindaanaktabahagians) {
		super(idAktabahagian, tblpdtaktapenggal, tblpdtakta, tajukBahagian,noBahagian,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtaktababs, tblpdtpindaanaktabahagians);
	}

}
