package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkborangppemohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangppemohon extends AbstractTblppkborangppemohon
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangppemohon() {
	}

	/** minimal constructor */
	public Tblppkborangppemohon(Long idBorangppemohon,
			Tblppkborangp tblppkborangp) {
		super(idBorangppemohon, tblppkborangp);
	}

	/** full constructor */
	public Tblppkborangppemohon(Long idBorangppemohon,
			Tblppkborangp tblppkborangp, Tblrujppktarafkptg tblrujppktarafkptg,
			Tblrujppksaudara tblrujppksaudara, String namaPemohon,
			String noKpBaru, String noKpLama, String jenisKp, String noKpLain,
			String jantina, String jenisAgama, Long umur, String jenisWarga,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, String noHp, String noTel, String emel,
			String noFax, String catatan, Long idNegeri, String statusPeguam,
			Long lapis, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkborangppeguams) {
		super(idBorangppemohon, tblppkborangp, tblrujppktarafkptg,
				tblrujppksaudara, namaPemohon, noKpBaru, noKpLama, jenisKp,
				noKpLain, jantina, jenisAgama, umur, jenisWarga, alamat1,
				alamat2, alamat3, bandar, poskod, noHp, noTel, emel, noFax,
				catatan, idNegeri, statusPeguam, lapis, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblppkborangppeguams);
	}

}
