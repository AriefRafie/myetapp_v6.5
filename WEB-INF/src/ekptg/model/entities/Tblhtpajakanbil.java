package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpajakanbil entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpajakanbil extends AbstractTblhtpajakanbil implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpajakanbil() {
	}

	/** minimal constructor */
	public Tblhtpajakanbil(Long idBil, Tblhtppajakankadar idKadar, Long idBayaran,
			Long idMasuk) {
		super(idBil, idKadar, idBayaran, idMasuk);
	}

	/** full constructor */
	public Tblhtpajakanbil(Long idBil, Tblhtppajakankadar idKadar, Long idBayaran,
			Double bayaranTertunggak, Date tarikhBil, Date tarikhBayar,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Long tarikhKemaskini) {
		super(idBil, idKadar, idBayaran, bayaranTertunggak, tarikhBil,
				tarikhBayar, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
