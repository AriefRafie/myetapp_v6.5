package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpperbadanan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpperbadanan extends AbstractTblhtpperbadanan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpperbadanan() {
	}

	/** minimal constructor */
	public Tblhtpperbadanan(TblhtpperbadananId id, Long idPerbadanan) {
		super(id, idPerbadanan);
	}

	/** full constructor */
	public Tblhtpperbadanan(TblhtpperbadananId id, Date tarikhKuasaPbdn,
			Date tarikhMsyrtJemaah, String noMemorandum, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idPerbadanan) {
		super(id, tarikhKuasaPbdn, tarikhMsyrtJemaah, noMemorandum, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idPerbadanan);
	}

}
