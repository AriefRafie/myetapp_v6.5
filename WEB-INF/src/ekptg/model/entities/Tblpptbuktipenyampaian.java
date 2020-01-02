package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptbuktipenyampaian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptbuktipenyampaian extends AbstractTblpptbuktipenyampaian
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptbuktipenyampaian() {
	}

	/** full constructor */
	public Tblpptbuktipenyampaian(String noDokumen, Long idJenisdokumen,
			String flagSerah, String catatan, Long jenisSerahan,
			String namaPenerima, String noKpPenerima, Date tarikhHantar,
			Date tarikhTerima, Long idPermohonan, Long idBantahan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(noDokumen, idJenisdokumen, flagSerah, catatan, jenisSerahan,
				namaPenerima, noKpPenerima, tarikhHantar, tarikhTerima,
				idPermohonan, idBantahan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
