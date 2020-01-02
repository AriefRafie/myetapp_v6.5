package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptmmkkeputusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptmmkkeputusan extends AbstractTblpptmmkkeputusan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptmmkkeputusan() {
	}

	/** full constructor */
	public Tblpptmmkkeputusan(Long idMmk, Date tarikhTerima,
			Date tarikhKeputusan, Long statusKeputusan, String ulasan,
			Date tarikhHantar, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idMmk, tarikhTerima, tarikhKeputusan, statusKeputusan, ulasan,
				tarikhHantar, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
