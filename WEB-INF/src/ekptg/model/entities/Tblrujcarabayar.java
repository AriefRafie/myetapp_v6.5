package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujcarabayar entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujcarabayar extends AbstractTblrujcarabayar implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujcarabayar() {
	}

	/** minimal constructor */
	public Tblrujcarabayar(Long idCarabayar) {
		super(idCarabayar);
	}

	/** full constructor */
	public Tblrujcarabayar(Long idCarabayar, String kodCaraBayar,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idCarabayar, kodCaraBayar, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
