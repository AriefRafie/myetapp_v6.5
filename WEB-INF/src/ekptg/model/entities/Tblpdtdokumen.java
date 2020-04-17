package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpdtdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtdokumen extends AbstractTblpdtdokumen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtdokumen() {
	}

	/** minimal constructor */
	public Tblpdtdokumen(Long idDokumen) {
		super(idDokumen);
	}

	/** full constructor */
	public Tblpdtdokumen(Long idDokumen, String noDokumen, String tajukDokumen,
			String kategoriDokumen, Date tarikhDokumen, Date tarikhDaftar,
			Long idFail, String seksyenUrusetia, String kandunganDokumen,
			String catatan, String dirDokumen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idDokumen, noDokumen, tajukDokumen, kategoriDokumen,
				tarikhDokumen, tarikhDaftar, idFail, seksyenUrusetia,
				kandunganDokumen, catatan, dirDokumen, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
