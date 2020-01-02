package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpinfoborangk entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpinfoborangk extends AbstractTblhtpinfoborangk implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpinfoborangk() {
	}

	/** minimal constructor */
	public Tblhtpinfoborangk(Long idInfoborangk, Long idBorangk,
			Long idHtphakmilik) {
		super(idInfoborangk, idBorangk, idHtphakmilik);
	}

	/** full constructor */
	public Tblhtpinfoborangk(Long idInfoborangk, Long idBorangk,
			Long idHtphakmilik, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idInfoborangk, idBorangk, idHtphakmilik, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
