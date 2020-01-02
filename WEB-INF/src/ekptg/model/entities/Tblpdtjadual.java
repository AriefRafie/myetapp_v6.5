package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtjadual entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtjadual extends AbstractTblpdtjadual implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtjadual() {
	}

	/** minimal constructor */
	public Tblpdtjadual(Long idJadual) {
		super(idJadual);
	}

	/** full constructor */
	public Tblpdtjadual(Long idJadual, Tblpdtenakmen tblpdtenakmen,
			Tblpdtakta tblpdtakta, String namaJadual, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtjadualseksyens) {
		super(idJadual, tblpdtenakmen, tblpdtakta, namaJadual, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, tblpdtjadualseksyens);
	}

}
