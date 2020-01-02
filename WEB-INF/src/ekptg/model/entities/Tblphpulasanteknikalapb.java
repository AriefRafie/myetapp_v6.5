package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpulasanteknikalapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpulasanteknikalapb extends AbstractTblphpulasanteknikalapb
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpulasanteknikalapb() {
	}

	/** minimal constructor */
	public Tblphpulasanteknikalapb(Long idUlasanteknikalapb) {
		super(idUlasanteknikalapb);
	}

	/** full constructor */
	public Tblphpulasanteknikalapb(Long idUlasanteknikalapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idUlasanteknikal) {
		super(idUlasanteknikalapb, tblphppmohonnjdualpertama, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idUlasanteknikal);
	}

}
