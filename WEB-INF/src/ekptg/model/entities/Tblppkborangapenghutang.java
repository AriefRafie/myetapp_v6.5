package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkborangapenghutang entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangapenghutang extends AbstractTblppkborangapenghutang
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangapenghutang() {
	}

	/** minimal constructor */
	public Tblppkborangapenghutang(Long idBorangapenghutang,
			Tblppkborangasimati tblppkborangasimati) {
		super(idBorangapenghutang, tblppkborangasimati);
	}

	/** full constructor */
	public Tblppkborangapenghutang(Long idBorangapenghutang,
			Tblppkborangasimati tblppkborangasimati, String namaPenghutang,
			String noKpBaru, String noKpLama, String noKpLain, String alamat1,
			String alamat2, String alamat3, String bandar, String poskod,
			String noAkaun, String namaBank, Double jumlahHutang,
			String butiranHutang, Long idNegeri, String jenisPenghutang,
			String jenisAgama, String jenisWarga, String jenisKp, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idBorangapenghutang, tblppkborangasimati, namaPenghutang,
				noKpBaru, noKpLama, noKpLain, alamat1, alamat2, alamat3,
				bandar, poskod, noAkaun, namaBank, jumlahHutang, butiranHutang,
				idNegeri, jenisPenghutang, jenisAgama, jenisWarga, jenisKp,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
