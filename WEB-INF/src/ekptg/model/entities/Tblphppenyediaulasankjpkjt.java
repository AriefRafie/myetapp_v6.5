package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppenyediaulasankjpkjt entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppenyediaulasankjpkjt extends
		AbstractTblphppenyediaulasankjpkjt implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppenyediaulasankjpkjt() {
	}

	/** minimal constructor */
	public Tblphppenyediaulasankjpkjt(Long idPenyediaulasankjpkjt) {
		super(idPenyediaulasankjpkjt);
	}

	/** full constructor */
	public Tblphppenyediaulasankjpkjt(Long idPenyediaulasankjpkjt,
			Tblphpulasanteknikal tblphpulasanteknikal, String namaPenyedia,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPenyediaulasankjpkjt, tblphpulasanteknikal, namaPenyedia,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
