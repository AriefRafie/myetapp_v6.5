package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppembelipasir entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppembelipasir extends AbstractTblphppembelipasir implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppembelipasir() {
	}

	/** minimal constructor */
	public Tblphppembelipasir(Long idPembelipasir) {
		super(idPembelipasir);
	}

	/** full constructor */
	public Tblphppembelipasir(Long idPembelipasir,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama, String nama,
			Long idJenispengenalan, String noKp, String noKpLama,
			Long idWarnakp, String alamat1, String alamat2, String alamat3,
			String poskod, Long idBandar, Long idNegeri, String noTelRumah,
			String noTelPejabat, String extTel, String noFax,
			String noTelBimbit, String emel, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idPembelipasir, tblphppmohonnjdualpertama, nama,
				idJenispengenalan, noKp, noKpLama, idWarnakp, alamat1, alamat2,
				alamat3, poskod, idBandar, idNegeri, noTelRumah, noTelPejabat,
				extTel, noFax, noTelBimbit, emel, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
