package ekptg.model.entities;

/**
 * AbstractTblhtppenswastaan entity provides the base persistence definition of
 * the Tblhtppenswastaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppenswastaan implements java.io.Serializable {

	// Fields

	private TblhtppenswastaanId id;
	private Tblhtppermohonan tblhtppermohonan;

	// Constructors

	/** default constructor */
	public AbstractTblhtppenswastaan() {
	}

	/** full constructor */
	public AbstractTblhtppenswastaan(TblhtppenswastaanId id,
			Tblhtppermohonan tblhtppermohonan) {
		this.id = id;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	// Property accessors

	public TblhtppenswastaanId getId() {
		return this.id;
	}

	public void setId(TblhtppenswastaanId id) {
		this.id = id;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

}