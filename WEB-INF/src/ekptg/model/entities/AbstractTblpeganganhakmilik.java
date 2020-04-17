package ekptg.model.entities;

/**
 * AbstractTblpeganganhakmilik entity provides the base persistence definition
 * of the Tblpeganganhakmilik entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpeganganhakmilik implements
		java.io.Serializable {

	// Fields

	private TblpeganganhakmilikId id;

	// Constructors

	/** default constructor */
	public AbstractTblpeganganhakmilik() {
	}

	/** full constructor */
	public AbstractTblpeganganhakmilik(TblpeganganhakmilikId id) {
		this.id = id;
	}

	// Property accessors

	public TblpeganganhakmilikId getId() {
		return this.id;
	}

	public void setId(TblpeganganhakmilikId id) {
		this.id = id;
	}

}