package ekptg.model.entities;

/**
 * Tblproses entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblproses extends AbstractTblproses implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblproses() {
	}

	/** full constructor */
	public Tblproses(String kodProses, String namaProses, String keterangan,
			Long bmTenagakerja) {
		super(kodProses, namaProses, keterangan, bmTenagakerja);
	}

}
