package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpcukaitemp entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpcukaitemp extends AbstractTblhtpcukaitemp implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpcukaitemp() {
	}

	/** minimal constructor */
	public Tblhtpcukaitemp(Long idCukaitemp, Tblhtppermohonan tblhtppermohonan,
			Long idDaerah, Long idNegeri, Long idMukim) {
		super(idCukaitemp, tblhtppermohonan, idDaerah, idNegeri, idMukim);
	}

	/** full constructor */
	public Tblhtpcukaitemp(Long idCukaitemp, Tblhtppermohonan tblhtppermohonan,
			Double tunggakan, String tahun, Double cukaiKenaBayar,
			Double cukaiPerluBayar, Double cukaiDibayar, Double denda,
			Double bayaranLain, Double cukaiTaliair, Double cukaiParit,
			Double pengurangan, Double pengecualian, String noResit,
			Date tarikhResit, Long idDaerah, Long idNegeri, Long idMukim,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idCukaitemp, tblhtppermohonan, tunggakan, tahun, cukaiKenaBayar,
				cukaiPerluBayar, cukaiDibayar, denda, bayaranLain,
				cukaiTaliair, cukaiParit, pengurangan, pengecualian, noResit,
				tarikhResit, idDaerah, idNegeri, idMukim, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
