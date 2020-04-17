package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkborangapemohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangapemohon extends AbstractTblppkborangapemohon
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangapemohon() {
	}

	/** minimal constructor */
	public Tblppkborangapemohon(Long idBorangapemohon,
			Tblppkboranga tblppkboranga, Tblrujppktarafkptg tblrujppktarafkptg,
			Tblrujppksaudara tblrujppksaudara) {
		super(idBorangapemohon, tblppkboranga, tblrujppktarafkptg,
				tblrujppksaudara);
	}

	/** full constructor */
	public Tblppkborangapemohon(Long idBorangapemohon,
			Tblppkboranga tblppkboranga, Tblrujppktarafkptg tblrujppktarafkptg,
			Tblrujppksaudara tblrujppksaudara, String namaPemohon,
			String noKpBaru, String noKpLama, String jenisKp, String noKpLain,
			String jantina, String jenisAgama, String jenisWarga,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, String noHp, String noTel, String emel,
			String noFax, String catatan, Long idNegeri, String statusPeguam,
			Long umur, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkborangapeguams) {
		super(idBorangapemohon, tblppkboranga, tblrujppktarafkptg,
				tblrujppksaudara, namaPemohon, noKpBaru, noKpLama, jenisKp,
				noKpLain, jantina, jenisAgama, jenisWarga, alamat1, alamat2,
				alamat3, bandar, poskod, noHp, noTel, emel, noFax, catatan,
				idNegeri, statusPeguam, umur, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblppkborangapeguams);
	}

}
