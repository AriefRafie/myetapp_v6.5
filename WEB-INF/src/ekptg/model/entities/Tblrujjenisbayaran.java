package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujjenisbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujjenisbayaran extends AbstractTblrujjenisbayaran implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenisbayaran() {
	}

	/** minimal constructor */
	public Tblrujjenisbayaran(Long idJenisbayaran, String kodJenisBayar) {
		super(idJenisbayaran, kodJenisBayar);
	}

	/** full constructor */
	public Tblrujjenisbayaran(Long idJenisbayaran, String kodJenisBayar,
			String keterangan, Long idBayaran, Double amaun, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idJenisbayaran, kodJenisBayar, keterangan, idBayaran, amaun,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
