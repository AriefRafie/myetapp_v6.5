package ekptg.model.entities;

/**
 * Tblrujjenisopb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujjenisopb extends AbstractTblrujjenisopb implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenisopb() {
	}

	/** minimal constructor */
	public Tblrujjenisopb(Long idRujjenisopb) {
		super(idRujjenisopb);
	}

	/** full constructor */
	public Tblrujjenisopb(Long idRujjenisopb, String kodNopb, String keterangan) {
		super(idRujjenisopb, kodNopb, keterangan);
	}

}
