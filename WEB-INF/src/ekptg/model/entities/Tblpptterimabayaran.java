package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptterimabayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptterimabayaran extends AbstractTblpptterimabayaran implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptterimabayaran() {
	}

	/** full constructor */
	public Tblpptterimabayaran(Long idHakmilik, String noRujukanSurat,
			Date tarikhTerima, Date tarikhSurat, Long idBank, Double amaunCek,
			Date tarikhAmbilCek, String masaAmbilCek, String tempatAmbil,
			Long jenisAward, Date tarikhBorangk, String noBorangk,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(idHakmilik, noRujukanSurat, tarikhTerima, tarikhSurat, idBank,
				amaunCek, tarikhAmbilCek, masaAmbilCek, tempatAmbil,
				jenisAward, tarikhBorangk, noBorangk, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
