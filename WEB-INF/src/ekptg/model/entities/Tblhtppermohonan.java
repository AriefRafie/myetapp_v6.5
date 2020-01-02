package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtppermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppermohonan extends AbstractTblhtppermohonan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppermohonan() {
	}

	/** minimal constructor */
	public Tblhtppermohonan(Long idHtppermohonan, Long idPegawai) {
		super(idHtppermohonan, idPegawai);
	}

	/** full constructor */
	public Tblhtppermohonan(Long idHtppermohonan, Long idPermohonan,
			Long idAgensi, String idJenistanah, Long idPegawai,
			String noRujukanKjp, String noRujukanLain, Date tarikhAgihan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idHtppermohonan, idPermohonan, idAgensi, idJenistanah, idPegawai,
				noRujukanKjp, noRujukanLain, tarikhAgihan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
