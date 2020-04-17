package ekptg.model.entities;

import java.util.Date;

/**
 * TblrujenistanahId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblrujenistanahId extends AbstractTblrujenistanahId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblrujenistanahId() {
	}

	/** minimal constructor */
	public TblrujenistanahId(Long idJenistanah) {
		super(idJenistanah);
	}

	/** full constructor */
	public TblrujenistanahId(Long idJenistanah, String kodJenisTanah,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idJenistanah, kodJenisTanah, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
