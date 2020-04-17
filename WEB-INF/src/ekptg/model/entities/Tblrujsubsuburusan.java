package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujsuburusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujsubsuburusan extends AbstractTblrujsubsuburusan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujsubsuburusan() {
	}

	/** minimal constructor */
	public Tblrujsubsuburusan(Long idSubrusan) {
		super(idSubrusan);
	}

	/** full constructor */
	public Tblrujsubsuburusan(Long idSuburusan, String kodSubsuburusan,
			String namaSubsuburusan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idSuburusan, kodSubsuburusan, namaSubsuburusan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
