package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktapenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktapenggal extends AbstractTblpdtaktapenggal implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktapenggal() {
	}

	/** minimal constructor */
	public Tblpdtaktapenggal(Long idAktapenggal) {
		super(idAktapenggal);
	}

	/** full constructor */
	public Tblpdtaktapenggal(Long idAktapenggal, Tblpdtakta tblpdtakta,
			String tajukPenggal, String noPenggal,Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpdtaktabahagians,
			Set tblpdtpindaanaktapenggals) {
		super(idAktapenggal, tblpdtakta, tajukPenggal,noPenggal, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblpdtaktabahagians,
				tblpdtpindaanaktapenggals);
	}

}
