package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtphakmilikbangunan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtphakmilikbangunan extends AbstractTblhtphakmilikbangunan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtphakmilikbangunan() {
	}

	/** minimal constructor */
	public Tblhtphakmilikbangunan(Long idHakmilikbangunan, Long idPermohonan,
			Long idLuas, Long idMukim, Long idDaerah, Long idNegeri) {
		super(idHakmilikbangunan, idPermohonan, idLuas, idMukim, idDaerah,
				idNegeri);
	}

	/** full constructor */
	public Tblhtphakmilikbangunan(Long idHakmilikbangunan, Long idPermohonan,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idLuas, String luas, Long idMukim, Long idDaerah,
			Long idNegeri, String sewaBulanan, String ulasan, Date tarikhMula,
			Date tarikhTamat, Long idPegawai, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idHakmilikbangunan, idPermohonan, alamat1, alamat2, alamat3,
				poskod, idLuas, luas, idMukim, idDaerah, idNegeri, sewaBulanan,
				ulasan, tarikhMula, tarikhTamat, idPegawai, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}
	
	/** full constructor */
	public Tblhtphakmilikbangunan(Long idHakmilikbangunan, Long idPermohonan,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idLuas, String luas, Long idMukim, Long idDaerah,
			Long idNegeri, Double sewaBulanan, String ulasan, Date tarikhMula,
			Date tarikhTamat, Long idPegawai, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idHakmilikbangunan, idPermohonan, alamat1, alamat2, alamat3,
				poskod, idLuas, luas, idMukim, idDaerah, idNegeri, sewaBulanan,
				ulasan, tarikhMula, tarikhTamat, idPegawai, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
