package ekptg.model.entities;

/**
 * Tbllangkah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tbllangkah extends AbstractTbllangkah implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tbllangkah() {
	}

	/** full constructor */
	public Tbllangkah(String kodProses, String kodLangkah, String namaLangkah,
			String keterangan, Long bmMasa, Long bmKos, String alamatLangkah) {
		super(kodProses, kodLangkah, namaLangkah, keterangan, bmMasa, bmKos,
				alamatLangkah);
	}

}
