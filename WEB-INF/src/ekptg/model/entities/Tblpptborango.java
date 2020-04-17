package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborango entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborango extends AbstractTblpptborango implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborango() {
	}

	/** full constructor */
	public Tblpptborango(String noBorango, Date tarikhBorango,
			Date tarikhCetak, Date tarikhCetakSemula, String namaMahkamah,
			String alamatMahkamah, Long idBantahan, String ringkasanBantahan,
			String perihalTanaman, Date tarikhTerimaPerintah,
			Date tarikhTerimaSuratmahkamah, String flagPulangDeposit,
			Date tarikhPerintah, Long idBank, String noAkaun,
			Long keputusanMahkamah, String perintahMahkamah, Double amaunDenda,
			Long tarikhAkhirBayar, Long tempohBayarAward,
			String noRujukanTanah, Long caraSelesai, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Double kosPengapitHakim, Long idDb) {
		super(noBorango, tarikhBorango, tarikhCetak, tarikhCetakSemula,
				namaMahkamah, alamatMahkamah, idBantahan, ringkasanBantahan,
				perihalTanaman, tarikhTerimaPerintah,
				tarikhTerimaSuratmahkamah, flagPulangDeposit, tarikhPerintah,
				idBank, noAkaun, keputusanMahkamah, perintahMahkamah,
				amaunDenda, tarikhAkhirBayar, tempohBayarAward, noRujukanTanah,
				caraSelesai, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, kosPengapitHakim, idDb);
	}

}
