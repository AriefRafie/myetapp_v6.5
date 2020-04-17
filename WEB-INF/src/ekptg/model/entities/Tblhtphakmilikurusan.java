package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtphakmilikurusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtphakmilikurusan extends AbstractTblhtphakmilikurusan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtphakmilikurusan() {
	}

	/** minimal constructor */
	public Tblhtphakmilikurusan(Long idHakmilikurusan, Long idPermohonan,
			Long idLuas, String peganganHakmilik, Long idSubkategori,
			Long idKategori, Long idDaerah, Long idNegeri, String idMukim,
			Long idLot, Long idJenishakmilik) {
		super(idHakmilikurusan, idPermohonan, idLuas, peganganHakmilik,
				idSubkategori, idKategori, idDaerah, idNegeri, idMukim, idLot,
				idJenishakmilik);
	}

	/** full constructor */
	public Tblhtphakmilikurusan(Long idHakmilikurusan, Long idPermohonan,
			Long idLuas, String peganganHakmilik, String noHakmilik,
			String noWarta, Date tarikhWarta, String noLot, String luas,
			String noSyit, String noPelan, String syarat, String sekatan,
			Double cukai, String flagPelan, String ulasan, String statusSwasta,
			String tindakanLanjut, Long idSubkategori, String lokasi,
			Double cukaiTerkini, String statusRizab, String noBangunan,
			String noTingkat, String noptk, String statusTanah,
			Date tarikhMula, Date tarikhLuput, Double luasBersamaan,
			Long idJenistanah, String flagPtp, Long idKategori, Long idDaerah,
			Long idNegeri, String idMukim, Long idLot, Long idJenishakmilik,
			Long bil, Long idJenisrizab, String noRizab, Date tarikhRizab,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(idHakmilikurusan, idPermohonan, idLuas, peganganHakmilik,
				noHakmilik, noWarta, tarikhWarta, noLot, luas, noSyit, noPelan,
				syarat, sekatan, cukai, flagPelan, ulasan, statusSwasta,
				tindakanLanjut, idSubkategori, lokasi, cukaiTerkini,
				statusRizab, noBangunan, noTingkat, noptk, statusTanah,
				tarikhMula, tarikhLuput, luasBersamaan, idJenistanah, flagPtp,
				idKategori, idDaerah, idNegeri, idMukim, idLot,
				idJenishakmilik, bil, idJenisrizab, noRizab, tarikhRizab,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
