package ekptg.model.entities;

import java.util.Date;

/**
 * Tblsemakansenarai entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblsemakansenarai extends AbstractTblsemakansenarai implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblsemakansenarai() {
	}

	/** minimal constructor */
	public Tblsemakansenarai(Long idSemakan, Long idUrusan, String kodForm) {
		super(idSemakan, idUrusan, kodForm);
	}

	/** full constructor */
	public Tblsemakansenarai(Long idSemakan, Long idUrusan, String kodForm,
			Long aturan, String catatan, Long idSuburusan,
			Long idKategoripemohon, String statusDokumen, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idSemakan, idUrusan, kodForm, aturan, catatan, idSuburusan,
				idKategoripemohon, statusDokumen, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
