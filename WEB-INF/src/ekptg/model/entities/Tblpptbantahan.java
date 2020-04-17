package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptbantahan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptbantahan extends AbstractTblpptbantahan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptbantahan() {
	}

	/** minimal constructor */
	public Tblpptbantahan(Long idKementerian) {
		super(idKementerian);
	}

	/** full constructor */
	public Tblpptbantahan(String noBantahan, Long jenisPembantah,
			String namaPembantah, Date tarikhTerima, Date tarikhBorangn,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, String flagPenerimaPampasan,
			String flagBahagianPampasan, String flagUkurLuas,
			String flagPampasan, String flagSyarat, Long idPihakberkepentingan,
			Long idKementerian, Long idAgensi, Double amaunAward,
			Date tarikhTerimaAward, Long idAward, String alasan,
			Long statusBantahan, Long caraBayar, String noRujukanBayaran,
			Date tarikhSuratBayardeposit, String tempohBayar, Long unitTempoh,
			Date tarikhAkhirBayardeposit, Date tarikhTerimaResit,
			Date tarikhResit, String noResit, String flagTerimadeposit,
			Double amaunDeposit, String noRujukanSurattarikbalik,
			Date tarikhTerimaTarikbalik, Date tarikhSuratTarikbalik,
			String flagTarikbalik, Date tarikhCetak, Date tarikhCetakSemula,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(noBantahan, jenisPembantah, namaPembantah, tarikhTerima,
				tarikhBorangn, alamat1, alamat2, alamat3, poskod, idNegeri,
				flagPenerimaPampasan, flagBahagianPampasan, flagUkurLuas,
				flagPampasan, flagSyarat, idPihakberkepentingan, idKementerian,
				idAgensi, amaunAward, tarikhTerimaAward, idAward, alasan,
				statusBantahan, caraBayar, noRujukanBayaran,
				tarikhSuratBayardeposit, tempohBayar, unitTempoh,
				tarikhAkhirBayardeposit, tarikhTerimaResit, tarikhResit,
				noResit, flagTerimadeposit, amaunDeposit,
				noRujukanSurattarikbalik, tarikhTerimaTarikbalik,
				tarikhSuratTarikbalik, flagTarikbalik, tarikhCetak,
				tarikhCetakSemula, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
