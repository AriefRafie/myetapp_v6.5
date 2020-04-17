package ekptg.model.entities;
//yati bain
import java.util.Date;

/**
 * Tblrujnegeri entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblintrujkategoritanah extends AbstractTblintrujkategoritanah implements
		java.io.Serializable {
	

	// Constructors

	/** default constructor */
	public Tblintrujkategoritanah() {
	}

	/** minimal constructor */
	public Tblintrujkategoritanah(Long idKategoritanah) {
		super(idKategoritanah);
	}

	/** full constructor */
	public Tblintrujkategoritanah(Long idKategoritanah, String kodKategoritanah,
			String kodJPPH, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idKategoritanah, kodKategoritanah, kodJPPH, keterangan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
