package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujkementerian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujkementerian extends AbstractTblrujkementerian implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujkementerian() {
	}

	/** minimal constructor */
	public Tblrujkementerian(Long idKementerian) {
		super(idKementerian);
	}

	/** full constructor */
	public Tblrujkementerian(Long idKementerian, String kodKementerian,
			String namaKementerian, String alamat1, String alamat2,
			String alamat3, String poskod, Long idNegeri, String jawatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idKementerian, kodKementerian, namaKementerian, alamat1, alamat2,
				alamat3, poskod, idNegeri, jawatan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
