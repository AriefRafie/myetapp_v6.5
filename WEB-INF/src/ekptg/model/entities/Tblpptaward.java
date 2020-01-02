package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptaward entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptaward extends AbstractTblpptaward implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptaward() {
	}

	/** full constructor */
	public Tblpptaward(Long jenisAward, Double bayarFee, Double bayarPampasan,
			Double bayarBangunan, Double bayarBetterment,
			Double bayarPecahpisah, Double bayarPenjejasan,
			Double bayarPendapatan, Double bayarPindah, Double bayarSewa,
			Double bayarLain2, Double luasAmbil, Long idUnitluasAmbil,
			Double nilaiSeunitAmbil, String unitNilaiAmbil,
			Long statusPenerima, Double dendaLewat, Date tarikhSediaAward,
			Date tarikhTerimaAgensi, String ulasan, Date tarikhAkhirAgensi,
			Double faedahSelepasPu, Double faedahSebelumPu,
			String flagSerahCek, Long keputusanTawaran, Long tempohPampasan,
			Long unitTempoh, Date tarikhSurat, Long idPihakberkepentingan,
			Long idSiasatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(jenisAward, bayarFee, bayarPampasan, bayarBangunan,
				bayarBetterment, bayarPecahpisah, bayarPenjejasan,
				bayarPendapatan, bayarPindah, bayarSewa, bayarLain2, luasAmbil,
				idUnitluasAmbil, nilaiSeunitAmbil, unitNilaiAmbil,
				statusPenerima, dendaLewat, tarikhSediaAward,
				tarikhTerimaAgensi, ulasan, tarikhAkhirAgensi, faedahSelepasPu,
				faedahSebelumPu, flagSerahCek, keputusanTawaran,
				tempohPampasan, unitTempoh, tarikhSurat, idPihakberkepentingan,
				idSiasatan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				idDb);
	}

}
