package ekptg.model.entities;

import java.util.Date;

	/**
	 * Tblppkrujunit entity.
	 * 
	 * @author by Elly (080709)
	 */
	public class Tblppkrujunit extends AbstractTblppkrujunit implements
			java.io.Serializable {

		// Constructors

		/** default constructor */
		public Tblppkrujunit() {
		}

		/** full constructor */
		public Tblppkrujunit(String kod,Long idNegeri,Long idJkptg,String namapejabat,String keteranganunitpsk,String namapegawai,String jawatan,String statuspeg,
				String alamat1,String alamat2,String alamat3,String poskod,String notel,String notelsambungan,String catatan,Long idMasuk,Date tarikhMasuk,Long idKemaskini,
				Long idDB) {
			super(kod, idNegeri, idJkptg, namapejabat, keteranganunitpsk,
					namapegawai, jawatan, statuspeg, alamat1, alamat2, alamat3, poskod, notel, notelsambungan, catatan, idMasuk, tarikhMasuk, idKemaskini, idDB);
		}

	}

