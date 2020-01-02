package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtphakmilikgadaianId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtphakmilikgadaianId extends AbstractTblhtphakmilikgadaianId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtphakmilikgadaianId() {
	}

	/** minimal constructor */
	public TblhtphakmilikgadaianId(Long idHakmilik,
			Tblhtppermohonan tblhtppermohonan, Long idNegeri, Long idDaerah,
			Long idMukim, Long idJenishakmilik, Long idLot, Long idKategori,
			Long idSubkategori, Long idLuas, Long idJenistanah, Long idRizab) {
		super(idHakmilik, tblhtppermohonan, idNegeri, idDaerah, idMukim,
				idJenishakmilik, idLot, idKategori, idSubkategori, idLuas,
				idJenistanah, idRizab);
	}

	/** full constructor */
	public TblhtphakmilikgadaianId(Long idHakmilik, String peganganhakmilik,
			Tblhtppermohonan tblhtppermohonan, Long idNegeri, Long idDaerah,
			Long idMukim, String noHakmilik, String noWarta, Date tarikhWarta,
			String noLot, String luas, String noSyit, String noPelan,
			String syarat, String sekatan, Double cukai, String statusPelan,
			String ulasan, String statusSwasta, String tindakanLanjut,
			Long idJenishakmilik, Long idLot, Long idKategori,
			Long idSubkategori, Long idLuas, String lokasi,
			Double cukaiTerkini, String statusRizab, String noBangunan,
			String noTingkat, String noPetak, String statusTanah,
			Date tarikhMula, Date tarikhLuput, Double luasBersamaan,
			Long idJenistanah, String statusPtp, Long idRizab, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idHakmilik, peganganhakmilik, tblhtppermohonan, idNegeri,
				idDaerah, idMukim, noHakmilik, noWarta, tarikhWarta, noLot,
				luas, noSyit, noPelan, syarat, sekatan, cukai, statusPelan,
				ulasan, statusSwasta, tindakanLanjut, idJenishakmilik, idLot,
				idKategori, idSubkategori, idLuas, lokasi, cukaiTerkini,
				statusRizab, noBangunan, noTingkat, noPetak, statusTanah,
				tarikhMula, tarikhLuput, luasBersamaan, idJenistanah,
				statusPtp, idRizab, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
