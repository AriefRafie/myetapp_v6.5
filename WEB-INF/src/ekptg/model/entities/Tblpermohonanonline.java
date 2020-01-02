package ekptg.model.entities;

/**
 * Tblpermohonanonline entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpermohonanonline extends AbstractTblpermohonanonline implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpermohonanonline() {
	}

	/** minimal constructor */
	public Tblpermohonanonline(Long idPermohonanonline) {
		super(idPermohonanonline);
	}

	/** full constructor */
	public Tblpermohonanonline(Long idPermohonanonline,
			String noPermohonanOnline, Long idPermohonan) {
		super(idPermohonanonline, noPermohonanOnline, idPermohonan);
	}

}
