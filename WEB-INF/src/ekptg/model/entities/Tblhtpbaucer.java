package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblhtpbaucer entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpbaucer extends AbstractTblhtpbaucer implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpbaucer() {
	}

	/** minimal constructor */
	public Tblhtpbaucer(Long idBaucer, Tblhtpcukaiutama tblhtpcukaiutama,
			Long idNegeri, Long idDaerah) {
		super(idBaucer, tblhtpcukaiutama, idNegeri, idDaerah);
	}

	/** full constructor */
	public Tblhtpbaucer(Long idBaucer, Tblhtpcukaiutama tblhtpcukaiutama,
			String tahun, String noBaucer, Double amaunBaucer,
			Date tarikhBaucer, Long idBank, Date tarikhBayaran, String noResit,
			Date tarikhResit, Date tarikhTerima, Date tarikhTerimaResit,
			Long idNegeri, Long idDaerah, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblhtpbayarancukais) {
		super(idBaucer, tblhtpcukaiutama, tahun, noBaucer, amaunBaucer,
				tarikhBaucer, idBank, tarikhBayaran, noResit, tarikhResit,
				tarikhTerima, tarikhTerimaResit, idNegeri, idDaerah, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, tblhtpbayarancukais);
	}

}
