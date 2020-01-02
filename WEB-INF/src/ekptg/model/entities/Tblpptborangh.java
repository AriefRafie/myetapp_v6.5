package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborangh entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborangh extends AbstractTblpptborangh implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborangh() {
	}

	/** full constructor */
	public Tblpptborangh(Long jenisBorangh, Date tarikhBorangh,
			Date tarikhCetak, Long tarikhCetakSemula, Date tarikhTerima,
			Long keputusan, Long idBorangg, Long statusCek, Date tarikhSerah,
			Date tarikhAkhirBayaragensi, Long idPihakberkepentingan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(jenisBorangh, tarikhBorangh, tarikhCetak, tarikhCetakSemula,
				tarikhTerima, keputusan, idBorangg, statusCek, tarikhSerah,
				tarikhAkhirBayaragensi, idPihakberkepentingan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
