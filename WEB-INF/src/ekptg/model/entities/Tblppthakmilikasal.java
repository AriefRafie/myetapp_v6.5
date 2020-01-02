package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppthakmilikasal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppthakmilikasal extends AbstractTblppthakmilikasal implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppthakmilikasal() {
	}

	/** full constructor */
	public Tblppthakmilikasal(Long idNegeri, Long idDaerah, Long idMukim,
			Long idJenishakmilik, String noHakmilik, Long idSubjaket,
			Date tarikhTerima, Date tarikhDaftar, String flagRezab,
			String flagGsa, String tempohLuput, Date tarikhLuput, String noPt,
			Long idUnitluaspt, String luasPt, String noLot, Long idUnitluaslot,
			String luasLot, Long idUnitluasambil, Long luasAmbil,
			Long idUnitluasbaru, Long luasBaru, String noPelan, String noSyit,
			String lokasi, Long idKategoritanah, String syaratNyata,
			String syaratKhas, String sekatanKepentingan, String sekatanHak,
			String jenisMilik, String ulasanPentadbir, String flagUbah,
			String noBangunan, String noTingkat, String noPetak,
			Long idPermohonan, Long idHakmilik, Long idPembatalan,
			String noKelulusan, String noDaftar, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idNegeri, idDaerah, idMukim, idJenishakmilik, noHakmilik,
				idSubjaket, tarikhTerima, tarikhDaftar, flagRezab, flagGsa,
				tempohLuput, tarikhLuput, noPt, idUnitluaspt, luasPt, noLot,
				idUnitluaslot, luasLot, idUnitluasambil, luasAmbil,
				idUnitluasbaru, luasBaru, noPelan, noSyit, lokasi,
				idKategoritanah, syaratNyata, syaratKhas, sekatanKepentingan,
				sekatanHak, jenisMilik, ulasanPentadbir, flagUbah, noBangunan,
				noTingkat, noPetak, idPermohonan, idHakmilik, idPembatalan,
				noKelulusan, noDaftar, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
