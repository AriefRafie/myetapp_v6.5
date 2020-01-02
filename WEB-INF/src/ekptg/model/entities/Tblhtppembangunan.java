package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtppembangunan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppembangunan extends AbstractTblhtppembangunan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppembangunan() {
	}

	/** minimal constructor */
	public Tblhtppembangunan(Long idPembangunan, Long idHakmilikpegangan,
			Long idMasuk) {
		super(idPembangunan, idHakmilikpegangan, idMasuk);
	}

	/** full constructor */
	public Tblhtppembangunan(Long idPembangunan, Long idHakmilikpegangan,
			String jenisBinaan, String noRujjkr, Date tarikhBinaan,
			Double hargaBinaan, String catatan, String unitLuas, String luas,
			Double luasHektar, Double luasJalan, Double luasPadang,
			Double luasBgn, Double luasParking, Double luasLain,
			Double luasAsal, Long idMasuk, Date tarikhMasuk) {
		super(idPembangunan, idHakmilikpegangan, jenisBinaan, noRujjkr,
				tarikhBinaan, hargaBinaan, catatan, unitLuas, luas, luasHektar,
				luasJalan, luasPadang, luasBgn, luasParking, luasLain,
				luasAsal, idMasuk, tarikhMasuk);
	}

}
