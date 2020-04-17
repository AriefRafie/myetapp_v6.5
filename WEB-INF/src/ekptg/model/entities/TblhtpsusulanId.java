package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtpsusulanId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtpsusulanId extends AbstractTblhtpsusulanId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtpsusulanId() {
	}

	/** minimal constructor */
	public TblhtpsusulanId(Long idSusulan, Tblhtppermohonan tblhtppermohonan) {
		super(idSusulan, tblhtppermohonan);
	}

	/** full constructor */
	public TblhtpsusulanId(Long idSusulan, Tblhtppermohonan tblhtppermohonan,
			Date tarikh, String aktiviti, String keterangan,
			String namaPegawai, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idSusulan, tblhtppermohonan, tarikh, aktiviti, keterangan,
				namaPegawai, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
