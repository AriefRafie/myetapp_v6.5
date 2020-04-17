package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborangk entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborangk extends AbstractTblpptborangk implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborangk() {
	}

	/** full constructor */
	public Tblpptborangk(String flagSegera, Date tarikhBorangk,
			Date tarikhCetak, Long idBorangi, Long idBorangg,
			Long idPermohonan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(flagSegera, tarikhBorangk, tarikhCetak, idBorangi, idBorangg,
				idPermohonan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
