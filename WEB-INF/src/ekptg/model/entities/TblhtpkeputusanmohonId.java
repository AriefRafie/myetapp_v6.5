package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtpkeputusanmohonId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtpkeputusanmohonId extends AbstractTblhtpkeputusanmohonId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtpkeputusanmohonId() {
	}

	/** minimal constructor */
	public TblhtpkeputusanmohonId(Long idKeputusan,
			Tblhtppermohonan tblhtppermohonan) {
		super(idKeputusan, tblhtppermohonan);
	}

	/** full constructor */
	public TblhtpkeputusanmohonId(Long idKeputusan,
			Tblhtppermohonan tblhtppermohonan, String noRujukanKeputusan,
			Date tarikhKeputusan, String status, String ulasan,
			String keputusanKjp, String ulasanKjp, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idKeputusan, tblhtppermohonan, noRujukanKeputusan,
				tarikhKeputusan, status, ulasan, keputusanKjp, ulasanKjp,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
