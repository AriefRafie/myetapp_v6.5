package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkborangppeguam entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangppeguam extends AbstractTblppkborangppeguam implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangppeguam() {
	}

	/** minimal constructor */
	public Tblppkborangppeguam(Long idBorangppeguam,
			Tblppkborangppemohon tblppkborangppemohon) {
		super(idBorangppeguam, tblppkborangppemohon);
	}

	/** full constructor */
	public Tblppkborangppeguam(Long idBorangppeguam,
			Tblppkborangppemohon tblppkborangppemohon, String namaFirma,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, String noRujukanFirma, String noTel, String noFax,
			String namaPeguam, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idBorangppeguam, tblppkborangppemohon, namaFirma, alamat1,
				alamat2, alamat3, poskod, idNegeri, noRujukanFirma, noTel,
				noFax, namaPeguam, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
