package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkborangasimati entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangasimati extends AbstractTblppkborangasimati implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangasimati() {
	}

	/** minimal constructor */
	public Tblppkborangasimati(Long idBorangasimati,
			Tblppkboranga tblppkboranga, String namaSimati) {
		super(idBorangasimati, tblppkboranga, namaSimati);
	}

	/** full constructor */
	public Tblppkborangasimati(Long idBorangasimati,
			Tblppkboranga tblppkboranga, Tblrujppkbuktimati tblrujppkbuktimati,
			String namaSimati, String namaLain, String noKpBaru,
			String noKpLama, String jenisKp, String n0KpLain, Long umur,
			String jantina, String noSijilMati, String tempatMati,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, Date tarikhMati, String waktuKematian,
			String jenisWaktuKematian, String sebabMati, String catatan,
			Long idNegeri, String jenisAgama, String jenisWarga, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkborangahtas, Set tblppkborangaobs,
			Set tblppkborangapenghutangs, Set tblppkborangahas) {
		super(idBorangasimati, tblppkboranga, tblrujppkbuktimati, namaSimati,
				namaLain, noKpBaru, noKpLama, jenisKp, n0KpLain, umur, jantina,
				noSijilMati, tempatMati, alamat1, alamat2, alamat3, bandar,
				poskod, tarikhMati, waktuKematian, jenisWaktuKematian,
				sebabMati, catatan, idNegeri, jenisAgama, jenisWarga, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, tblppkborangahtas,
				tblppkborangaobs, tblppkborangapenghutangs, tblppkborangahas);
	}

}
