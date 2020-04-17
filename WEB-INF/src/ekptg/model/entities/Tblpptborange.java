package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptborange entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptborange extends AbstractTblpptborange implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptborange() {
	}

	/** full constructor */
	public Tblpptborange(Date tarikhBorange, Date tarikhSiasatan,
			String masaSiasatan, String tempatSiasatan, String alamat1,
			String alamat2, String alamat3, String poskod, Long idNegeri,
			Date tarikhCetak, Date tarikhCetakSemula, Date tarikhAkhirTampal,
			String flagAktif, Long idPermohonan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(tarikhBorange, tarikhSiasatan, masaSiasatan, tempatSiasatan,
				alamat1, alamat2, alamat3, poskod, idNegeri, tarikhCetak,
				tarikhCetakSemula, tarikhAkhirTampal, flagAktif, idPermohonan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
