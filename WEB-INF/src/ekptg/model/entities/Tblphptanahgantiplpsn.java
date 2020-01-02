package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphptanahgantiplpsn entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphptanahgantiplpsn extends AbstractTblphptanahgantiplpsn
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphptanahgantiplpsn() {
	}

	/** minimal constructor */
	public Tblphptanahgantiplpsn(Long idTanahganti) {
		super(idTanahganti);
	}

	/** full constructor */
	public Tblphptanahgantiplpsn(Long idTanahganti,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan,
			Long idHakmilik, Long idNegeri, Long idDaerah, Long idMukim,
			Long idJenishm, Long idSyaratnyata, Long idSekatan, String noHm,
			Long idLot, String noLot, Double luas, Long idUnitluas,
			Long idKategori, Double kadarNilaianJpph, Double jumlahNilaianJpph,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, String flagDaftarHtp,
			String flagHakmilikPersekutuan) {
		super(idTanahganti, tblphppermohonanpelepasan, idHakmilik, idNegeri,
				idDaerah, idMukim, idJenishm, idSyaratnyata, idSekatan, noHm,
				idLot, noLot, luas, idUnitluas, idKategori, kadarNilaianJpph,
				jumlahNilaianJpph, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, flagDaftarHtp, flagHakmilikPersekutuan);
	}

}
