package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppemohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppemohon extends AbstractTblphppemohon implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppemohon() {
	}

	/** full constructor */
	public Tblphppemohon(Long idPermohonan, Long idAgensi, Long idMenteri,
			Long idKategoripemohon, String nama, Long idJenispengenalan,
			String noKp, String noKpLama, Long idWarnakp, String alamat1Tetap,
			String alamat2Tetap, String alamat3Tetap, String poskodTetap,
			Long idBandartetap, Long idNegeritetap, String alamat1Surat,
			String alamat2Surat, String alamat3Surat, String poskodSurat,
			Long idBandarsurat, Long idNegerisurat, String noTelRumah,
			String noTelPejabat, String extTel, String noFax,
			String noTelBimbit, String emel, Date tarikhLahir, Long umur,
			Long idKeturunan, Long idWarganegara, String noSijil,
			String pekerjaan, String tempatDaftar, String statusSyarikat,
			Double modalDibenarkan, Double modalJelas,
			String undangUndangDiperbadankan, String jenisPendaftaran,
			Long idPejabat, Long idJantina, Long idPejabatjkptg, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idPermohonan, idAgensi, idMenteri, idKategoripemohon, nama,
				idJenispengenalan, noKp, noKpLama, idWarnakp, alamat1Tetap,
				alamat2Tetap, alamat3Tetap, poskodTetap, idBandartetap,
				idNegeritetap, alamat1Surat, alamat2Surat, alamat3Surat,
				poskodSurat, idBandarsurat, idNegerisurat, noTelRumah,
				noTelPejabat, extTel, noFax, noTelBimbit, emel, tarikhLahir,
				umur, idKeturunan, idWarganegara, noSijil, pekerjaan,
				tempatDaftar, statusSyarikat, modalDibenarkan, modalJelas,
				undangUndangDiperbadankan, jenisPendaftaran, idPejabat,
				idJantina, idPejabatjkptg, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
