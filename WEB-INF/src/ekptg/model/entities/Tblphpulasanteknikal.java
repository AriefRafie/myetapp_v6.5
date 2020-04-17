package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpulasanteknikal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpulasanteknikal extends AbstractTblphpulasanteknikal
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpulasanteknikal() {
	}

	/** minimal constructor */
	public Tblphpulasanteknikal(Long idUlasanteknikal) {
		super(idUlasanteknikal);
	}

	/** full constructor */
	public Tblphpulasanteknikal(Long idUlasanteknikal, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppenyediaulasanjpphs, Set tblphpulasankjpkjts,
			Set tblphpulasanjupems, Set tblphppenyediaulasankjpkjts,
			Set tblphpulasanjpphs) {
		super(idUlasanteknikal, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblphppenyediaulasanjpphs,
				tblphpulasankjpkjts, tblphpulasanjupems,
				tblphppenyediaulasankjpkjts, tblphpulasanjpphs);
	}

}
