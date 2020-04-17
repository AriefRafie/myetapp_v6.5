package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujseksyen extends AbstractTblrujseksyen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujseksyen() {
	}

	/** minimal constructor */
	public Tblrujseksyen(Long idSeksyen) {
		super(idSeksyen);
	}

	/** full constructor */
	public Tblrujseksyen(Long idSeksyen, String kodSeksyen, String namaSeksyen,
			String versiSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idSeksyen, kodSeksyen, namaSeksyen, versiSeksyen, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
