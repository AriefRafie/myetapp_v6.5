package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujbebanan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujbebanan extends AbstractTblrujbebanan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujbebanan() {
	}

	/** minimal constructor */
	public Tblrujbebanan(Long idRujbebanan) {
		super(idRujbebanan);
	}

	/** full constructor */
	public Tblrujbebanan(Long idRujbebanan, String kodBebanan,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idRujbebanan, kodBebanan, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
