package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpfdfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpfdfail extends AbstractTblpfdfail implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdfail() {
	}

	/** full constructor */
	public Tblpfdfail(String kodJabatan, Long idTarafkeselamatan,
			Long idSeksyen, Long idUrusan, Long idSuburusan,
			Date tarikhDaftarFail, String tajukFail, String noFail,
			String noFailRoot, Long idLokasifail, Long idNegeri,
			Long idKementerian, Long idFaharasat, Long flagFail, Long idStatus,
			String catatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(kodJabatan, idTarafkeselamatan, idSeksyen, idUrusan, idSuburusan,
				tarikhDaftarFail, tajukFail, noFail, noFailRoot, idLokasifail,
				idNegeri, idKementerian, idFaharasat, flagFail, idStatus,
				catatan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
