package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpfdfailmesyuarat entity. @author MyEclipse Persistence Tools
 */
public class Tblpfdfailmesyuarat extends AbstractTblpfdfailmesyuarat implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdfailmesyuarat() {
	}

	/** full constructor */
	public Tblpfdfailmesyuarat(Long idFail, Long idMesyuarat,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idFail, idMesyuarat, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
