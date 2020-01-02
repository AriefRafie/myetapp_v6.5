package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtphakmilikId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtphakmilikId extends AbstractTblhtphakmilikId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtphakmilikId() {
	}

	/** minimal constructor */
	public TblhtphakmilikId(Long idHtphakmilik,
			Tblhtppermohonan tblhtppermohonan, Long idLuas,
			String peganganHakmilik, Long idSubkategori, Long idKategori,
			Long idDaerah, Long idNegeri, Long idMukim, Long idLot,
			Long idJenishakmilik, Long idRizab) {
		super(idHtphakmilik, tblhtppermohonan, idLuas, peganganHakmilik,
				idSubkategori, idKategori, idDaerah, idNegeri, idMukim, idLot,
				idJenishakmilik, idRizab);
	}

	/** full constructor */
	public TblhtphakmilikId(Long idHtphakmilik,
			Tblhtppermohonan tblhtppermohonan, Long idLuas,
			String peganganHakmilik, String noHakmilik, String noWarta,
			Date tarikhWarta, String noLot, String luas, String noSyit,
			String noPelan, String syarat, String sekatan, Double cukai,
			String flagPelan, String ulasan, String statusSwasta,
			String tindakanLanjut, Long idSubkategori, String lokasi,
			Double cukaiTerkini, String statusRizab, String noBangunan,
			String noTingkat, String noPtk, String statusTanah,
			Date tarikhMula, Date tarikhLuput, Double luasBersamaan,
			String jenisTanah, String flagPtp, Long idKategori, Long idDaerah,
			Long idNegeri, Long idMukim, Long idLot, Long idJenishakmilik,
			Long idRizab, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idHtphakmilik, tblhtppermohonan, idLuas, peganganHakmilik,
				noHakmilik, noWarta, tarikhWarta, noLot, luas, noSyit, noPelan,
				syarat, sekatan, cukai, flagPelan, ulasan, statusSwasta,
				tindakanLanjut, idSubkategori, lokasi, cukaiTerkini,
				statusRizab, noBangunan, noTingkat, noPtk, statusTanah,
				tarikhMula, tarikhLuput, luasBersamaan, jenisTanah, flagPtp,
				idKategori, idDaerah, idNegeri, idMukim, idLot,
				idJenishakmilik, idRizab, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
