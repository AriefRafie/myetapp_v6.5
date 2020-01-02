package ekptg.model.entities;

/**
 * AbstractTblhtphakmilikgadaian entity provides the base persistence definition
 * of the Tblhtphakmilikgadaian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilikgadaian implements
		java.io.Serializable {

	// Fields

	private TblhtphakmilikgadaianId id;
	private Tblhtppermohonan tblhtppermohonan;

	// Constructors

	/** default constructor */
	public AbstractTblhtphakmilikgadaian() {
	}

	/** full constructor */
	public AbstractTblhtphakmilikgadaian(TblhtphakmilikgadaianId id,
			Tblhtppermohonan tblhtppermohonan) {
		this.id = id;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	// Property accessors

	public TblhtphakmilikgadaianId getId() {
		return this.id;
	}

	public void setId(TblhtphakmilikgadaianId id) {
		this.id = id;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

}