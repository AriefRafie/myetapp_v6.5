package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtakta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtakta extends AbstractTblpdtakta implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtakta() {
	}

	/** minimal constructor */
	public Tblpdtakta(Long idAkta, String noAkta, String namaAkta, Long idFail) {
		super(idAkta, noAkta, namaAkta, idFail);
	}

	/** full constructor */
	public Tblpdtakta(Long idAkta, String noAkta, String namaAkta,
			Date tarikhKuatkuasa, Date tarikhDaftar, Date tarikhMansuh,
			Long bil, String dirFail, Long idFail, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			String catatan, Set tblpdtpindaanaktas, Set tblpdtjaduals,
			Set tblpdtaktabahagians, Set tblpdtaktapenggals) {
		super(idAkta, noAkta, namaAkta, tarikhKuatkuasa, tarikhDaftar,
				tarikhMansuh, bil, dirFail, idFail, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, catatan, tblpdtpindaanaktas,
				tblpdtjaduals, tblpdtaktabahagians, tblpdtaktapenggals);
	}

}
