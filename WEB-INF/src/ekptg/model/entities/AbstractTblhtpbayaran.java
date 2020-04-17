package ekptg.model.entities;

/**
 * AbstractTblhtpbayaran entity provides the base persistence definition of the
 * Tblhtpbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpbayaran implements java.io.Serializable {

	// Fields

	private TblhtpbayaranId id;
	private Tblhtppermohonan tblhtppermohonan;

	// Constructors

	/** default constructor */
	public AbstractTblhtpbayaran() {
	}

	/** full constructor */
	public AbstractTblhtpbayaran(TblhtpbayaranId id,
			Tblhtppermohonan tblhtppermohonan) {
		this.id = id;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	// Property accessors

	public TblhtpbayaranId getId() {
		return this.id;
	}

	public void setId(TblhtpbayaranId id) {
		this.id = id;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

}