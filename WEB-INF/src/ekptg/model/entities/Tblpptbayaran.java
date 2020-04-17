package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptbayaran extends AbstractTblpptbayaran implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptbayaran() {
	}

	/** full constructor */
	public Tblpptbayaran(String noBayaran, Double amaunBayaran,
			Date tarikhTerima, Long idBank, Long caraBayar, Date tarikhCek,
			Long jenisAward, Date tarikhSerahCek, String flagSerahCek,
			String namaWakil, String noWakil, Long idJenisnopb,
			String flagTerimaCek, String noRujukanSurat, Date tarikhSurat,
			Date tarikhAmbilCek, String tempatAmbil, String masaAmbilCek,
			String penerimaCek, Double dendaLewat, String noPb,
			String noKpLama, String noEft, String noBaucer,
			String noRujukanSurateft, Date tarikhTerimaEft,
			Date tarikhSuratEft, Long idTerimabayaran,
			Long idPihakberkepentingan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(noBayaran, amaunBayaran, tarikhTerima, idBank, caraBayar,
				tarikhCek, jenisAward, tarikhSerahCek, flagSerahCek, namaWakil,
				noWakil, idJenisnopb, flagTerimaCek, noRujukanSurat,
				tarikhSurat, tarikhAmbilCek, tempatAmbil, masaAmbilCek,
				penerimaCek, dendaLewat, noPb, noKpLama, noEft, noBaucer,
				noRujukanSurateft, tarikhTerimaEft, tarikhSuratEft,
				idTerimabayaran, idPihakberkepentingan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
