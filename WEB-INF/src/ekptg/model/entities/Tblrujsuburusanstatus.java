package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujsuburusanstatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujsuburusanstatus extends AbstractTblrujsuburusanstatus
		implements java.io.Serializable {

	private static final long serialVersionUID = 5490830864989222472L;

	// Constructors
	/** default constructor */
	public Tblrujsuburusanstatus() {
	}

	/** minimal constructor */
	public Tblrujsuburusanstatus(Long idSuburusanstatus, Long idSuburusan,
			Long idStatus, Long idMasuk, Long idKemaskini) {
		super(idSuburusanstatus, idSuburusan, idStatus, idMasuk, idKemaskini);
	}

	/** full constructor */
	public Tblrujsuburusanstatus(Long idSuburusanstatus, Long idSuburusan,
			Long idStatus, String aktif, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set<?> tblrujsuburusanstatusfails,int langkah) {
		super(idSuburusanstatus, idSuburusan, idStatus, aktif, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblrujsuburusanstatusfails,langkah);
	}

}
