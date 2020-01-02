package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphppemeganglesenapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppemeganglesenapb extends AbstractTblphppemeganglesenapb
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppemeganglesenapb() {
	}

	/** minimal constructor */
	public Tblphppemeganglesenapb(Long idPemeganglesenapb) {
		super(idPemeganglesenapb);
	}

	/** full constructor */
	public Tblphppemeganglesenapb(Long idPemeganglesenapb,
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppemeganglesenperjanjians) {
		super(idPemeganglesenapb, tblphpjadualkedualesenapb, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblphppemeganglesenperjanjians);
	}

}
