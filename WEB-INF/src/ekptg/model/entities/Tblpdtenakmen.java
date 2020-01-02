package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtenakmen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtenakmen extends AbstractTblpdtenakmen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtenakmen() {
	}

	/** minimal constructor */
	public Tblpdtenakmen(Long idEnakmen, Long noEnakmen, String namaEnakmen,
			Long idFail) {
		super(idEnakmen, noEnakmen, namaEnakmen, idFail);
	}

	/** full constructor */
	public Tblpdtenakmen(Long idEnakmen, Long noEnakmen, String namaEnakmen,
			Date tarikhKuatkuasa, Date tarikhDaftar, Date tarikhMansuh,
			Long bil, String dirFail, Long idFail, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			String catatan, Date tarikhWarta, Set tblpdtenakmenbahagians,
			Set tblpdtenakmenpenggals, Set tblpdtjaduals,
			Set tblpdtpindaanenakmens) {
		super(idEnakmen, noEnakmen, namaEnakmen, tarikhKuatkuasa, tarikhDaftar,
				tarikhMansuh, bil, dirFail, idFail, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, catatan, tarikhWarta,
				tblpdtenakmenbahagians, tblpdtenakmenpenggals, tblpdtjaduals,
				tblpdtpindaanenakmens);
	}

}
