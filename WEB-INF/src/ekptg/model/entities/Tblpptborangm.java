package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborangm entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborangm extends AbstractTblpptborangm implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborangm() {
	}

	/** full constructor */
	public Tblpptborangm(String namaMahkamah, String alamatMahkamah1,
			String alamatMahkamah2, String alamatMahkamah3, String tujuan,
			String perkaraRujukan, Date tarikhBorangm, Long keputusanMahkamah,
			String perintahMahkamah, Date tarikhPerintah,
			Date tarikhTerimaPerintah, Double pampasanAsal,
			Double pampasanBantahan, Double bezaPampasan,
			String noRujukanProsiding, Long idPermohonan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(namaMahkamah, alamatMahkamah1, alamatMahkamah2, alamatMahkamah3,
				tujuan, perkaraRujukan, tarikhBorangm, keputusanMahkamah,
				perintahMahkamah, tarikhPerintah, tarikhTerimaPerintah,
				pampasanAsal, pampasanBantahan, bezaPampasan,
				noRujukanProsiding, idPermohonan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
