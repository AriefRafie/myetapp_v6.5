package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpcukaiterperinci entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpcukaiterperinci extends AbstractTblhtpcukaiterperinci
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpcukaiterperinci() {
	}

	/** minimal constructor */
	public Tblhtpcukaiterperinci(Long idCukaiterperinci,
			Tblhtphakmilikcukai tblhtphakmilikcukai,
			Tblhtpcukaiutama tblhtpcukaiutama) {
		super(idCukaiterperinci, tblhtphakmilikcukai, tblhtpcukaiutama);
	}

	/** full constructor */
	public Tblhtpcukaiterperinci(Long idCukaiterperinci,
			Tblhtphakmilikcukai tblhtphakmilikcukai,
			Tblhtpcukaiutama tblhtpcukaiutama, Double tunggakan, String tahun,
			Double cukaiKenaBayar, Double cukaiPerluBayar, Double cukaiDibayar,
			Double denda, Double bayaranLain, Double cukaiTaliair,
			Double cukaiParit, Double pengurangan, Double pengecualian,
			String noResit, Date tarikhResit, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idCukaiterperinci, tblhtphakmilikcukai, tblhtpcukaiutama,
				tunggakan, tahun, cukaiKenaBayar, cukaiPerluBayar,
				cukaiDibayar, denda, bayaranLain, cukaiTaliair, cukaiParit,
				pengurangan, pengecualian, noResit, tarikhResit, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
