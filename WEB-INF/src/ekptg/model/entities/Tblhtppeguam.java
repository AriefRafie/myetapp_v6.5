package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtppeguam entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppeguam extends AbstractTblhtppeguam implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppeguam() {
	}

	/** minimal constructor */
	public Tblhtppeguam(Long idPeguam, Long idPermohonan, Long idDaerah,
			Long idNegeri) {
		super(idPeguam, idPermohonan, idDaerah, idNegeri);
	}

	/** full constructor */
	public Tblhtppeguam(Long idPeguam, Long idPermohonan, String noRujukan,
			String nama, String alamat1, String alamat2, String alamat3,
			String poskod, Long idDaerah, Long idNegeri, String noTel,
			String noFax, String namaPeguam, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idPeguam, idPermohonan, noRujukan, nama, alamat1, alamat2,
				alamat3, poskod, idDaerah, idNegeri, noTel, noFax, namaPeguam,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
