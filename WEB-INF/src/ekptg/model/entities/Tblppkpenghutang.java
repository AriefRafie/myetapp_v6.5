package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkpenghutang entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkpenghutang extends AbstractTblppkpenghutang implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkpenghutang() {
	}

	/** minimal constructor */
	public Tblppkpenghutang(Long idPenghutang) {
		super(idPenghutang);
	}

	/** full constructor */
	public Tblppkpenghutang(Long idPenghutang, Long idSimati,
			String namaPenghutang, String noKpBaru, String noKpLama,
			String noKpLain, String alamat1, String alamat2, String alamat3,
			String bandar, String poskod, String noAkaun, String namaBank,
			Double jumlahHutang, String butiranHutang, Long idNegeri,
			String jenisPenghutang, String jenisAgama, String jenisWarga,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, String jenisKp) {
		super(idPenghutang, idSimati, namaPenghutang, noKpBaru, noKpLama,
				noKpLain, alamat1, alamat2, alamat3, bandar, poskod, noAkaun,
				namaBank, jumlahHutang, butiranHutang, idNegeri,
				jenisPenghutang, jenisAgama, jenisWarga, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini,jenisKp);
	}

}
