package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtphakmilikagensi entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtphakmilikagensi extends AbstractTblhtphakmilikagensi
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtphakmilikagensi() {
	}

	/** minimal constructor */
	public Tblhtphakmilikagensi(Long idHakmilikagensi, Long idHakmilik,
			Long idAgensi, Long idKementerian) {
		super(idHakmilikagensi, idHakmilik, idAgensi, idKementerian);
	}

	/** full constructor */
	public Tblhtphakmilikagensi(Long idHakmilikagensi, Long idHakmilik,
			Double luasAsal, Double luasBaru, Long idAgensi,
			Long idKementerian, Date tarikhKkini, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idHakmilikagensi, idHakmilik, luasAsal, luasBaru, idAgensi,
				idKementerian, tarikhKkini, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
