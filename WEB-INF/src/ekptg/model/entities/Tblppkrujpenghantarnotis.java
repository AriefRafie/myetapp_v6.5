package ekptg.model.entities;


/**
 * Tblppkrujpenghantarnotis entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkrujpenghantarnotis extends AbstractTblppkrujpenghantarnotis implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkrujpenghantarnotis() {
	}

	/** minimal constructor */
	public Tblppkrujpenghantarnotis(Long idPejabatJkptg) {
		super(idPejabatJkptg);
	}

	/** full constructor */
	public Tblppkrujpenghantarnotis(Long idPejabatJkptg, String nama,
			String kodPenghantarNotis) {
		super(idPejabatJkptg, nama, kodPenghantarNotis);
	}

}
