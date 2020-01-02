package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanaktapenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanaktapenggal extends AbstractTblpdtpindaanaktapenggal
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanaktapenggal() {
	}

	/** minimal constructor */
	public Tblpdtpindaanaktapenggal(Long idPindaanaktapenggal) {
		super(idPindaanaktapenggal);
	}

	/** full constructor */
	public Tblpdtpindaanaktapenggal(Long idPindaanaktapenggal,
			Tblpdtaktapenggal tblpdtaktapenggal,
			Tblpdtaktapindapenggal tblpdtaktapindapenggal,
			Set tblpdtpindaanaktas) {
		super(idPindaanaktapenggal, tblpdtaktapenggal, tblpdtaktapindapenggal,
				tblpdtpindaanaktas);
	}

}
