package ekptg.model.entities;

/**
 * AbstractTblhtpkeputusanmohon entity provides the base persistence definition
 * of the Tblhtpkeputusanmohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpkeputusanmohon implements
		java.io.Serializable {

	// Fields

	private TblhtpkeputusanmohonId id;
	private Tblhtppermohonan tblhtppermohonan;

	// Constructors

	/** default constructor */
	public AbstractTblhtpkeputusanmohon() {
	}

	/** full constructor */
	public AbstractTblhtpkeputusanmohon(TblhtpkeputusanmohonId id,
			Tblhtppermohonan tblhtppermohonan) {
		this.id = id;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	// Property accessors

	public TblhtpkeputusanmohonId getId() {
		return this.id;
	}

	public void setId(TblhtpkeputusanmohonId id) {
		this.id = id;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

}