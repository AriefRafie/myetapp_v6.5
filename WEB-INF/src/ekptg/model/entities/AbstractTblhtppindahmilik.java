package ekptg.model.entities;

/**
 * AbstractTblhtppindahmilik entity provides the base persistence definition of
 * the Tblhtppindahmilik entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppindahmilik implements java.io.Serializable {

	// Fields

	private TblhtppindahmilikId id;

	// Constructors

	/** default constructor */
	public AbstractTblhtppindahmilik() {
	}

	/** full constructor */
	public AbstractTblhtppindahmilik(TblhtppindahmilikId id) {
		this.id = id;
	}

	// Property accessors

	public TblhtppindahmilikId getId() {
		return this.id;
	}

	public void setId(TblhtppindahmilikId id) {
		this.id = id;
	}

}