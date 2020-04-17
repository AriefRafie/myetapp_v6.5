package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpbarge entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpbarge extends AbstractTblphpbarge implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpbarge() {
	}

	/** minimal constructor */
	public Tblphpbarge(Long idBarge) {
		super(idBarge);
	}

	/** full constructor */
	public Tblphpbarge(Long idBarge, Tblphpboranga tblphpboranga,
			Date tarikhHantar, String namaBarge, Long idJenisbarge,
			String lokasiDibekalkan, String noPendaftaran, Long muatan,
			Long idMuatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idBarge, tblphpboranga, tarikhHantar, namaBarge, idJenisbarge,
				lokasiDibekalkan, noPendaftaran, muatan, idMuatan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
