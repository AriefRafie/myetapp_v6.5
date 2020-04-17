package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujsuburusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujsuburusan extends AbstractTblrujsuburusan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujsuburusan() {
	}

	/** minimal constructor */
	public Tblrujsuburusan(Long idUrusan, Long idSeksyen) {
		super(idUrusan, idSeksyen);
	}

	/** full constructor */
	public Tblrujsuburusan(Long idUrusan, String kodSuburusan,
			String namaSuburusan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idSeksyen) {
		super(idUrusan, kodSuburusan, namaSuburusan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idSeksyen);
	}

}
