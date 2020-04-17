package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphppemeganglesenperjanjian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppemeganglesenperjanjian extends
		AbstractTblphppemeganglesenperjanjian implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppemeganglesenperjanjian() {
	}

	/** minimal constructor */
	public Tblphppemeganglesenperjanjian(Long idPemeganglesenperjanjian) {
		super(idPemeganglesenperjanjian);
	}

	/** full constructor */
	public Tblphppemeganglesenperjanjian(Long idPemeganglesenperjanjian,
			Tblphppemegangpenyewaan tblphppemegangpenyewaan,
			Tblphppemeganglesenapb tblphppemeganglesenapb, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppemegangs) {
		super(idPemeganglesenperjanjian, tblphppemegangpenyewaan,
				tblphppemeganglesenapb, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblphppemegangs);
	}

}
