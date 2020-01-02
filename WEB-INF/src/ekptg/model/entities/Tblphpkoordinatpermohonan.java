package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpkoordinatpermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpkoordinatpermohonan extends
		AbstractTblphpkoordinatpermohonan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpkoordinatpermohonan() {
	}

	/** minimal constructor */
	public Tblphpkoordinatpermohonan(Long idKoordinatpermohonan) {
		super(idKoordinatpermohonan);
	}

	/** full constructor */
	public Tblphpkoordinatpermohonan(Long idKoordinatpermohonan,
			Tblphpversikoordinat tblphpversikoordinat, Long idStatuskoordinat,
			String bilanganTitik, Double darjahU, Double darjahT,
			Double minitU, Double minitT, Double saatU, Double saatT,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idKoordinatpermohonan, tblphpversikoordinat, idStatuskoordinat,
				bilanganTitik, darjahU, darjahT, minitU, minitT, saatU, saatT,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
