package ekptg.model.entities;

/**
 * Tbljenispengguna entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tbljenispengguna extends AbstractTbljenispengguna implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tbljenispengguna() {
	}

	/** full constructor */
	public Tbljenispengguna(String kodJenisPengguna, String jenisPengguna,
			String keterangan) {
		super(kodJenisPengguna, jenisPengguna, keterangan);
	}

}
