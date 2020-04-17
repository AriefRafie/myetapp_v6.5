package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborangf entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborangf extends AbstractTblpptborangf implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborangf() {
	}

	/** full constructor */
	public Tblpptborangf(Long idPermohonan, Long idPihakberkepentingan,
			Long idJenispb, String nama, String alamat1, String alamat2,
			String alamat3, String poskod, Long idNegeri, Date tarikhBorangf,
			Date tarikhHantar, String tempoh, Long unitTempoh, String ulasan,
			Date tarikhTerima, Date tarikhCetak, Date tarikhCetakSemula,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(idPermohonan, idPihakberkepentingan, idJenispb, nama, alamat1,
				alamat2, alamat3, poskod, idNegeri, tarikhBorangf,
				tarikhHantar, tempoh, unitTempoh, ulasan, tarikhTerima,
				tarikhCetak, tarikhCetakSemula, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
