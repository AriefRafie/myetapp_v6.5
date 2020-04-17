package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppengarahpmhnpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppengarahpmhnpenyewaan extends
		AbstractTblphppengarahpmhnpenyewaan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppengarahpmhnpenyewaan() {
	}

	/** minimal constructor */
	public Tblphppengarahpmhnpenyewaan(Long idTblpengarahpmhnpenyewaan) {
		super(idTblpengarahpmhnpenyewaan);
	}

	/** full constructor */
	public Tblphppengarahpmhnpenyewaan(Long idTblpengarahpmhnpenyewaan,
			Tblphppemohonpenyewaan tblphppemohonpenyewaan, String nama,
			Long idJenispengenalan, String noKp, Long idWarganegara,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idTblpengarahpmhnpenyewaan, tblphppemohonpenyewaan, nama,
				idJenispengenalan, noKp, idWarganegara, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
