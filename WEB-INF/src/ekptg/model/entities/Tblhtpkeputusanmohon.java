package ekptg.model.entities;

/**
 * Tblhtpkeputusanmohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpkeputusanmohon extends AbstractTblhtpkeputusanmohon
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpkeputusanmohon() {
	}

	/** full constructor */
	public Tblhtpkeputusanmohon(TblhtpkeputusanmohonId id,
			Tblhtppermohonan tblhtppermohonan) {
		super(id, tblhtppermohonan);
	}

}
