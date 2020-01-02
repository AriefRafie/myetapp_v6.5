package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkperayu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkperayu extends AbstractTblppkperayu implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkperayu() {
	}

	/** minimal constructor */
	public Tblppkperayu(Long idPerayu, Tblppkrayuan tblppkrayuan) {
		super(idPerayu, tblppkrayuan);
	}

	/** full constructor */
	public Tblppkperayu(Long idPerayu, Tblppkrayuan tblppkrayuan,
			String namaPerayu, String noKpBaru, String noKpLama,
			String noKpLain, String alamat1, String alamat2, String alamat3,
			String bandar, String poskod, Long idTarafkptg, Long idNegeri,
			Long idDaerah, String jenisKp, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idPerayu, tblppkrayuan, namaPerayu, noKpBaru, noKpLama, noKpLain,
				alamat1, alamat2, alamat3, bandar, poskod, idTarafkptg,
				idNegeri, idDaerah, jenisKp, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
