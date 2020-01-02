package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptpihakberkepentingan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptpihakberkepentingan extends
		AbstractTblpptpihakberkepentingan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptpihakberkepentingan() {
	}

	/** full constructor */
	public Tblpptpihakberkepentingan(Long idJenispb, String noKpLama,
			Long idJenisnopb, String noPb, Long jantina, Long idBangsa,
			Long idWarganegara, String namaPb, String namaKp, String flagHidup,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, String noTelRumah, String noHp, String noFax,
			Long syerAtas, Long syerBawah, String flagBayarAward,
			String flagBantahan, String flagMahkamah,
			String flagPermintaanUkur, Double jumlahAward, String flagBorangj,
			String flagBayarPu, String flagPembayaranAward,
			String flagBayarBantahan, String noAkaun, Long idBank,
			String jumlahAwardPu, Double jumlahAwardBantahan,
			Double jumlahFaedah, String jenisLain2Pb, String flagJenisDaftarpb,
			Long idHakmilik, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(idJenispb, noKpLama, idJenisnopb, noPb, jantina, idBangsa,
				idWarganegara, namaPb, namaKp, flagHidup, alamat1, alamat2,
				alamat3, poskod, idNegeri, noTelRumah, noHp, noFax, syerAtas,
				syerBawah, flagBayarAward, flagBantahan, flagMahkamah,
				flagPermintaanUkur, jumlahAward, flagBorangj, flagBayarPu,
				flagPembayaranAward, flagBayarBantahan, noAkaun, idBank,
				jumlahAwardPu, jumlahAwardBantahan, jumlahFaedah, jenisLain2Pb,
				flagJenisDaftarpb, idHakmilik, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
