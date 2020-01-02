package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphppemohonpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppemohonpenyewaan extends AbstractTblphppemohonpenyewaan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppemohonpenyewaan() {
	}

	/** minimal constructor */
	public Tblphppemohonpenyewaan(Long idPemohonpenyewaan) {
		super(idPemohonpenyewaan);
	}

	/** full constructor */
	public Tblphppemohonpenyewaan(Long idPemohonpenyewaan,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan,
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
			Long idJantina, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphppengarahpmhnpenyewaans) {
		super(idPemohonpenyewaan, tblphppermohonanpenyewaan, idKategoripemohon,
				nama, idJenispengenalan, noKp, noKpLama, idWarnakp,
				alamat1Tetap, alamat2Tetap, alamat3Tetap, poskodTetap,
				idBandartetap, idNegeritetap, alamat1Surat, alamat2Surat,
				alamat3Surat, poskodSurat, idBandarsurat, idNegerisurat,
				noTelRumah, noTelPejabat, extTel, noFax, noTelBimbit, emel,
				tarikhLahir, umur, idKeturunan, idWarganegara, noSijil,
				pekerjaan, tempatDaftar, statusSyarikat, modalDibenarkan,
				modalJelas, undangUndangDiperbadankan, jenisPendaftaran,
				idJantina, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblphppengarahpmhnpenyewaans);
	}

}
