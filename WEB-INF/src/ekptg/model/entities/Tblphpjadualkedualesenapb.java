package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpjadualkedualesenapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpjadualkedualesenapb extends
		AbstractTblphpjadualkedualesenapb implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpjadualkedualesenapb() {
	}

	/** minimal constructor */
	public Tblphpjadualkedualesenapb(Long idJadualkedualesenapb) {
		super(idJadualkedualesenapb);
	}

	/** full constructor */
	public Tblphpjadualkedualesenapb(Long idJadualkedualesenapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama, Long idFail,
			String noSiriLesen, Date tarikhKeluarlesen, String lokasi,
			String tempoh, Long idTempoh, Date tarikhMulaLesen,
			Date tarikhTamatlesen, Double luas, Long idUnitluas,
			String statusLesen, Long idJenissewa, String jenisLesen,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphppemeganglesenapbs,
			Set tblphpborangas, Set tblphpversikoordinats,
			Set tblphplaporanpasirs) {
		super(idJadualkedualesenapb, tblphppmohonnjdualpertama, idFail,
				noSiriLesen, tarikhKeluarlesen, lokasi, tempoh, idTempoh,
				tarikhMulaLesen, tarikhTamatlesen, luas, idUnitluas,
				statusLesen, idJenissewa, jenisLesen, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblphppemeganglesenapbs,
				tblphpborangas, tblphpversikoordinats, tblphplaporanpasirs);
	}

}
