package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpulasankjpkjt entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpulasankjpkjt extends AbstractTblphpulasankjpkjt implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpulasankjpkjt() {
	}

	/** minimal constructor */
	public Tblphpulasankjpkjt(Long idTblphpulasankjpkjt) {
		super(idTblphpulasankjpkjt);
	}

	/** full constructor */
	public Tblphpulasankjpkjt(Long idTblphpulasankjpkjt,
			Tblphpulasanteknikal tblphpulasanteknikal, Long idJabatanteknikal,
			Long idMenteri, Long idAgensi, String noRujukan, Long idDokumen,
			String ulasan, Date tarikhHantar, Long jangkamasa,
			Date tarikhJangkaTerima, Date tarikhSurat, Date tarikhTerima,
			String flagStatus, Long bilHantar, Long noVersi,
			Long idUlasanringkasan, String flagTerkini, String ulasanPindaan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idTblphpulasankjpkjt, tblphpulasanteknikal, idJabatanteknikal,
				idMenteri, idAgensi, noRujukan, idDokumen, ulasan,
				tarikhHantar, jangkamasa, tarikhJangkaTerima, tarikhSurat,
				tarikhTerima, flagStatus, bilHantar, noVersi,
				idUlasanringkasan, flagTerkini, ulasanPindaan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
