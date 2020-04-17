package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkborangpsimati entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangpsimati extends AbstractTblppkborangpsimati implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangpsimati() {
	}

	/** minimal constructor */
	public Tblppkborangpsimati(Long idBorangpsimati,
			Tblppkborangp tblppkborangp, String namaSimati) {
		super(idBorangpsimati, tblppkborangp, namaSimati);
	}

	/** full constructor */
	public Tblppkborangpsimati(Long idBorangpsimati,
			Tblrujppkbuktimati tblrujppkbuktimati, Tblppkborangp tblppkborangp,
			String namaSimati, String namaLain, String noKpBaru,
			String noKpLama, String jenisKp, String n0KpLain, Long umur,
			String jantina, String noSijilMati, String tempatMati,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, Date tarikhMati, String waktuKematian,
			String jenisWaktuKematian, String sebabMati, String catatan,
			Long idNegeri, String jenisAgama, String jenisWarga, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkborangppenghutangs, Set tblppkborangpobs,
			Set tblppkborangphas, Set tblppkborangphtas) {
		super(idBorangpsimati, tblrujppkbuktimati, tblppkborangp, namaSimati,
				namaLain, noKpBaru, noKpLama, jenisKp, n0KpLain, umur, jantina,
				noSijilMati, tempatMati, alamat1, alamat2, alamat3, bandar,
				poskod, tarikhMati, waktuKematian, jenisWaktuKematian,
				sebabMati, catatan, idNegeri, jenisAgama, jenisWarga, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblppkborangppenghutangs, tblppkborangpobs, tblppkborangphas,
				tblppkborangphtas);
	}

}
