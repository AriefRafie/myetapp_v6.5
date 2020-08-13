package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujsuburusanstatusfail entity.
 *
 * @author MyEclipse Persistence Tools
 */
public class Tblrujsuburusanstatusfail extends
		AbstractTblrujsuburusanstatusfail implements java.io.Serializable {

	// class Tblrujsuburusanstatusfail
	// Constructors

	/** default constructor */
	public Tblrujsuburusanstatusfail() {
	}

	/** minimal constructor */
	public Tblrujsuburusanstatusfail(Long idSuburusanstatusfail, Long idPermohonan,
			Long idSuburusanstatus, Long idMasuk, Long idKemaskini,Long idFail) {
		super(idSuburusanstatusfail, idPermohonan, idSuburusanstatus, idMasuk,
				idKemaskini,idFail);
	}

	/** full constructor */
	public Tblrujsuburusanstatusfail(Long idSuburusanstatusfail, Long idPermohonan,
			Long idSuburusanstatus, String url, String aktif
			, Long idMasuk,Date tarikhMasuk, String tarikhMasukStr
			, Long idKemaskini, Date tarikhKemaskini, String tarikhKemaskiniStr
			, Long idFail) {
		super(idSuburusanstatusfail, idPermohonan, idSuburusanstatus, url, aktif,
				idMasuk, tarikhMasuk, tarikhMasukStr
				, idKemaskini, tarikhKemaskini, tarikhKemaskiniStr
				,idFail);
	}
}