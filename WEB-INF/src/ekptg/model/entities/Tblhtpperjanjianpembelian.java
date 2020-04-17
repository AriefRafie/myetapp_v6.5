package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpperjanjianpembelian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpperjanjianpembelian extends
		AbstractTblhtpperjanjianpembelian implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpperjanjianpembelian() {
	}

	/** minimal constructor */
	public Tblhtpperjanjianpembelian(Long idPerjanjianpembelian,
			Long idPerjanjian) {
		super(idPerjanjianpembelian, idPerjanjian);
	}

	/** full constructor */
	public Tblhtpperjanjianpembelian(Long idPerjanjianpembelian,
			Long idPerjanjian, Double nilaiTanah, Double hargaBeli,
			String bilHakmilikbeli, String bilPetakBeli, String bilUnitBeli,
			Date tarikhBorang14a, Double hargaTambahan,
			Date tarikhSerahBangunan, Double nilaiBangunan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idPerjanjianpembelian, idPerjanjian, nilaiTanah, hargaBeli,
				bilHakmilikbeli, bilPetakBeli, bilUnitBeli, tarikhBorang14a,
				hargaTambahan, tarikhSerahBangunan, nilaiBangunan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
