package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpfddokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpfddokumen extends AbstractTblpfddokumen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfddokumen() {
	}

	/** minimal constructor */
	public Tblpfddokumen(Long idDokumen) {
		super(idDokumen);
	}

	/** full constructor */
	public Tblpfddokumen(Long idDokumen, Long idFail, Long idSubjaket,
			String namaPengirim, String noRujukanDokumen,
			String bilMinitDokumen, Date tarikhDokumenMasuk,
			Date tarikhDokumenKeluar, Date tarikhDaftar, String catatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idJenisdokumen, String namaPenerima,
			String namaPegawai, Long idDb, String flagDokumen) {
		super(idDokumen, idFail, idSubjaket, namaPengirim, noRujukanDokumen,
				bilMinitDokumen, tarikhDokumenMasuk, tarikhDokumenKeluar,
				tarikhDaftar, catatan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idJenisdokumen, namaPenerima, namaPegawai,
				idDb, flagDokumen);
	}

}
