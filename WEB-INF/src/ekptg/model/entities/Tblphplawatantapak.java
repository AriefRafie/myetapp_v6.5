package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphplawatantapak entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphplawatantapak extends AbstractTblphplawatantapak implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphplawatantapak() {
	}

	/** minimal constructor */
	public Tblphplawatantapak(Long idLawatantapak) {
		super(idLawatantapak);
	}

	/** full constructor */
	public Tblphplawatantapak(Long idLawatantapak, Date tarikhLawatan,
			String tujuanLawatan, String perkembanganTapak,
			String jalanHubungan, String kawasanBerhampiran,
			String jarakDrbandar, String keadaanRupabumi, String keadaanTanah,
			String kemudahanAsas, String pembangunanSekitar, String sempUtara,
			String sempBarat, String sempTimur, String sempSelatan,
			String ulasan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphpimejantanahs,
			Set tblphpjabatanlaporantanahs, Set tblphppegawailaporantanahs) {
		super(idLawatantapak, tarikhLawatan, tujuanLawatan, perkembanganTapak,
				jalanHubungan, kawasanBerhampiran, jarakDrbandar,
				keadaanRupabumi, keadaanTanah, kemudahanAsas,
				pembangunanSekitar, sempUtara, sempBarat, sempTimur,
				sempSelatan, ulasan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblphpimejantanahs,
				tblphpjabatanlaporantanahs, tblphppegawailaporantanahs);
	}

}
