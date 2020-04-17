package ekptg.model.entities;


import java.util.Date;
import java.util.Set;

/**
 * Tblonlineaduan entity. @author MyEclipse Persistence Tools
 */
public class Tblonlineaduan extends AbstractTblonlineaduan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblonlineaduan() {
	}

	/** minimal constructor */
	public Tblonlineaduan(Long idAduan) {
		super(idAduan);
	}

	/** full constructor */
	public Tblonlineaduan(Long idAduan, Long idPegawai,
			Long idJenisaduan, Long idSeksyen,
			Long noAduanOnline, String aduan, String dirBuktiAduan,
			Date tarikhAduan, Date tarikhPengagihan,
			Date tarikhPengagihanKpdpegawai, String flagStatusAduan,
			String tindakanSusulan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb,
			Set tblonlinepengaduans) {
		super(idAduan, idPegawai, idJenisaduan, idSeksyen,
				noAduanOnline, aduan, dirBuktiAduan, tarikhAduan,
				tarikhPengagihan, tarikhPengagihanKpdpegawai, flagStatusAduan,
				tindakanSusulan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb, tblonlinepengaduans);
	}

}
