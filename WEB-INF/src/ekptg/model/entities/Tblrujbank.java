package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujbank entity. @author MyEclipse Persistence Tools
 */
public class Tblrujbank extends AbstractTblrujbank implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujbank() {
	}

	/** full constructor */
	public Tblrujbank(String kodBank, String namaBank, String alamat1,
			String alamat2, String alamat3, String poskod, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(kodBank, namaBank, alamat1, alamat2, alamat3, poskod, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
