package ekptg.model.entities;

/**
 * AbstractTblhtpnotis5a entity provides the base persistence definition of the
 * Tblhtpnotis5a entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpnotis5a implements java.io.Serializable {

	// Fields

	private Tblhtpnotis5aId id;
	private Tblhtppermohonan tblhtppermohonan;

	// Constructors

	/** default constructor */
	public AbstractTblhtpnotis5a() {
	}

	/** full constructor */
	public AbstractTblhtpnotis5a(Tblhtpnotis5aId id,
			Tblhtppermohonan tblhtppermohonan) {
		this.id = id;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	// Property accessors

	public Tblhtpnotis5aId getId() {
		return this.id;
	}

	public void setId(Tblhtpnotis5aId id) {
		this.id = id;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

}