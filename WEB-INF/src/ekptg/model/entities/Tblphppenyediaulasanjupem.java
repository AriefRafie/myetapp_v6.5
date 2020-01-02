package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppenyediaulasanjupem entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppenyediaulasanjupem extends
		AbstractTblphppenyediaulasanjupem implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppenyediaulasanjupem() {
	}

	/** minimal constructor */
	public Tblphppenyediaulasanjupem(Long idPenyediaulasanjupem) {
		super(idPenyediaulasanjupem);
	}

	/** full constructor */
	public Tblphppenyediaulasanjupem(Long idPenyediaulasanjupem,
			Tblphpulasanjupem tblphpulasanjupem, Long idUlasanteknikal,
			String namaPenyedia, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idPenyediaulasanjupem, tblphpulasanjupem, idUlasanteknikal,
				namaPenyedia, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
