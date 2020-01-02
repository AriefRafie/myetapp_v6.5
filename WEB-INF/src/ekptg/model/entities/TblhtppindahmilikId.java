package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtppindahmilikId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtppindahmilikId extends AbstractTblhtppindahmilikId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtppindahmilikId() {
	}

	/** minimal constructor */
	public TblhtppindahmilikId(Long idPindahmilik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik) {
		super(idPindahmilik, idPenswastaan, idHtphakmilik, idHakmilik);
	}

	/** full constructor */
	public TblhtppindahmilikId(Long idPindahmilik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik, Date tarikhTandatangan,
			Date tarikhHantar, Date tarikhDaftar, Date tarikhBatalRekod,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPindahmilik, idPenswastaan, idHtphakmilik, idHakmilik,
				tarikhTandatangan, tarikhHantar, tarikhDaftar,
				tarikhBatalRekod, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
