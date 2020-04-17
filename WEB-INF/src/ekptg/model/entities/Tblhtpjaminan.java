package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpjaminan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpjaminan extends AbstractTblhtpjaminan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpjaminan() {
	}

	/** minimal constructor */
	public Tblhtpjaminan(Long idJaminan, Long idHtphakmilik, String ulasan) {
		super(idJaminan, idHtphakmilik, ulasan);
	}

	/** full constructor */
	public Tblhtpjaminan(Long idJaminan, Long idHtphakmilik, String ulasan,
			Date tarikhSerah, Date tarikhSerahSebenar, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idJaminan, idHtphakmilik, ulasan, tarikhSerah,
				tarikhSerahSebenar, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
