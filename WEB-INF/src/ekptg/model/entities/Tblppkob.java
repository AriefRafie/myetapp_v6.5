package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkob entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkob extends AbstractTblppkob implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkob() {
	}

	/** minimal constructor */
	public Tblppkob(Long idOb, Long idSimati) {
		super(idOb, idSimati);
	}

	/** full constructor */
	public Tblppkob(Long idOb, Long idSimati, String namaOb, String noKpBaru,
			String noKpLama, String jenisKp, String noKpLain,
			String noSuratBeranak, Date tarikhLahir, String jantina, Long umur,
			String alamat1, String alamat2, String alamat3, String bandar,
			String poskod, String noHp, String noTel, String catatan,
			String statusHidup, Long idTarafkptg, Long idNegeri,
			Long idSaudara, Long idJenispb, String jenisAgama,
			String jenisWarga, Long idBank, String noAkaun, Date tarikhMati,
			String waktuKematian, String jenisWaktuKematian, String statusOb,
			Double nilaiHutang, Long baFaraid, Long bbFaraid, Long lapis,
			String butiranHutang, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		 super(idOb, idSimati, namaOb, noKpBaru, noKpLama, jenisKp, noKpLain,
				noSuratBeranak, tarikhLahir, jantina, umur, alamat1, alamat2,
				alamat3, bandar, poskod, noHp, noTel, catatan, statusHidup,
				idTarafkptg, idNegeri, idSaudara, idJenispb, jenisAgama,
				jenisWarga, idBank, noAkaun, tarikhMati, waktuKematian,
				jenisWaktuKematian, statusOb, nilaiHutang, baFaraid, bbFaraid,
				lapis, butiranHutang, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
