package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborangj entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborangj extends AbstractTblpptborangj implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborangj() {
	}

	/** full constructor */
	public Tblpptborangj(Long idBangunan, Date tarikhNotis,
			Date tarikhTamatNotis, Date tarikhBorangj, Long idHakmilik,
			Long idBorangi, Long tempoh, Long unitTempoh, String tindakan,
			String keputusan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(idBangunan, tarikhNotis, tarikhTamatNotis, tarikhBorangj,
				idHakmilik, idBorangi, tempoh, unitTempoh, tindakan, keputusan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
