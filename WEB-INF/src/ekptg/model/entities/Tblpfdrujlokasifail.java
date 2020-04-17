package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpfdrujlokasifail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpfdrujlokasifail extends AbstractTblpfdrujlokasifail implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdrujlokasifail() {
	}

	/** full constructor */
	public Tblpfdrujlokasifail(String lokasiFail, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(lokasiFail, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
