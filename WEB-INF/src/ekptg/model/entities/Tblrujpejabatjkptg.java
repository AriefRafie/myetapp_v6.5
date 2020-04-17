package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujpejabatjkptg entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujpejabatjkptg extends AbstractTblrujpejabatjkptg implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujpejabatjkptg() {
	}

	/** minimal constructor */
	public Tblrujpejabatjkptg(Long idSeksyen, Long idNegeri, Long idDaerah) {
		super(idSeksyen, idNegeri, idDaerah);
	}

	/** full constructor */
	public Tblrujpejabatjkptg(Long idSeksyen, Long idDb, String kodJkptg,
			String jenisPejabat, String namaPejabat, String alamat1,
			String alamat2, String alamat3, String poskod, Long idNegeri,
			Long idDaerah, String prefix, String noTel, String noFax,
			String jawatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idSeksyen, idDb, kodJkptg, jenisPejabat, namaPejabat, alamat1,
				alamat2, alamat3, poskod, idNegeri, idDaerah, prefix, noTel,
				noFax, jawatan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
