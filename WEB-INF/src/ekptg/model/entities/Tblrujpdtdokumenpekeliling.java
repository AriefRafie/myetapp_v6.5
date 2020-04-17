package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujpdtdokumenpekeliling entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujpdtdokumenpekeliling extends
		AbstractTblrujpdtdokumenpekeliling implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujpdtdokumenpekeliling() {
	}

	/** minimal constructor */
	public Tblrujpdtdokumenpekeliling(Long idDokumenPekeliling) {
		super(idDokumenPekeliling);
	}

	/** full constructor */
	public Tblrujpdtdokumenpekeliling(Long idDokumenPekeliling,
			String jenisDokumenPekeliling, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idDokumenPekeliling, jenisDokumenPekeliling, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}
 
}
