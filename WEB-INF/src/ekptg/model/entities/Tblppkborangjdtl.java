package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkborangjdtl entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangjdtl extends AbstractTblppkborangjdtl implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangjdtl() {
	}

	/** minimal constructor */
	public Tblppkborangjdtl(Long idBorangjdtl, Tblppkborangj tblppkborangj) {
		super(idBorangjdtl, tblppkborangj);
	}

	/** full constructor */
	public Tblppkborangjdtl(Long idBorangjdtl, Tblppkborangj tblppkborangj,
			Tblppkob tblppkob, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idBorangjdtl, tblppkborangj, tblppkob, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
