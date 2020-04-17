package ekptg.model.entities;

/**
 * Tblkumpulanpengguna entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblkumpulanpengguna extends AbstractTblkumpulanpengguna implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblkumpulanpengguna() {
	}

	/** full constructor */
	public Tblkumpulanpengguna(String kodPengguna, String kodKumpulan) {
		super(kodPengguna, kodKumpulan);
	}

}
