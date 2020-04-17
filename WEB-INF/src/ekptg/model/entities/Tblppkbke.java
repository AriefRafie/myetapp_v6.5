package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppkbke entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkbke extends AbstractTblppkbke implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkbke() {
	}

	/** minimal constructor */
	public Tblppkbke(Long idBke, Tblppkpermohonan tblppkpermohonan) {
		super(idBke, tblppkpermohonan);
	}

	/** full constructor */
	public Tblppkbke(Long idBke, Tblrujppkjenisperintah tblrujppkjenisperintah,
			Tblppkpermohonan tblppkpermohonan, Tblrujppkunit tblrujppkunit,
			Long idNegeri, Long idDaerah, Date tarikhMohon,
			Long idNegeriunitpsk, String alasan1, String alasan2,
			String alasan3, String alasan4, String alasan5, String alasan6,
			String alasan7, String alasanLain, String keputusanPegawai,
			String keputusanKptgPtg, String catatanPegawai, Date tarikhLulus,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idBke, tblrujppkjenisperintah, tblppkpermohonan, tblrujppkunit,
				idNegeri, idDaerah, tarikhMohon, idNegeriunitpsk, alasan1,
				alasan2, alasan3, alasan4, alasan5, alasan6, alasan7,
				alasanLain, keputusanPegawai, keputusanKptgPtg, catatanPegawai,
				tarikhLulus, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
