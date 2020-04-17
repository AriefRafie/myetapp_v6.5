package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppksimati entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppksimati extends AbstractTblppksimati implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppksimati() {
	}

	/** minimal constructor */
	public Tblppksimati(Long idSimati,
			String namaSimati) {
		super(idSimati, namaSimati);
	}

	/** full constructor */
	public Tblppksimati(Long idSimati, Long idPermohonan,Long idBuktiMati,
			String namaSimati,
			String namaLain, String noKpBaru, String noKpLama, String jenisKp,
			String n0KpLain, Long umur, String jantina, String noSijilMati,
			String tempatMati, String alamat1, String alamat2, String alamat3,
			String bandar, String poskod, Date tarikhMati,
			String waktuKematian, String jenisWaktuMati, String sebabMati,
			String catatan, Long idNegeri, String jenisAgama,
			String jenisWarga, Date tarikhKkini, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkpenghutangs, Set tblppkhas, Set tblppkobs, Set tblppkhtas) {
		super(idSimati, idPermohonan, idBuktiMati, namaSimati,
				namaLain, noKpBaru, noKpLama, jenisKp, n0KpLain, umur, jantina,
				noSijilMati, tempatMati, alamat1, alamat2, alamat3, bandar,
				poskod, tarikhMati, waktuKematian, jenisWaktuMati, sebabMati,
				catatan, idNegeri, jenisAgama, jenisWarga, tarikhKkini,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblppkpenghutangs, tblppkhas, tblppkobs, tblppkhtas);
	}

}
