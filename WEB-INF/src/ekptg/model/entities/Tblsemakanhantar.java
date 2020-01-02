package ekptg.model.entities;

import java.util.Date;

/**
 * Tblsemakanhantar entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblsemakanhantar extends AbstractTblsemakanhantar implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblsemakanhantar() {
	}

	/** minimal constructor */
	public Tblsemakanhantar(Long idSemakansenarai, Long idPermohonan) {
		super(idSemakansenarai, idPermohonan);
	}

	/** full constructor */
	public Tblsemakanhantar(Long idSemakansenarai, Long idPermohonan,
			String pemohon, String pentadbir, String status, String catatan,
			Date tarikhPelbagai, Long idDokumen, String flagAda, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idSemakansenarai, idPermohonan, pemohon, pentadbir, status,
				catatan, tarikhPelbagai, idDokumen, flagAda, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
