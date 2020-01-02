package ekptg.model.entities;

/**
 * Tblpengguna entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpengguna extends AbstractTblpengguna implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpengguna() {
	}

	/** minimal constructor */
	public Tblpengguna(String kodStatusPengguna) {
		super(kodStatusPengguna);
	}

	/** full constructor */
	public Tblpengguna(String kodPengguna, String namaPengguna, String noKp,
			String katalaluan, String katalaluanBandingan,
			String kodStatusPengguna, String kodJenisPengguna) {
		super(kodPengguna, namaPengguna, noKp, katalaluan, katalaluanBandingan,
				kodStatusPengguna, kodJenisPengguna);
	}

}
