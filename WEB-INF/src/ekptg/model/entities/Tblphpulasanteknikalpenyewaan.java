package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpulasanteknikalpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpulasanteknikalpenyewaan extends
		AbstractTblphpulasanteknikalpenyewaan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpulasanteknikalpenyewaan() {
	}

	/** minimal constructor */
	public Tblphpulasanteknikalpenyewaan(Long idUlasanteknikalpenyewaan) {
		super(idUlasanteknikalpenyewaan);
	}

	/** full constructor */
	public Tblphpulasanteknikalpenyewaan(Long idUlasanteknikalpenyewaan,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idUlasanteknikal) {
		super(idUlasanteknikalpenyewaan, tblphppermohonanpenyewaan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idUlasanteknikal);
	}

}
