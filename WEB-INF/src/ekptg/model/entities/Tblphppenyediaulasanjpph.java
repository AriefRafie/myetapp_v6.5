package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppenyediaulasanjpph entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppenyediaulasanjpph extends AbstractTblphppenyediaulasanjpph
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppenyediaulasanjpph() {
	}

	/** minimal constructor */
	public Tblphppenyediaulasanjpph(Long idPenyediaulasanjpph) {
		super(idPenyediaulasanjpph);
	}

	/** full constructor */
	public Tblphppenyediaulasanjpph(Long idPenyediaulasanjpph,
			Tblphpulasanjpph tblphpulasanjpph,
			Tblphpulasanteknikal tblphpulasanteknikal, String namaPenyedia,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPenyediaulasanjpph, tblphpulasanjpph, tblphpulasanteknikal,
				namaPenyedia, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
