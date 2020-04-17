package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpulasanjpph entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpulasanjpph extends AbstractTblphpulasanjpph implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpulasanjpph() {
	}

	/** minimal constructor */
	public Tblphpulasanjpph(Long idUlasanjpph) {
		super(idUlasanjpph);
	}

	/** full constructor */
	public Tblphpulasanjpph(Long idUlasanjpph,
			Tblphpulasanteknikal tblphpulasanteknikal, Long idJabatanteknikal,
			String noRujukan, Long idDokumen, String ulasan, Date tarikhHantar,
			Long jangkamasa, Date tarikhJangkaTerima, Date tarikhSurat,
			Date tarikhTerima, String flagStatus, Long bilHantar, Long noVersi,
			Long idUlasanringkasan, String flagTerkini,
			Double kadarSewaKesebulan, Double kadarSewaBertempoh,
			Double kadarNilaianJpph, Double jumlahNilaianJpph,
			Long tempohSahNilaian, String ulasanPindaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppenyediaulasanjpphs) {
		super(idUlasanjpph, tblphpulasanteknikal, idJabatanteknikal, noRujukan,
				idDokumen, ulasan, tarikhHantar, jangkamasa,
				tarikhJangkaTerima, tarikhSurat, tarikhTerima, flagStatus,
				bilHantar, noVersi, idUlasanringkasan, flagTerkini,
				kadarSewaKesebulan, kadarSewaBertempoh, kadarNilaianJpph,
				jumlahNilaianJpph, tempohSahNilaian, ulasanPindaan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblphppenyediaulasanjpphs);
	}

}
