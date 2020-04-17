package ekptg.model.entities;

/**
 * Tblkumpulan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblkumpulan extends AbstractTblkumpulan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblkumpulan() {
	}

	/** full constructor */
	public Tblkumpulan(String kodKumpulan, String kumpulan, String keterangan) {
		super(kodKumpulan, kumpulan, keterangan);
	}

}
