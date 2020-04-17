package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkhbgnfaraid entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkhbgnfaraid extends AbstractTblppkhbgnfaraid implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkhbgnfaraid() {
	}

	/** minimal constructor */
	public Tblppkhbgnfaraid(Long idHbgnfaraid, Long kodFaraid) {
		super(idHbgnfaraid, kodFaraid);
	}

	/** full constructor */
	public Tblppkhbgnfaraid(Long idHbgnfaraid,
			Tblrujppktarafkptg tblrujppktarafkptg,
			Tblrujppksaudara tblrujppksaudara, Long kodFaraid,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkobfaraids) {
		super(idHbgnfaraid, tblrujppktarafkptg, tblrujppksaudara, kodFaraid,
				keterangan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblppkobfaraids);
	}

}
