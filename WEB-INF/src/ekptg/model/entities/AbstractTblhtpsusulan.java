package ekptg.model.entities;

/**
 * AbstractTblhtpsusulan entity provides the base persistence definition of the
 * Tblhtpsusulan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpsusulan implements java.io.Serializable {

	// Fields

	private TblhtpsusulanId id;
	private Tblhtppermohonan tblhtppermohonan;

	// Constructors

	/** default constructor */
	public AbstractTblhtpsusulan() {
	}

	/** full constructor */
	public AbstractTblhtpsusulan(TblhtpsusulanId id,
			Tblhtppermohonan tblhtppermohonan) {
		this.id = id;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	// Property accessors

	public TblhtpsusulanId getId() {
		return this.id;
	}

	public void setId(TblhtpsusulanId id) {
		this.id = id;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

}