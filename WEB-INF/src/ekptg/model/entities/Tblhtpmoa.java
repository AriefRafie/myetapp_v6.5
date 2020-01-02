package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpmoa entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpmoa extends AbstractTblhtpmoa implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpmoa() {
	}

	/** minimal constructor */
	public Tblhtpmoa(Long idMoa, Tblhtppermohonan tblhtppermohonan) {
		super(idMoa, tblhtppermohonan);
	}

	/** full constructor */
	public Tblhtpmoa(Long idMoa, Tblhtppermohonan tblhtppermohonan,
			Date tarikhHantar, Date tarikhTerima, String noRujukanMoa,
			Date tarikhTandatangan, Date tarikhDaftar, Date tarikhBayaran,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idMoa, tblhtppermohonan, tarikhHantar, tarikhTerima,
				noRujukanMoa, tarikhTandatangan, tarikhDaftar, tarikhBayaran,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
