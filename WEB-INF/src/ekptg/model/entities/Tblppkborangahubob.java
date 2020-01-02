package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkborangahubob entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangahubob extends AbstractTblppkborangahubob implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangahubob() {
	}

	/** minimal constructor */
	public Tblppkborangahubob(Long idBorangahubob,
			Tblppkborangaob tblppkborangaob, Long idParentborangaob,
			Long idSaudara) {
		super(idBorangahubob, tblppkborangaob, idParentborangaob, idSaudara);
	}

	/** full constructor */
	public Tblppkborangahubob(Long idBorangahubob,
			Tblppkborangaob tblppkborangaob, Long idParentborangaob,
			Long idSaudara, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idBorangahubob, tblppkborangaob, idParentborangaob, idSaudara,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
