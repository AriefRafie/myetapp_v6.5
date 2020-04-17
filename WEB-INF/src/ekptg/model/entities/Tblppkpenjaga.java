package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkpenjaga entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkpenjaga extends AbstractTblppkpenjaga implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkpenjaga() {
	}

	/** minimal constructor */
	public Tblppkpenjaga(Long idPenjaga, Long idObminor) {
		super(idPenjaga, idObminor);
	}

	/** full constructor */
	public Tblppkpenjaga(Long idPenjaga, Long idObminor, String noKpBaru,
			String noKpLama, String jenisKp, String noKpLain,
			String namaPenjaga, String jantina, String jenisAgama,
			String jenisWarga, Long umur, String alamat1, String alamat2,
			String alamat3, String poskod, String bandar, Long idNegeri,
			String catatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPenjaga, idObminor, noKpBaru, noKpLama, jenisKp, noKpLain,
				namaPenjaga, jantina, jenisAgama, jenisWarga, umur, alamat1,
				alamat2, alamat3, poskod, bandar, idNegeri, catatan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
