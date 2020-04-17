package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujjenispb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujjenispb extends AbstractTblrujjenispb implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenispb() {
	}

	/** minimal constructor */
	public Tblrujjenispb(Long idJenispb) {
		super(idJenispb);
	}

	/** full constructor */
	public Tblrujjenispb(Long idJenispb, String kodJenisPb, String keterangan,
			String jenisDaftarPb, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idJenispb, kodJenisPb, keterangan, jenisDaftarPb, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
