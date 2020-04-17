package ekptg.model.entities;

/**
 * Tblhtpbajetguna entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpbajetguna extends AbstractTblhtpbajetguna implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpbajetguna() {
	}
 	/** minimal constructor */
	public Tblhtpbajetguna(Long idBajetguna,
			Tblhtpbajet tblhtpbajet,Tblhtpbayarancukai tblhtpbayarancukai) {
		super(idBajetguna, tblhtpbajet,tblhtpbayarancukai);
	}

	/** full constructor */
	public Tblhtpbajetguna(Long idBajetguna,Tblhtpbajet idBajet,
			Tblhtpbayarancukai tblhtpbayarancukai,  Long guna,
			Long baki) {
		super(idBajetguna,idBajet,tblhtpbayarancukai, guna, baki);
	}
     

}
