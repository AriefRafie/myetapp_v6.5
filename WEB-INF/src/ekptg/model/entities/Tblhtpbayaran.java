package ekptg.model.entities;

/**
 * Tblhtpbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpbayaran extends AbstractTblhtpbayaran implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpbayaran() {
	}

	/** full constructor */
	public Tblhtpbayaran(TblhtpbayaranId id, Tblhtppermohonan tblhtppermohonan) {
		super(id, tblhtppermohonan);
	}

}
