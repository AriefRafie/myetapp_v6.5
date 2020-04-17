package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujpejabat entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujpejabat extends AbstractTblrujpejabat implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujpejabat() {
	}

	/** minimal constructor */
	public Tblrujpejabat(Long idDaerah,
			Long idSeksyen, Long idNegeri) {
		super(idDaerah, idSeksyen, idNegeri);
	}

	/** full constructor */
	public Tblrujpejabat(Long idDaerah,
			Long idSeksyen, Long idNegeri,String kodPejabat,
			String jenisPejabat, String namaPejabat, String alamat1,
			String alamat2, String alamat3, String poskod, String noTel,
			String noFax, String jawatan, String noAkaun, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idDaerah, idSeksyen, idNegeri, kodPejabat, jenisPejabat,
				namaPejabat, alamat1, alamat2, alamat3, poskod, noTel, noFax,
				jawatan, noAkaun, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
