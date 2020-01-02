package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphppemegangpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppemegangpenyewaan extends AbstractTblphppemegangpenyewaan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppemegangpenyewaan() {
	}

	/** minimal constructor */
	public Tblphppemegangpenyewaan(Long idPemegangpenyewaan) {
		super(idPemegangpenyewaan);
	}

	/** full constructor */
	public Tblphppemegangpenyewaan(Long idPemegangpenyewaan,
			Tblphpperjanjianpenyewaan tblphpperjanjianpenyewaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppemeganglesenperjanjians) {
		super(idPemegangpenyewaan, tblphpperjanjianpenyewaan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblphppemeganglesenperjanjians);
	}

}
