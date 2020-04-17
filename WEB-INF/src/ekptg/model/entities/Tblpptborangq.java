package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborangq entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborangq extends AbstractTblpptborangq implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborangq() {
	}

	/** full constructor */
	public Tblpptborangq(String noBorangq, Date tarikhBorangq,
			String flagNotis, Date tarikhMula, Date tarikhAkhir,
			Long idUnitluas, Long luasSewa, Long idPermohonan,
			Long tempohPendudukan, Long unitTempoh, Double tawaranPampasan,
			String masa, String tempat, Date tarikhRundingan, Long idHakmilik,
			Date tarikhCetak, Date tarikhCetakSemula, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(noBorangq, tarikhBorangq, flagNotis, tarikhMula, tarikhAkhir,
				idUnitluas, luasSewa, idPermohonan, tempohPendudukan,
				unitTempoh, tawaranPampasan, masa, tempat, tarikhRundingan,
				idHakmilik, tarikhCetak, tarikhCetakSemula, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
