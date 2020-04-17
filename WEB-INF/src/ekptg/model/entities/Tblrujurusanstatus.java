package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujurusanstatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujurusanstatus extends AbstractTblrujurusanstatus implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujurusanstatus() {
	}

	/** minimal constructor */
	public Tblrujurusanstatus(Long idUrusanstatus, Long idPermohonan,
			Long idStatusFail, Long idUrusan) {
		super(idUrusanstatus, idPermohonan, idStatusFail, idUrusan);
	}

	/** full constructor */
	public Tblrujurusanstatus(Long idUrusanstatus, Long idPermohonan,
			Long idStatusFail, Long idUrusan, Date tarikhMula,
			Date tarikhAkhir, String aktif, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idUrusanstatus, idPermohonan, idStatusFail, idUrusan, tarikhMula,
				tarikhAkhir, aktif, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
