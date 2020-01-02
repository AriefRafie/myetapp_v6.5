package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphprujdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphprujdokumen extends AbstractTblphprujdokumen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphprujdokumen() {
	}

	/** minimal constructor */
	public Tblphprujdokumen(Long idDokumen) {
		super(idDokumen);
	}

	/** full constructor */
	public Tblphprujdokumen(Long idDokumen, String kodDokumen, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idDokumen, kodDokumen, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
