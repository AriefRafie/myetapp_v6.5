package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppknotabicara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppknotabicara extends AbstractTblppknotabicara implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppknotabicara() {
	}

	/** minimal constructor */
	public Tblppknotabicara(Long idNotabicara,
			Tblppkperbicaraan tblppkperbicaraan) {
		super(idNotabicara, tblppkperbicaraan);
	}

	/** full constructor */
	public Tblppknotabicara(Long idNotabicara,
			Tblppkperbicaraan tblppkperbicaraan, String noJilid, String dir,
			Long bil, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idNotabicara, tblppkperbicaraan, noJilid, dir, bil, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
