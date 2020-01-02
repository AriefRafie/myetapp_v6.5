package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpulasanjupem entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpulasanjupem extends AbstractTblphpulasanjupem implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpulasanjupem() {
	}

	/** minimal constructor */
	public Tblphpulasanjupem(Long idUlasanjupem) {
		super(idUlasanjupem);
	}

	/** full constructor */
	public Tblphpulasanjupem(Long idUlasanjupem,
			Tblphpulasanteknikal tblphpulasanteknikal, Long idJabatanteknikal,
			String noRujukan, Long idDokumen, String ulasan, Date tarikhHantar,
			Long jangkamasa, Date tarikhJangkaTerima, Date tarikhSurat,
			Date tarikhTerima, String flagStatus, Long bilHantar, Long noVersi,
			Long idUlasanringkasan, String flagTerkini, String flagBertindih,
			Long idKedudukan, String flagApb, String ulasanPindaan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphppenyediaulasanjupems) {
		super(idUlasanjupem, tblphpulasanteknikal, idJabatanteknikal,
				noRujukan, idDokumen, ulasan, tarikhHantar, jangkamasa,
				tarikhJangkaTerima, tarikhSurat, tarikhTerima, flagStatus,
				bilHantar, noVersi, idUlasanringkasan, flagTerkini,
				flagBertindih, idKedudukan, flagApb, ulasanPindaan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblphppenyediaulasanjupems);
	}

}
