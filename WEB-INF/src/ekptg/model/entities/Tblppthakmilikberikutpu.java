package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppthakmilikberikutpu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppthakmilikberikutpu extends AbstractTblppthakmilikberikutpu
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppthakmilikberikutpu() {
	}

	/** full constructor */
	public Tblppthakmilikberikutpu(Long idSubjaket, Long idNegeri,
			Long idDaerah, Long idMukim, Long idJenishakmilik,
			String noHakmilik, Date tarikhTerima, Date tarikhDaftar,
			String noPu, String noPt, String noLot, Long idUnitluaslot,
			String luasLot, Long idUnitluaspt, String luasPt,
			Long idUnitluasbaru, Long luasBaru, String flagBatal,
			String ulasan, String flagPu, String noBangunan, String noTingkat,
			String noPetak, Long idHakmilik, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idBorangk, Long idDb) {
		super(idSubjaket, idNegeri, idDaerah, idMukim, idJenishakmilik,
				noHakmilik, tarikhTerima, tarikhDaftar, noPu, noPt, noLot,
				idUnitluaslot, luasLot, idUnitluaspt, luasPt, idUnitluasbaru,
				luasBaru, flagBatal, ulasan, flagPu, noBangunan, noTingkat,
				noPetak, idHakmilik, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idBorangk, idDb);
	}

}
