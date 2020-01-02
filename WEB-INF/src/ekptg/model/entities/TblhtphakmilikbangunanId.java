package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtphakmilikbangunanId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtphakmilikbangunanId extends AbstractTblhtphakmilikbangunanId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtphakmilikbangunanId() {
	}

	/** minimal constructor */
	public TblhtphakmilikbangunanId(Long idHakmilikbangunan,
			Tblhtppermohonan tblhtppermohonan, Long idLuas, Long idMukim,
			Long idDaerah, Long idNegeri) {
		super(idHakmilikbangunan, tblhtppermohonan, idLuas, idMukim, idDaerah,
				idNegeri);
	}

	/** full constructor */
	public TblhtphakmilikbangunanId(Long idHakmilikbangunan,
			Tblhtppermohonan tblhtppermohonan, String alamat1, String alamat2,
			String alamat3, String poskod, Long idLuas, String luas,
			Long idMukim, Long idDaerah, Long idNegeri, Double sewaBulanan,
			String ulasan, Date tarikhMula, Date tarikhTamat, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idHakmilikbangunan, tblhtppermohonan, alamat1, alamat2, alamat3,
				poskod, idLuas, luas, idMukim, idDaerah, idNegeri, sewaBulanan,
				ulasan, tarikhMula, tarikhTamat, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
