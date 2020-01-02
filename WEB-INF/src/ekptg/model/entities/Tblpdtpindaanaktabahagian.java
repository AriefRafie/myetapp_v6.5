package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanaktabahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanaktabahagian extends
		AbstractTblpdtpindaanaktabahagian implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanaktabahagian() {
	}

	/** minimal constructor */
	public Tblpdtpindaanaktabahagian(Long idPindaanaktabahagian) {
		super(idPindaanaktabahagian);
	}

	/** full constructor */
	public Tblpdtpindaanaktabahagian(Long idPindaanaktabahagian,
			Tblpdtaktabahagian tblpdtaktabahagian,
			Tblpdtaktapindabahagian tblpdtaktapindabahagian,
			Set tblpdtpindaanaktas) {
		super(idPindaanaktabahagian, tblpdtaktabahagian,
				tblpdtaktapindabahagian, tblpdtpindaanaktas);
	}

}
