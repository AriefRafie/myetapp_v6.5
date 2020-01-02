package ekptg.model.entities;

/**
 * Tblborang entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblborang extends AbstractTblborang implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblborang() {
	}

	/** full constructor */
	public Tblborang(String kodBorang, String namaBorang, String keterangan) {
		super(kodBorang, namaBorang, keterangan);
	}

}
