package ekptg.model.entities;

/**
 * AbstractTblrujjenisdok entity provides the base persistence definition of the
 * Tblrujjenisdok entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenisdok implements java.io.Serializable {

	// Fields

	private TblrujjenisdokId id;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenisdok() {
	}

	/** full constructor */
	public AbstractTblrujjenisdok(TblrujjenisdokId id) {
		this.id = id;
	}

	// Property accessors

	public TblrujjenisdokId getId() {
		return this.id;
	}

	public void setId(TblrujjenisdokId id) {
		this.id = id;
	}

}