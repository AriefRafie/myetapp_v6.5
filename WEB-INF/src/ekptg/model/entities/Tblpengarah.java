package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpengarah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpengarah extends AbstractTblpengarah implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpengarah() {
	}

	/** minimal constructor */
	public Tblpengarah(Long idPengarah, Long idPemaju, Long idRujjenisopb,
			Long idWarganegara, Long idUrusan, Long idSeksyen) {
		super(idPengarah, idPemaju, idRujjenisopb, idWarganegara, idUrusan,
				idSeksyen);
	}

	/** full constructor */
	public Tblpengarah(Long idPengarah, Long idPemaju, Long idRujjenisopb,
			String noOpb, String nama, Long idWarganegara, Long idUrusan,
			Long idSeksyen, Long idKemaskini, Date tarikhKemaskini) {
		super(idPengarah, idPemaju, idRujjenisopb, noOpb, nama, idWarganegara,
				idUrusan, idSeksyen, idKemaskini, tarikhKemaskini);
	}

}
