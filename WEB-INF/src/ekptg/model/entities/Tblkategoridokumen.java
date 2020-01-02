package ekptg.model.entities;

/**
 * Tblkategoridokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblkategoridokumen extends AbstractTblkategoridokumen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblkategoridokumen() {
	}

	/** full constructor */
	public Tblkategoridokumen(String kodKategoriDokumen,
			String kategoriDokumen, String keterangan) {
		super(kodKategoriDokumen, kategoriDokumen, keterangan);
	}

}
