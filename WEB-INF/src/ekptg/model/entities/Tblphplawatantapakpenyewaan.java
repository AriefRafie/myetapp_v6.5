package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphplawatantapakpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphplawatantapakpenyewaan extends
		AbstractTblphplawatantapakpenyewaan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphplawatantapakpenyewaan() {
	}

	/** minimal constructor */
	public Tblphplawatantapakpenyewaan(Long idLawatantapakpenyewaan) {
		super(idLawatantapakpenyewaan);
	}

	/** full constructor */
	public Tblphplawatantapakpenyewaan(Long idLawatantapakpenyewaan,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idLawatantapak) {
		super(idLawatantapakpenyewaan, tblphppermohonanpenyewaan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idLawatantapak);
	}

}
