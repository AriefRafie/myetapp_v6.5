package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkborangppenghutang entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangppenghutang extends AbstractTblppkborangppenghutang
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangppenghutang() {
	}

	/** minimal constructor */
	public Tblppkborangppenghutang(Long idBorangppenghutang,
			Tblppkborangpsimati tblppkborangpsimati) {
		super(idBorangppenghutang, tblppkborangpsimati);
	}

	/** full constructor */
	public Tblppkborangppenghutang(Long idBorangppenghutang,
			Tblppkborangpsimati tblppkborangpsimati, String namaPenghutang,
			String noKpBaru, String noKpLama, String noKpLain, String alamat1,
			String alamat2, String alamat3, String bandar, String poskod,
			String noAkaun, String namaBank, Double jumlahHutang,
			String catatan, Long idNegeri, String jenisPenghutang,
			String jenisAgama, String jenisWarga, String jenisKp, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idBorangppenghutang, tblppkborangpsimati, namaPenghutang,
				noKpBaru, noKpLama, noKpLain, alamat1, alamat2, alamat3,
				bandar, poskod, noAkaun, namaBank, jumlahHutang, catatan,
				idNegeri, jenisPenghutang, jenisAgama, jenisWarga, jenisKp,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
