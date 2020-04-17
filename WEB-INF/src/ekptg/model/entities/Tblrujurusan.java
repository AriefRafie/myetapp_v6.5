package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujurusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujurusan extends AbstractTblrujurusan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujurusan() {
	}

	/** minimal constructor */
	public Tblrujurusan(Long idUrusan) {
		super(idUrusan);
	}

	/** full constructor */
	public Tblrujurusan(Long idUrusan, String kodUrusan, String namaUrusan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idUrusan, kodUrusan, namaUrusan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
