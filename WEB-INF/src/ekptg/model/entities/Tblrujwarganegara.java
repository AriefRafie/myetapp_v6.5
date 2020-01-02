package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujwarganegara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujwarganegara extends AbstractTblrujwarganegara implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujwarganegara() {
	}

	/** minimal constructor */
	public Tblrujwarganegara(Long idWarganegara) {
		super(idWarganegara);
	}

	/** full constructor */
	public Tblrujwarganegara(Long idWarganegara, String kodWarga,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idWarganegara, kodWarga, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
