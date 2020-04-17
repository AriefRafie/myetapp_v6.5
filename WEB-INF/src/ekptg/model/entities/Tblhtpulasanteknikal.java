package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpulasanteknikal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpulasanteknikal extends AbstractTblhtpulasanteknikal
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpulasanteknikal() {
	}

	/** minimal constructor */
	public Tblhtpulasanteknikal(String idUlasanteknikal,
			Tblhtppermohonan tblhtppermohonan, Long idNegeri, Long idDaerah,
			Long idAgensi, Long idKementerian, Long idMasuk) {
		super(idUlasanteknikal, tblhtppermohonan, idNegeri, idDaerah, idAgensi,
				idKementerian, idMasuk);
	}

	/** full constructor */
	public Tblhtpulasanteknikal(String idUlasanteknikal,
			Tblhtppermohonan tblhtppermohonan, String noRujkjt, Long idNegeri,
			Long idDaerah, Long idAgensi, Long idKementerian,
			Date tarikhHantar, Date tarikhTerima, Date tarikhSuratKeputusan,
			String ulasan, String statusKeputusan, String idPejabat,
			String namaPegawai, String idJnsdokumen, Long idMasuk,
			Date tarikhMasuk) {
		super(idUlasanteknikal, tblhtppermohonan, noRujkjt, idNegeri, idDaerah,
				idAgensi, idKementerian, tarikhHantar, tarikhTerima,
				tarikhSuratKeputusan, ulasan, statusKeputusan, idPejabat,
				namaPegawai, idJnsdokumen, idMasuk, tarikhMasuk);
	}

}
