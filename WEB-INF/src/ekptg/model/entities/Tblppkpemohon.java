package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkpemohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkpemohon extends AbstractTblppkpemohon implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkpemohon() {
	}

	/** minimal constructor */
	public Tblppkpemohon(Long idPemohon) {
		super(idPemohon);
	}

	/** full constructor */
	public Tblppkpemohon(Long idPemohon, Long idPermohonan ,
                        Long idTarafkptg, Long idSaudara,
                       // Tblrujppktarafkptg tblrujppktarafkptg,
			//Tblrujppksaudara tblrujppksaudara, 
                        String namaPemohon,
			String noKpBaru, String noKpLama, String jenisKp, String noKpLain,
			Long umur, String jantina, String jenisAgama, String jenisWarga,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, String noHp, String noTel, String emel,
			String noFax, String catatan, Long idNegeri, String statusPeguam,
			String statusPemohon, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkpeguams) {
		super(idPemohon, idPermohonan ,idTarafkptg, idSaudara, namaPemohon, noKpBaru, noKpLama, jenisKp,
				noKpLain, umur, jantina, jenisAgama, jenisWarga, alamat1,
				alamat2, alamat3, bandar, poskod, noHp, noTel, emel, noFax,
				catatan, idNegeri, statusPeguam, statusPemohon, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, tblppkpeguams);
	}

}
