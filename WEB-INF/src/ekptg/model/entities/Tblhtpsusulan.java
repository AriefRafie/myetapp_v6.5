package ekptg.model.entities;

/**
 * Tblhtpsusulan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpsusulan extends AbstractTblhtpsusulan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpsusulan() {
	}

	/** full constructor */
	public Tblhtpsusulan(TblhtpsusulanId id, Tblhtppermohonan tblhtppermohonan) {
		super(id, tblhtppermohonan);
	}

}
