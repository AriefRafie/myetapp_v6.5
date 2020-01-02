package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkborangaob entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangaob extends AbstractTblppkborangaob implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangaob() {
	}

	/** minimal constructor */
	public Tblppkborangaob(Long idBorangaob,
			Tblppkborangasimati tblppkborangasimati,
			Tblrujppktarafkptg tblrujppktarafkptg) {
		super(idBorangaob, tblppkborangasimati, tblrujppktarafkptg);
	}

	/** full constructor */
	public Tblppkborangaob(Long idBorangaob, Tblrujppkjenispb tblrujppkjenispb,
			Tblppkborangasimati tblppkborangasimati,
			Tblrujppktarafkptg tblrujppktarafkptg,
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
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangahubobs) {
		super(idBorangaob, tblrujppkjenispb, tblppkborangasimati,
				tblrujppktarafkptg, tblrujppksaudara, namaOb, noKpBaru,
				noKpLama, jenisKp, noKpLain, noSuratBeranak, tarikhLahir,
				jantina, umur, alamat1, alamat2, alamat3, bandar, poskod, noHp,
				noTel, catatan, statusHidup, idNegeri, jenisAgama, jenisWarga,
				idBank, noAkaun, tarikhMati, waktuKematian, jenisWaktuKematian,
				statusOb, nilaiHutang, baFaraid, bbFaraid, lapis,
				butiranHutang, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblppkborangahubobs);
	}

}
