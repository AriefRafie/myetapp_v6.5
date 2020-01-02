package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkborangapeguam entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangapeguam extends AbstractTblppkborangapeguam implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangapeguam() {
	}

	/** minimal constructor */
	public Tblppkborangapeguam(Long idBorangapeguam,
			Tblppkborangapemohon tblppkborangapemohon) {
		super(idBorangapeguam, tblppkborangapemohon);
	}

	/** full constructor */
	public Tblppkborangapeguam(Long idBorangapeguam,
			Tblppkborangapemohon tblppkborangapemohon, String namaFirma,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, String noRujukanFirma, String noTel, String noFax,
			String namaPeguam, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idBorangapeguam, tblppkborangapemohon, namaFirma, alamat1,
				alamat2, alamat3, poskod, idNegeri, noRujukanFirma, noTel,
				noFax, namaPeguam, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
