package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpperjanjianborang entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpperjanjianborang extends AbstractTblhtpperjanjianborang
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpperjanjianborang() {
	}

	/** minimal constructor */
	public Tblhtpperjanjianborang(Long idPerjanjianborang, Long idPerjanjian) {
		super(idPerjanjianborang, idPerjanjian);
	}

	/** full constructor */
	public Tblhtpperjanjianborang(Long idPerjanjianborang, Long idPerjanjian,
			Date tarikhTerima, Date tarikhTandatanganPtp, Date tarikhHantar,
			Date tarikhDaftar, Date tarikhTerimaHakmilik, String jenisBorang,
			String noPeserahanSptb, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idPerjanjianborang, idPerjanjian, tarikhTerima,
				tarikhTandatanganPtp, tarikhHantar, tarikhDaftar,
				tarikhTerimaHakmilik, jenisBorang, noPeserahanSptb, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
