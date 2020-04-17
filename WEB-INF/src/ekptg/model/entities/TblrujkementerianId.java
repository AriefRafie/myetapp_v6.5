package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujkementerianId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujkementerianId extends AbstractTblrujkementerianId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujkementerianId() {
	}

	/** minimal constructor */
	public TblrujkementerianId(Long idKementerian, Long idNegeri) {
		super(idKementerian, idNegeri);
	}

	/** full constructor */
	public TblrujkementerianId(Long idKementerian, String kodKementerian,
			String namaKementerian, String alamat1, String alamat2,
			String alamat3, String poskod, Long idNegeri, String jawatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idKementerian, kodKementerian, namaKementerian, alamat1, alamat2,
				alamat3, poskod, idNegeri, jawatan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
