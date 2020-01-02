package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpfdminitarahan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpfdminitarahan extends AbstractTblpfdminitarahan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdminitarahan() {
	}

	/** minimal constructor */
	public Tblpfdminitarahan(Long idMinitArahan) {
		super(idMinitArahan);
	}

	/** full constructor */
	public Tblpfdminitarahan(Long idMinitArahan, String minitArahan, Long idPegawaiYgmengarah, 
                Long idPegawaiYgmenerima, Date tarikhMinitArahan,Long idDokumen, Long idDb, String statusTindakan) {
		super(idMinitArahan, minitArahan, idPegawaiYgmengarah, idPegawaiYgmenerima, tarikhMinitArahan, idDokumen, idDb,
                statusTindakan);
	}

}
