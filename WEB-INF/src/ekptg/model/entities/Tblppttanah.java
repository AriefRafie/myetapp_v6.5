package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppttanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppttanah extends AbstractTblppttanah implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppttanah() {
	}

	/** full constructor */
	public Tblppttanah(Long idHakmilik, Long idUnitluasambil, Long luasAmbil,
			Long idUnitluasterkini, Long luasTerkini, String jarakBandar,
			String keadaanTanah, String sempadanUtara, String sempadanSelatan,
			String sempadanBarat, String sempadanTimur, String kemudahanAwam,
			String ulasanPegawai, String tanaman, Date tarikhMulaLawat,
			Date tarikhAkhirLawat, String lokasiTanah, Double hargaPasaran,
			Double hargaSeunitJpph, Double amaunPenjejasanJpph,
			Double amaunPecahpisahJpph, Double naikNilaiJpph,
			Double amaunSaguhati, Double amaunBayarPenyewa, Double amaunLain2,
			Long idPelapor, String unitHarga, Double hargaSeunitSo,
			Double bayarFee, Double bayarTanah, Double bayarPecahpisah,
			Double bayarPenjejasan, String unitHargaSo, Double hargaPasaranSo,
			Double bayarNaikNilaiso, Double hargaSeunitAkhir,
			String unitHargaAkhir, String namaPbt, String halangan,
			String flagPbt, String flagRezabMelayu, String flagBukit,
			String flagLandai, String flagRendah, String flagRata,
			String flagPaya, String flagLembah, String flagLurah,
			String flagDiusaha, String flagLapang, String flagTerbiar,
			String flagHutan, String flagBelukar, String flagSemak,
			String rupabumi, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(idHakmilik, idUnitluasambil, luasAmbil, idUnitluasterkini,
				luasTerkini, jarakBandar, keadaanTanah, sempadanUtara,
				sempadanSelatan, sempadanBarat, sempadanTimur, kemudahanAwam,
				ulasanPegawai, tanaman, tarikhMulaLawat, tarikhAkhirLawat,
				lokasiTanah, hargaPasaran, hargaSeunitJpph,
				amaunPenjejasanJpph, amaunPecahpisahJpph, naikNilaiJpph,
				amaunSaguhati, amaunBayarPenyewa, amaunLain2, idPelapor,
				unitHarga, hargaSeunitSo, bayarFee, bayarTanah,
				bayarPecahpisah, bayarPenjejasan, unitHargaSo, hargaPasaranSo,
				bayarNaikNilaiso, hargaSeunitAkhir, unitHargaAkhir, namaPbt,
				halangan, flagPbt, flagRezabMelayu, flagBukit, flagLandai,
				flagRendah, flagRata, flagPaya, flagLembah, flagLurah,
				flagDiusaha, flagLapang, flagTerbiar, flagHutan, flagBelukar,
				flagSemak, rupabumi, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
