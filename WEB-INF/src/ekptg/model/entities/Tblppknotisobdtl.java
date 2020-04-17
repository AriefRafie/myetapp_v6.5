package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppknotisobdtl entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppknotisobdtl extends AbstractTblppknotisobdtl implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppknotisobdtl() {
	}

	/** minimal constructor */
	public Tblppknotisobdtl(Long idNotisobdtl,
			Tblppknotisobmst tblppknotisobmst, Tblppkob tblppkob) {
		super(idNotisobdtl, tblppknotisobmst, tblppkob);
	}

	/** full constructor */
	public Tblppknotisobdtl(Long idNotisobdtl,
			Tblppknotisobmst tblppknotisobmst, Tblppkob tblppkob,
			String namaPenerima, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idNotisobdtl, tblppknotisobmst, tblppkob, namaPenerima, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
