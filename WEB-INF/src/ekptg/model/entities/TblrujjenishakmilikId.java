package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujjenishakmilikId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujjenishakmilikId extends AbstractTblrujjenishakmilikId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujjenishakmilikId() {
	}

	/** minimal constructor */
	public TblrujjenishakmilikId(Long idJenishakmilik) {
		super(idJenishakmilik);
	}

	/** full constructor */
	public TblrujjenishakmilikId(Long idJenishakmilik, String kodJnshm,
			String keterangan, String statusHm, String ownerHm, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idJenishakmilik, kodJnshm, keterangan, statusHm, ownerHm,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
