package ekptg.model.entities;

/**
 * AbstractTblrujenistanah entity provides the base persistence definition of
 * the Tblrujenistanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujenistanah implements java.io.Serializable {

	// Fields

	private TblrujenistanahId id;

	// Constructors

	/** default constructor */
	public AbstractTblrujenistanah() {
	}

	/** full constructor */
	public AbstractTblrujenistanah(TblrujenistanahId id) {
		this.id = id;
	}

	// Property accessors

	public TblrujenistanahId getId() {
		return this.id;
	}

	public void setId(TblrujenistanahId id) {
		this.id = id;
	}

}