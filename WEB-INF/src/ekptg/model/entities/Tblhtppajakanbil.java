package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtppajakanbil entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppajakanbil extends AbstractTblhtppajakanbil implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppajakanbil() {
	}

	/** minimal constructor */
	public Tblhtppajakanbil(Long idPajakanbil, Long idKadar, Long idBayaran) {
		super(idPajakanbil, idKadar, idBayaran);
	}

	/** full constructor */
	public Tblhtppajakanbil(Long idPajakanbil, Long idKadar, Long idBayaran,
			Double bayaranTertunggak, Date tarikhBil, Date tarikhBayar,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPajakanbil, idKadar, idBayaran, bayaranTertunggak, tarikhBil,
				tarikhBayar, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
