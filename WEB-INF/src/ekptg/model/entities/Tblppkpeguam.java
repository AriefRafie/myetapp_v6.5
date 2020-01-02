package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkpeguam entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkpeguam extends AbstractTblppkpeguam implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkpeguam() {
	}

	/** minimal constructor */
	public Tblppkpeguam(Long idPeguam) 
        {
		super(idPeguam);
	}

	/** full constructor */
	public Tblppkpeguam(Long idPeguam, Long idPemohon,
                        //Tblppkpemohon tblppkpemohon,
			String namaFirma, String alamat1, String alamat2, String alamat3,
			String bandar, String poskod, Long idNegeri, String noRujukanFirma,
			String noTel, String noFax, String noSyarikat, String namaPeguam,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, String emel) {
		super(idPeguam, idPemohon, namaFirma, alamat1, alamat2, alamat3,
				bandar, poskod, idNegeri, noRujukanFirma, noTel, noFax,
				noSyarikat, namaPeguam, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, emel);
	}

}
