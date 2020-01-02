package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpprojeklesenapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpprojeklesenapb extends AbstractTblphpprojeklesenapb
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpprojeklesenapb() {
	}

	/** minimal constructor */
	public Tblphpprojeklesenapb(Long idProjeklesenapb) {
		super(idProjeklesenapb);
	}

	/** full constructor */
	public Tblphpprojeklesenapb(Long idProjeklesenapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama,
			String namaProjek, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idProjeklesenapb, tblphppmohonnjdualpertama, namaProjek, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
