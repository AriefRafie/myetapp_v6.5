package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpperenggankkpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpperenggankkpenyewaan extends
		AbstractTblphpperenggankkpenyewaan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpperenggankkpenyewaan() {
	}

	/** minimal constructor */
	public Tblphpperenggankkpenyewaan(Long idPerenggankkpenyewaan) {
		super(idPerenggankkpenyewaan);
	}

	/** full constructor */
	public Tblphpperenggankkpenyewaan(Long idPerenggankkpenyewaan,
			Tblphpkertaskerjapenyewaan tblphpkertaskerjapenyewaan,
			String perenggan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPerenggankkpenyewaan, tblphpkertaskerjapenyewaan, perenggan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
