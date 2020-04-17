package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppemohonpelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppemohonpelepasan extends AbstractTblphppemohonpelepasan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppemohonpelepasan() {
	}

	/** minimal constructor */
	public Tblphppemohonpelepasan(Long idPemohonpelepasan) {
		super(idPemohonpelepasan);
	}

	/** full constructor */
	public Tblphppemohonpelepasan(Long idPemohonpelepasan,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan,
			Long idKategoripemohon, String nama, Long idJenispengenalan,
			String noKp, String alamat1Tetap, String alamat2Tetap,
			String alamat3Tetap, String poskodTetap, Long idBandartetap,
			Long idNegeritetap, String alamat1Surat, String alamat2Surat,
			String alamat3Surat, String poskodSurat, Long idBandarsurat,
			Long idNegerisurat, String noTelPejabat, String extTel,
			String noFax, String noTelBimbit, String emel, String tempatDaftar,
			String statusSyarikat, Double modalDibenarkan, Double modalJelas,
			String undangUndangDiperbadankan, String jenisPendaftaran,
			Long idAgensi, Long idMenteri, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, String flagSyarikat) {
		super(idPemohonpelepasan, tblphppermohonanpelepasan, idKategoripemohon,
				nama, idJenispengenalan, noKp, alamat1Tetap, alamat2Tetap,
				alamat3Tetap, poskodTetap, idBandartetap, idNegeritetap,
				alamat1Surat, alamat2Surat, alamat3Surat, poskodSurat,
				idBandarsurat, idNegerisurat, noTelPejabat, extTel, noFax,
				noTelBimbit, emel, tempatDaftar, statusSyarikat,
				modalDibenarkan, modalJelas, undangUndangDiperbadankan,
				jenisPendaftaran, idAgensi, idMenteri, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, flagSyarikat);
	}

}
