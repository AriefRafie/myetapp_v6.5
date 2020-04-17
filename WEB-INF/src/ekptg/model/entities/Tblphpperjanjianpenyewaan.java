package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpperjanjianpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpperjanjianpenyewaan extends
		AbstractTblphpperjanjianpenyewaan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpperjanjianpenyewaan() {
	}

	/** minimal constructor */
	public Tblphpperjanjianpenyewaan(Long idPerjanjianpenyewaan,
			String noSiriPerjanjian, Long bilPerjanjian) {
		super(idPerjanjianpenyewaan, noSiriPerjanjian, bilPerjanjian);
	}

	/** full constructor */
	public Tblphpperjanjianpenyewaan(Long idPerjanjianpenyewaan,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan,
			Long idFailhasil, String noSiriPerjanjian, Long bilPerjanjian,
			Date tarikhDaftarPerjanjian, Long idJenistnh, Long idHakmilik,
			Long idNegeri, Long idDaerah, Long idMukim, Long idJenishm,
			String noHm, Long idLot, String noLot, Date tarikhWarta,
			String noWarta, Long idMenteri, Long idAgensi, String keterangan,
			Long idTempoh, String tempoh, Date tarikhMulaPerjanjian,
			Date tarikhTamatPerjanjian, Double kadarSewa, Long idUnitluas,
			Double luas, String statusFail, Double deposit,
			Double kadarBayaran, Date tarikhBayarBerikut, Long idJenissewa,
			String statusPerjanjian, Date tarikhMohonSamb, String flagGuna,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphppemegangpenyewaans,
			Set tblphptanahdisewas, Set tblphpbayaransewas) {
		super(idPerjanjianpenyewaan, tblphppermohonanpenyewaan, idFailhasil,
				noSiriPerjanjian, bilPerjanjian, tarikhDaftarPerjanjian,
				idJenistnh, idHakmilik, idNegeri, idDaerah, idMukim, idJenishm,
				noHm, idLot, noLot, tarikhWarta, noWarta, idMenteri, idAgensi,
				keterangan, idTempoh, tempoh, tarikhMulaPerjanjian,
				tarikhTamatPerjanjian, kadarSewa, idUnitluas, luas, statusFail,
				deposit, kadarBayaran, tarikhBayarBerikut, idJenissewa,
				statusPerjanjian, tarikhMohonSamb, flagGuna, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblphppemegangpenyewaans, tblphptanahdisewas,
				tblphpbayaransewas);
	}

}
