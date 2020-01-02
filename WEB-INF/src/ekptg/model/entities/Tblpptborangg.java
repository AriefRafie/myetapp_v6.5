package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborangg entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborangg extends AbstractTblpptborangg implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborangg() {
	}

	/** full constructor */
	public Tblpptborangg(Date tarikhBorangg, Date tarikhCetak,
			Date tarikhCetakSemula, Date tarikhTerimaAp, Date tarikhAkhirAp,
			Date tarikhBorangh, Long idSiasatan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(tarikhBorangg, tarikhCetak, tarikhCetakSemula, tarikhTerimaAp,
				tarikhAkhirAp, tarikhBorangh, idSiasatan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
