package ekptg.model.entities;

/**
 * AbstractTblhtpperjanjian entity provides the base persistence definition of
 * the Tblhtpperjanjian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpperjanjian implements java.io.Serializable {

	// Fields

	private TblhtpperjanjianId id;

	// Constructors

	/** default constructor */
	public AbstractTblhtpperjanjian() {
	}

	/** full constructor */
	public AbstractTblhtpperjanjian(TblhtpperjanjianId id) {
		this.id = id;
	}

	// Property accessors

	public TblhtpperjanjianId getId() {
		return this.id;
	}

	public void setId(TblhtpperjanjianId id) {
		this.id = id;
	}

}