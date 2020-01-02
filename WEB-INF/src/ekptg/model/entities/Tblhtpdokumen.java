package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpdokumen extends AbstractTblhtpdokumen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpdokumen() {
	}

	/** minimal constructor */
	public Tblhtpdokumen(Long idDokumen, Long idPermohonan) {
		super(idDokumen, idPermohonan);
	}

	/** full constructor */
	public Tblhtpdokumen(Long idDokumen, Long idPermohonan, Date tarikhHantar,
			Date tarikhTerima, String jenisDokumen, String pihak,
			String ulasan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idDokumen, idPermohonan, tarikhHantar, tarikhTerima,
				jenisDokumen, pihak, ulasan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
