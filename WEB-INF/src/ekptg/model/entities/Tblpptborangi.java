package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborangi entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborangi extends AbstractTblpptborangi implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborangi() {
	}

	/** full constructor */
	public Tblpptborangi(Date tarikhBorangi, Date tarikhCetak,
			Date tarikhCetakSemula, Long jenisAmbilsegera,
			String noRujukanSurat, Date tarikhSurat, Date tarikhTerima,
			Long idPermohonan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(tarikhBorangi, tarikhCetak, tarikhCetakSemula, jenisAmbilsegera,
				noRujukanSurat, tarikhSurat, tarikhTerima, idPermohonan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
