package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtppihakberkepentingan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppihakberkepentingan extends
		AbstractTblhtppihakberkepentingan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppihakberkepentingan() {
	}

	/** minimal constructor */
	public Tblhtppihakberkepentingan(Long idPihakberkepentingan,
			Long idHakmilikurusan, Long idJenisnopb, Long idJenispb,
			Long idDaerah, Long idNegeri) {
		super(idPihakberkepentingan, idHakmilikurusan, idJenisnopb, idJenispb,
				idDaerah, idNegeri);
	}

	/** full constructor */
	public Tblhtppihakberkepentingan(Long idPihakberkepentingan,
			Long idHakmilikurusan, Long idJenisnopb, Long idJenispb,
			String noRujukan, String nama, String alamat1, String alamat2,
			String alamat3, String poskod, Long idDaerah, Long idNegeri,
			String noTel, String noFax, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idPihakberkepentingan, idHakmilikurusan, idJenisnopb, idJenispb,
				noRujukan, nama, alamat1, alamat2, alamat3, poskod, idDaerah,
				idNegeri, noTel, noFax, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
