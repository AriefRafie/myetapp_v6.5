package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpfailapbbertindih entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpfailapbbertindih extends AbstractTblphpfailapbbertindih
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpfailapbbertindih() {
	}

	/** minimal constructor */
	public Tblphpfailapbbertindih(Long idFailapbbertindih) {
		super(idFailapbbertindih);
	}

	/** full constructor */
	public Tblphpfailapbbertindih(Long idFailapbbertindih,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama,
			String bertindihDenganFail, String namaSyarikatTindih,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idFailapbbertindih, tblphppmohonnjdualpertama,
				bertindihDenganFail, namaSyarikatTindih, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
