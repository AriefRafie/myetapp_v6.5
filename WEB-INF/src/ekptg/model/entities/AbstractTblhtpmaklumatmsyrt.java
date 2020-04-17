package ekptg.model.entities;

/**
 * AbstractTblhtpmaklumatmsyrt entity provides the base persistence definition
 * of the Tblhtpmaklumatmsyrt entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpmaklumatmsyrt implements
		java.io.Serializable {

	// Fields

	private TblhtpmaklumatmsyrtId id;

	// Constructors

	/** default constructor */
	public AbstractTblhtpmaklumatmsyrt() {
	}

	/** full constructor */
	public AbstractTblhtpmaklumatmsyrt(TblhtpmaklumatmsyrtId id) {
		this.id = id;
	}

	// Property accessors

	public TblhtpmaklumatmsyrtId getId() {
		return this.id;
	}

	public void setId(TblhtpmaklumatmsyrtId id) {
		this.id = id;
	}

}