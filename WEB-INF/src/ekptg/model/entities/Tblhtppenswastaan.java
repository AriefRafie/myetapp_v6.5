package ekptg.model.entities;

/**
 * Tblhtppenswastaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppenswastaan extends AbstractTblhtppenswastaan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppenswastaan() {
	}

	/** full constructor */
	public Tblhtppenswastaan(TblhtppenswastaanId id,
			Tblhtppermohonan tblhtppermohonan) {
		super(id, tblhtppermohonan);
	}

}
