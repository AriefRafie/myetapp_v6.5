package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpdtversi entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtversi extends AbstractTblpdtversi implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtversi() {
	}

	/** minimal constructor */
	public Tblpdtversi(Long idVersi) {
		super(idVersi);
	}

	/** full constructor */
	public Tblpdtversi(Long idVersi, Tblpdtmesyuarat tblpdtmesyuarat,
			Date tarikhVersi) {
		super(idVersi, tblpdtmesyuarat, tarikhVersi);
	}

}
