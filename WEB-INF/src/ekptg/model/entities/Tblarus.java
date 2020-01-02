package ekptg.model.entities;

/**
 * Tblarus entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblarus extends AbstractTblarus implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblarus() {
	}

	/** full constructor */
	public Tblarus(String kodLangkah, String kodArus, String namaArus,
			String keterangan) {
		super(kodLangkah, kodArus, namaArus, keterangan);
	}

}
