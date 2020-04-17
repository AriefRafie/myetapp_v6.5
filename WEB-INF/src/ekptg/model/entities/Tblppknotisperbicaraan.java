package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppknotisperbicaraan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppknotisperbicaraan extends AbstractTblppknotisperbicaraan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppknotisperbicaraan() {
	}

	/** minimal constructor */
	public Tblppknotisperbicaraan(Long idNotisperbicaraan,
			Tblppknotisobmst tblppknotisobmst,
			Tblppkperbicaraan tblppkperbicaraan) {
		super(idNotisperbicaraan, tblppknotisobmst, tblppkperbicaraan);
	}

	/** full constructor */
	public Tblppknotisperbicaraan(Long idNotisperbicaraan,
			Tblppknotisobmst tblppknotisobmst,
			Tblppkperbicaraan tblppkperbicaraan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idNotisperbicaraan, tblppknotisobmst, tblppkperbicaraan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
