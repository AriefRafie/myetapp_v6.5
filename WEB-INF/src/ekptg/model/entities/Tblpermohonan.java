package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpermohonan extends AbstractTblpermohonan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpermohonan() {
	}

	/** minimal constructor */
	public Tblpermohonan(Long idPermohonan, Long idFail, Long idJkptg) {
		super(idPermohonan, idFail, idJkptg);
	}

	/** full constructor */
	public Tblpermohonan(Long idPermohonan, Long idFail, Long idJkptg,
			String noPermohonan, String noPerserahan, Date tarikhSurat,
			Date tarikhTerima, String tujuan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, String catatanOnline) {
		super(idPermohonan, idFail, idJkptg, noPermohonan, noPerserahan,
				tarikhSurat, tarikhTerima, tujuan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, catatanOnline);
	}

}
