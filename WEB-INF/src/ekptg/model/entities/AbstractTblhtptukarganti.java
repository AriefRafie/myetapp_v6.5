package ekptg.model.entities;

/**
 * AbstractTblhtptukarganti entity provides the base persistence definition of
 * the Tblhtptukarganti entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtptukarganti implements java.io.Serializable {

	// Fields

	private TblhtptukargantiId id;

	// Constructors

	/** default constructor */
	public AbstractTblhtptukarganti() {
	}

	/** full constructor */
	public AbstractTblhtptukarganti(TblhtptukargantiId id) {
		this.id = id;
	}

	// Property accessors

	public TblhtptukargantiId getId() {
		return this.id;
	}

	public void setId(TblhtptukargantiId id) {
		this.id = id;
	}

}