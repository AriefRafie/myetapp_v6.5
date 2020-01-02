package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkborangpob entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangpob extends AbstractTblppkborangpob implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangpob() {
	}

	/** minimal constructor */
	public Tblppkborangpob(Long idBorangpob,
			Tblppkborangpsimati tblppkborangpsimati) {
		super(idBorangpob, tblppkborangpsimati);
	}

	/** full constructor */
	public Tblppkborangpob(Long idBorangpob, Tblrujppkjenispb tblrujppkjenispb,
			Tblrujppktarafkptg tblrujppktarafkptg,
			Tblppkborangpsimati tblppkborangpsimati,
			Tblrujppksaudara tblrujppksaudara, String namaOb, String noKpBaru,
			String noKpLama, String jenisKp, String noKpLain,
			String noSuratBeranak, Date tarikhLahir, String jantina, Long umur,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, String noHp, String noTel, String catatan,
			String statusHidup, Long idNegeri, String jenisAgama,
			String jenisWarga, Long idBank, String noAkaun, Date tarikhMati,
			String waktuKematian, String jenisWaktuKematian, String statusOb,
			Double nilaiHutang, Long baFaraid, Long bbFaraid, Long lapis,
			String butiranHutang, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangphubobs) {
		super(idBorangpob, tblrujppkjenispb, tblrujppktarafkptg,
				tblppkborangpsimati, tblrujppksaudara, namaOb, noKpBaru,
				noKpLama, jenisKp, noKpLain, noSuratBeranak, tarikhLahir,
				jantina, umur, alamat1, alamat2, alamat3, bandar, poskod, noHp,
				noTel, catatan, statusHidup, idNegeri, jenisAgama, jenisWarga,
				idBank, noAkaun, tarikhMati, waktuKematian, jenisWaktuKematian,
				statusOb, nilaiHutang, baFaraid, bbFaraid, lapis,
				butiranHutang, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblppkborangphubobs);
	}

}
