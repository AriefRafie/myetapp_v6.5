package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborangl entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborangl extends AbstractTblpptborangl implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborangl() {
	}

	/** full constructor */
	public Tblpptborangl(Date tarikhBorangl, Date tarikhCetak, Long jenisPilih,
			Long unitTempoh, Date tarikhTempoh, Long tempoh, Long idPermohonan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(tarikhBorangl, tarikhCetak, jenisPilih, unitTempoh, tarikhTempoh,
				tempoh, idPermohonan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
