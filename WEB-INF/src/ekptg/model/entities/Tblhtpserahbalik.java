package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpserahbalik entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpserahbalik extends AbstractTblhtpserahbalik implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpserahbalik() {
	}

	/** minimal constructor */
	public Tblhtpserahbalik(String idSerahbalik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik) {
		super(idSerahbalik, idPenswastaan, idHtphakmilik, idHakmilik);
	}

	/** full constructor */
	public Tblhtpserahbalik(String idSerahbalik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik, Date tarikhTandatangan,
			Date tarikhHantar, Date tarikhDaftar, Date tarikhBatalRekod,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idSerahbalik, idPenswastaan, idHtphakmilik, idHakmilik,
				tarikhTandatangan, tarikhHantar, tarikhDaftar,
				tarikhBatalRekod, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
