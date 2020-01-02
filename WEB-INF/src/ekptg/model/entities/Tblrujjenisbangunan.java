package ekptg.model.entities;
//yati
import java.util.Date;

/**
 * Tblrujnegeri entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujjenisbangunan extends AbstractTblrujjenisbangunan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenisbangunan() {
	}

	/** minimal constructor */
	public Tblrujjenisbangunan(Long idKategoritanah) {
		super(idKategoritanah);
	}

	/** full constructor */
	public Tblrujjenisbangunan(Long idJenisBangunan, String namaBangunan,
			String keteranganBangunan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idJenisBangunan, namaBangunan, keteranganBangunan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
