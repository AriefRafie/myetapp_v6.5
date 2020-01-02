package ekptg.model.entities;


/**
 * Tblrujbulan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujbulan extends AbstractTblrujbulan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujbulan() {
	}

	/** minimal constructor */
	public Tblrujbulan(Long idBulan) {
		super(idBulan);
	}

	/** full constructor */
	public Tblrujbulan(Long idBulan, String kodBulan, String namaBulan) {
		super(idBulan, kodBulan, namaBulan);
	}

}
