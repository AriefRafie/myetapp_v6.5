package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtppenswastaanId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtppenswastaanId extends AbstractTblhtppenswastaanId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtppenswastaanId() {
	}

	/** minimal constructor */
	public TblhtppenswastaanId(Long idPenswastaan,
			Tblhtppermohonan tblhtppermohonan) {
		super(idPenswastaan, tblhtppermohonan);
	}

	/** full constructor */
	public TblhtppenswastaanId(Long idPenswastaan,
			Tblhtppermohonan tblhtppermohonan, String tindakanLanjut,
			String statusPtp, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPenswastaan, tblhtppermohonan, tindakanLanjut, statusPtp,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
