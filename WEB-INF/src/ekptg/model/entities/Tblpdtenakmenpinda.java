package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmenpinda entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmenpinda extends AbstractTblpdtenakmenpinda implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmenpinda() {
	}

	/** minimal constructor */
	public Tblpdtenakmenpinda(Long idEnakmenpinda, Long noEnakmenAsal,
			Long noEnakmenPindaan, Long noEnakmenBaru, String namaEnakmenBaru,
			Long idFail) {
		super(idEnakmenpinda, noEnakmenAsal, noEnakmenPindaan, noEnakmenBaru,
				namaEnakmenBaru, idFail);
	}

	/** full constructor */
	public Tblpdtenakmenpinda(Long idEnakmenpinda, Long noEnakmenAsal,
			Long noEnakmenPindaan, Long noEnakmenBaru, String namaEnakmenBaru,
			String justifikasiPindaan, String kandunganPindaan,
			Date tarikhKuatkuasa, Date tarikhDaftarPindaan,
			Date tarikhKuatkuasaPindaan, Date tarikhWarta,
			Date tarikhPerkenaanDiraja, Date tarikhPenyiaranDalamwarta,
			Long bil, String dirFail, Long idFail, String catatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtjadualpindas,
			Set tblpdtenakmenpindabahagians, Set tblpdtpindaanenakmens,
			Set tblpdtenakmenpindapenggals) {
		super(idEnakmenpinda, noEnakmenAsal, noEnakmenPindaan, noEnakmenBaru,
				namaEnakmenBaru, justifikasiPindaan, kandunganPindaan,
				tarikhKuatkuasa, tarikhDaftarPindaan, tarikhKuatkuasaPindaan,
				tarikhWarta, tarikhPerkenaanDiraja, tarikhPenyiaranDalamwarta,
				bil, dirFail, idFail, catatan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblpdtjadualpindas,
				tblpdtenakmenpindabahagians, tblpdtpindaanenakmens,
				tblpdtenakmenpindapenggals);
	}

}
