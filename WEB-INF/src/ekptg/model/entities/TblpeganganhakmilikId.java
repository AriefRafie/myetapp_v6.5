package ekptg.model.entities;

import java.util.Date;

/**
 * TblpeganganhakmilikId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblpeganganhakmilikId extends AbstractTblpeganganhakmilikId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblpeganganhakmilikId() {
	}

	/** minimal constructor */
	public TblpeganganhakmilikId(Long idHakmilik, String idSubkategori,
			String idPermohonan, String idKategori, Long idDaerah,
			Long idNegeri, String idMukim, String idLot,
			String idJenishakmilik, String idRizab, String idLuas) {
		super(idHakmilik, idSubkategori, idPermohonan, idKategori, idDaerah,
				idNegeri, idMukim, idLot, idJenishakmilik, idRizab, idLuas);
	}

	/** full constructor */
	public TblpeganganhakmilikId(Long idHakmilik, String noHakmilik,
			String noBangunan, String noTingkat, String noPetak,
			Date tarikhTerima, String noLot, String noWarta, Date tarikhDaftar,
			String noFailHakmilik, String tarafHakmilik, String statusRizab,
			String tempoh, Date tarikhLuput, String noPermintaanUkur,
			Double luas, String noPelan, String noSyit, String lokasi,
			Double cukai, String hakmilikAsal, String hakmilikBerikut,
			String statusPajakan, Date tarikhMohonPajakan, String statusSewa,
			Date tarikhMohonSewa, String statusSwasta, Date tarikhMohonSwasta,
			String statusPelepasan, Date tarikhMohonPelepasan,
			String caraDapat, String idSubkategori, Double cukaiTerkini,
			String kawasanRizab, Long noRizab, Date tarikhRizab, String syarat,
			String sekatan, String idPermohonan, Date tarikhBukaFail,
			String kegunaanTapak, String kegunaanTanah, String statusSah,
			String kodLuasLama, String luasLama, String statusPelarasan,
			String noFailJofa, String noFailKjp, String noFailAgensi,
			String noFailPtg, Long idKemaskini, Date tarikhKemaskini,
			String statusGeran, String statusTukarGanti, String idKategori,
			Long idDaerah, Long idNegeri, String idMukim, String idLot,
			String idJenishakmilik, String idRizab, String idLuas) {
		super(idHakmilik, noHakmilik, noBangunan, noTingkat, noPetak,
				tarikhTerima, noLot, noWarta, tarikhDaftar, noFailHakmilik,
				tarafHakmilik, statusRizab, tempoh, tarikhLuput,
				noPermintaanUkur, luas, noPelan, noSyit, lokasi, cukai,
				hakmilikAsal, hakmilikBerikut, statusPajakan,
				tarikhMohonPajakan, statusSewa, tarikhMohonSewa, statusSwasta,
				tarikhMohonSwasta, statusPelepasan, tarikhMohonPelepasan,
				caraDapat, idSubkategori, cukaiTerkini, kawasanRizab, noRizab,
				tarikhRizab, syarat, sekatan, idPermohonan, tarikhBukaFail,
				kegunaanTapak, kegunaanTanah, statusSah, kodLuasLama, luasLama,
				statusPelarasan, noFailJofa, noFailKjp, noFailAgensi,
				noFailPtg, idKemaskini, tarikhKemaskini, statusGeran,
				statusTukarGanti, idKategori, idDaerah, idNegeri, idMukim,
				idLot, idJenishakmilik, idRizab, idLuas);
	}

}
