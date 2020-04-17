package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkborangphubob entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangphubob extends AbstractTblppkborangphubob implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangphubob() {
	}

	/** minimal constructor */
	public Tblppkborangphubob(Long idBorangphubob,
			Tblppkborangpob tblppkborangpob, Long idParentborangpob,
			Long idSaudara) {
		super(idBorangphubob, tblppkborangpob, idParentborangpob, idSaudara);
	}

	/** full constructor */
	public Tblppkborangphubob(Long idBorangphubob,
			Tblppkborangpob tblppkborangpob, Long idParentborangpob,
			Long idSaudara, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idBorangphubob, tblppkborangpob, idParentborangpob, idSaudara,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
