package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkhubunganob entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkhubunganob extends AbstractTblppkhubunganob implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkhubunganob() {
	}

	/** minimal constructor */
	public Tblppkhubunganob(Long idHubunganob) {
		super(idHubunganob);
	}

	/** full constructor */
	public Tblppkhubunganob(Long idHubunganob, Long idOb,
			Long idParentob, Long idSaudara, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idHubunganob, idOb, idParentob, idSaudara, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
